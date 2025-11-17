package service;

import models.Livro;
import repository.LivroRepository;

import java.util.ArrayList;

public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    // Adicionar livro com validação
    public String adicionarLivro(Livro livro) {
        if (repository.procurarPorISBN(livro.getIsbn()) != null) {
            return "Já existe um livro com este ISBN.";
        }
        repository.adicionarLivro(livro);
        return "Livro adicionado com sucesso!";
    }

    // Procurar livro por título
    public String procurarLivro(String titulo) {
        Livro livro = repository.procurarPorTitulo(titulo);

        if (livro == null) {
            return "Livro não encontrado.";
        }

        return livro.toString();
    }

    // Procurar por ISBN
    public String procurarPorISBN(String isbn) {
        Livro livro = repository.procurarPorISBN(isbn);

        if (livro == null) {
            return "Livro não encontrado pelo ISBN.";
        }

        return livro.toString();
    }

    // Listar livros de determinado autor
    public String listarLivrosDeAutor(String autor) {
        ArrayList<Livro> encontrados = repository.buscarPorAutor(autor);

        if (encontrados.isEmpty()) {
            return "Nenhum livro encontrado para esse autor.";
        }

        String resultado = "Livros do autor " + autor + ":\n";
        for (Livro livro : encontrados) {
            resultado += livro.toString() + "\n";
        }

        return resultado;
    }

    // Listar livros disponíveis
    public String listarLivrosDisponiveis() {
        ArrayList<Livro> disponiveis = repository.livrosDisponiveis();

        if (disponiveis.isEmpty()) {
            return "Não há livros disponíveis.";
        }

        String resultado = "Livros disponíveis:\n";
        for (Livro livro : disponiveis) {
            resultado += livro.toString() + "\n";
        }

        return resultado;
    }

    // Emprestar livro
    public String emprestarLivro(String titulo) {
        Livro livro = repository.procurarPorTitulo(titulo);

        if (livro == null) {
            return "Livro não encontrado.";
        }

        if (!livro.isDisponivel()) {
            return "O livro já está emprestado.";
        }

        livro.setDisponivel(false);
        return "Livro emprestado com sucesso!";
    }

    // Devolver livro
    public String devolverLivro(String titulo) {
        Livro livro = repository.procurarPorTitulo(titulo);

        if (livro == null) {
            return "Livro não encontrado.";
        }

        if (livro.isDisponivel()) {
            return "O livro já está na biblioteca.";
        }

        livro.setDisponivel(true);
        return "Livro devolvido com sucesso!";
    }

    // Remover livro
    public String removerLivro(String titulo) {
        boolean removido = repository.removerLivro(titulo);

        if (removido) {
            return "Livro removido com sucesso.";
        }

        return "Livro não encontrado para remoção.";
    }
}
