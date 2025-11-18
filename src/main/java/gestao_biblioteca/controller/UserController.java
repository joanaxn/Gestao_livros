package gestao_biblioteca.controller;

import gestao_biblioteca.models.User;
import gestao_biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adicionar")
    public String adicionarUser(@RequestBody User user){
        return userService.adicionarUser(user);
    }

    @GetMapping("/{id}")
    public User procurarPorId(@PathVariable int id){
        return userService.procurarPorId(id);
    }

    @GetMapping("/listar")
    public ArrayList<User> listarUsers(){
        return (ArrayList<User>) userService.listarUsers();
    }

    @DeleteMapping("/{id}")
    public String removerUser(@PathVariable int id){
        return userService.removerUser(id);
    }
}
