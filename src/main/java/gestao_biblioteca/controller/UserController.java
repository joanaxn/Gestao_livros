package gestao_biblioteca.controller;

import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.models.User;
import gestao_biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adicionar")
    public String adicionarUser(@RequestBody User user){
        return userService.adicionarUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        User user = userService.procurarPorEmail(loginRequest.getEmail());

        if (user == null) return null;
        if (!user.getPassword().equals(loginRequest.getPassword())) return null;

        // remover lista para evitar recursão
        user.setLivrosemprestimos(null);
        return user;
    }

    @GetMapping("/listar")
    public ArrayList<User> listarUsers(){
        ArrayList<User> lista = userService.listarUsers();

        // APAGA SEMPRE empréstimos para frontend
        for (User u : lista) {
            u.setLivrosemprestimos(null);
        }

        return lista;
    }

    @GetMapping("/{id}/emprestimosAtivos")
    public ArrayList<Emprestimo> listarEmprestimosAtivos(@PathVariable int id) {
        ArrayList<Emprestimo> lista = userService.listarEmprestimosAtivos(id);

        for (Emprestimo e : lista) {
            e.setUser(null); // remover o user evita loops infinitos
        }

        return lista;
    }
}
