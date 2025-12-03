package gestao_biblioteca.service;

import gestao_biblioteca.models.User;
import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.repository.EmprestimoRepository;
import gestao_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final EmprestimoRepository emprestimoRepo;


    @Autowired
    public UserService(UserRepository repository,
                       EmprestimoRepository emprestimoRepo) {
        this.repository = repository;
        this.emprestimoRepo = emprestimoRepo;
    }


    public String adicionarUser(User user) {

        // Verificar email único
        if (repository.findByEmail(user.getEmail()) != null) {
            return "Já existe um utilizador com esse email.";
        }

        // JPA trata do ID e guarda o user
        repository.save(user);

        return "Utilizador adicionado!";
    }

    public User procurarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User procurarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public String removerUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Utilizador removido.";
        }
        return "Utilizador não encontrado.";
    }

    // POR AGORA devolve lista vazia (vamos corrigir quando migrarmos EmprestimoService)
    public ArrayList<Emprestimo> listarEmprestimosAtivos(Long userId) {

        List<Emprestimo> lista = emprestimoRepo.findByUserId(userId)
                .stream()
                .filter(e -> !e.isDevolvido())
                .toList();

        return new ArrayList<>(lista);
    }


    public ArrayList<User> listarUsers() {
        return new ArrayList<>(repository.findAll());
    }
}
