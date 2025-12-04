package gestao_biblioteca.service;

import gestao_biblioteca.models.Livro;
import gestao_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LivroService {

    private final LivroRepository repository;

    @Autowired
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public String adicionarLivro(Livro livro) {
        if (repository.findByIsbn(livro.getIsbn()) != null) {
            return "Já existe um livro com esse ISBN.";
        }

        repository.save(livro);
        return "Livro adicionado!";
    }

    public Livro procurarPorTitulo(String titulo) {
        return repository.findByTituloIgnoreCase(titulo);
    }

    public Livro procurarPorISBN(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public ArrayList<Livro> listarDisponiveis() {
        return new ArrayList<>(repository.findByDisponivelTrue());
    }

    public ArrayList<Livro> listarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    public boolean removerLivro(String titulo) {
        Livro livro = repository.findByTituloIgnoreCase(titulo);

        if (livro == null) return false;

        repository.delete(livro);
        return true;
    }
}
