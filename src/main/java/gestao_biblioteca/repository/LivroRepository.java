package gestao_biblioteca.repository;

import gestao_biblioteca.models.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LivroRepository {

    private ArrayList<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (!livros.contains(livro)) {
            livros.add(livro);
        }
    }

    public Livro procurarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Livro procurarPorISBN(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public ArrayList<Livro> buscarPorAutor(String autor) {
        ArrayList<Livro> resultados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public ArrayList<Livro> listarDisponiveis() {
        ArrayList<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public boolean removerLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }
}
