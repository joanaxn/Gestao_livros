package gestao_biblioteca.repository;

import gestao_biblioteca.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {

    private ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void adicionarUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public User procurarPorId(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public User procurarPorEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public boolean removerUser(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> listarUsers() {
        return new ArrayList<>(users);
    }
}
