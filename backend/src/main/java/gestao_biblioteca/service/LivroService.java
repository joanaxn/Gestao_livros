package gestao_biblioteca.service;

import gestao_biblioteca.models.Livro;
import gestao_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.ArrayList;

@Service
public class LivroService {

    private final LivroRepository repository;

    @Autowired
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public String adicionarLivro(Livro livro) {
        try {
            // Normalizar título e ISBN
            livro.setTitulo(livro.getTitulo().trim());
            if (livro.getIsbn() != null) {
                livro.setIsbn(livro.getIsbn().trim());
            }

            // Verificar se já existe livro com o mesmo título
            Livro livroExistentePorTitulo = repository.findByTituloIgnoreCase(livro.getTitulo());
            if (livroExistentePorTitulo != null) {
                return "Já existe um livro com esse título no catálogo.";
            }

            // Verificar se já existe livro com o mesmo ISBN
            if (livro.getIsbn() != null && !livro.getIsbn().isEmpty()) {
                Livro livroExistentePorIsbn = repository.findByIsbn(livro.getIsbn());
                if (livroExistentePorIsbn != null) {
                    return "Já existe um livro com esse ISBN no catálogo.";
                }
            }

            // Se passou nas validações, adiciona o livro
            repository.save(livro);
            return "Livro adicionado com sucesso!";

        } catch (DataIntegrityViolationException e) {
            // Fallback caso alguma constraint falhe na BD
            return "Erro: Já existe um livro com esse título ou ISBN no catálogo.";
        } catch (Exception e) {
            return "Erro ao adicionar livro: " + e.getMessage();
        }
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
