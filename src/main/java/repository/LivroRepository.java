package repository;

import models.Livro;
import java.util.ArrayList;

public class LivroRepository {

    private ArrayList<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    // Adicionar um livro
    public void adicionarLivro(Livro livro) {
        if (!livros.contains(livro)) {
            livros.add(livro);
        }
    }

    // Procurar um livro por título
    public Livro procurarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    // Procurar livro pelo ISBN
    public Livro procurarPorISBN(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return livro;
            }
        }
        return null;
    }

    // Listar livros de determinado autor
    public ArrayList<Livro> buscarPorAutor(String autor) {
        ArrayList<Livro> resultados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(livro);
            }
        }

        return resultados;
    }

    // Listar todos os livros disponíveis → devolve lista
    public ArrayList<Livro> livrosDisponiveis() {
        ArrayList<Livro> disponiveis = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }

        return disponiveis;
    }

    // Remover livro por título
    public boolean removerLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                return true;
            }
        }
        return false;
    }

    // Devolver lista completa de livros
    public ArrayList<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }
}
