package gestao_biblioteca.controller;

import gestao_biblioteca.models.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gestao_biblioteca.service.LivroService;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/adicionar")
    public String adicionarLivro(@RequestBody Livro livro){
        return livroService.adicionarLivro(livro);
    }

    @GetMapping("/titulo/{titulo}")
    public Livro procurarPorTitulo(@PathVariable String titulo){
        return livroService.procurarPorTitulo(titulo);
    }

    @GetMapping("/isbn/{isbn}")
    public Livro procurarPorISBN(@PathVariable String isbn){
        return livroService.procurarPorISBN(isbn);
    }

    @GetMapping("/disponiveis")
    public ArrayList<Livro> listarDisponiveis(){
        return livroService.listarDisponiveis();
    }

    @GetMapping("/todos")
    public ArrayList<Livro> listarTodos(){
        return livroService.listarTodos();
    }

    @DeleteMapping("/remover/{titulo}")
    public String removerLivro(@PathVariable String titulo){
        boolean removido = livroService.removerLivro(titulo);
        return removido ? "Livro removido com sucesso" : "Livro não encontrado";
    }
}
