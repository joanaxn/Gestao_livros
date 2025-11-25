package gestao_biblioteca.service;

import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.models.User;
import gestao_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserService {

    private final UserRepository repository;

    private int nextId = 1;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String adicionarUser(User user) {

        if (repository.procurarPorEmail(user.getEmail()) != null) {
            return "Já existe um utilizador com esse email.";
        }

        user.setId(nextId++);
        user.setLivrosemprestimos(new ArrayList<>());

        repository.adicionarUser(user);
        return "Utilizador adicionado!";
    }

    public User procurarPorId(int id) {
        return repository.procurarPorId(id);
    }

    public User procurarPorEmail(String email) {
        return repository.procurarPorEmail(email);
    }

    public String removerUser(int id) {
        if (repository.removerUser(id)) return "Utilizador removido.";
        return "Utilizador não encontrado.";
    }

    public ArrayList<Emprestimo> listarEmprestimosAtivos(int userId) {

        User user = repository.procurarPorId(userId);

        if (user == null || user.getLivrosemprestimos() == null)
            return new ArrayList<>();

        ArrayList<Emprestimo> ativos = new ArrayList<>();

        for (Emprestimo e : user.getLivrosemprestimos()) {
            if (!e.isDevolvido()) {
                Emprestimo copia = new Emprestimo(
                        e.getIdEmprestimo(),
                        null,
                        e.getLivro(),
                        e.getDataEmprestimo(),
                        e.getDataDevolucao(),
                        false
                );
                ativos.add(copia);
            }
        }

        return ativos;
    }

    public ArrayList<User> listarUsers() {
        return repository.listarUsers();
    }
}