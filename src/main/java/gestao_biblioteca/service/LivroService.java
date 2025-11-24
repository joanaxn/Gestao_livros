package gestao_biblioteca.service;

import gestao_biblioteca.models.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestao_biblioteca.repository.LivroRepository;

import java.util.ArrayList;

@Service
public class LivroService {

    private final LivroRepository repository;

    @Autowired
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public String adicionarLivro(Livro livro) {
        repository.adicionarLivro(livro);
        return "Livro adicionado!";
    }

    public Livro procurarPorTitulo(String titulo) {
        return repository.procurarPorTitulo(titulo);
    }

    public Livro procurarPorISBN(String isbn) {
        return repository.procurarPorISBN(isbn);
    }

    public ArrayList<Livro> listarDisponiveis() {
        return repository.listarDisponiveis();
    }

    public ArrayList<Livro> listarTodos() {
        return repository.listarTodos();
    }

    public boolean removerLivro(String titulo) {
        return repository.removerLivro(titulo);
    }
}
