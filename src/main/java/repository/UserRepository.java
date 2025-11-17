package repository;

import models.User;
import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    // adicionar user sem duplicar
    public void adicionarUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    // procurar user por ID
    public User procurarPorId(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // procurar por email
    public User procurarPorEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // remover user
    public boolean removerUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    // listar users
    public ArrayList<User> listarUsers() {
        return new ArrayList<>(users);
    }
}
