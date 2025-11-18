package gestao_biblioteca.service;

import gestao_biblioteca.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestao_biblioteca.repository.UserRepository;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String adicionarUser(User user) {
        if (repository.procurarPorEmail(user.getEmail()) != null) {
            return "Já existe um utilizador com esse email.";
        }
        repository.adicionarUser(user);
        return "Utilizador adicionado!";
    }

    public User procurarPorId(int id) {
        return repository.procurarPorId(id);
    }

    public String removerUser(int id) {
        if (repository.removerUser(id)) return "Utilizador removido.";
        return "Utilizador não encontrado.";
    }

    public ArrayList<User> listarUsers() {
        return repository.listarUsers();
    }
}
