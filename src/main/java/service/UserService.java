package service;

import models.User;
import repository.UserRepository;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String adicionarUser(User user) {
        if (repository.procurarPorEmail(user.getEmail()) != null) {
            return "Já existe um utilizador com este email.";
        }

        repository.adicionarUser(user);
        return "Utilizador adicionado com sucesso!";
    }

    public String procurarUser(int id) {
        User user = repository.procurarPorId(id);

        if (user == null) {
            return "Utilizador não encontrado.";
        }

        return user.toString();
    }

    public String removerUser(int id) {
        boolean removido = repository.removerUser(id);

        if (removido) {
            return "Utilizador removido com sucesso.";
        }

        return "Utilizador não encontrado.";
    }

    public String listarUsers() {
        var lista = repository.listarUsers();

        if (lista.isEmpty()) {
            return "Não existem utilizadores registados.";
        }

        String txt = "Lista de utilizadores:\n";
        for (User u : lista) {
            txt += u.toString() + "\n";
        }

        return txt;
    }
}
