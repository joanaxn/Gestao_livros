package service;

import models.Emprestimo;
import models.Livro;
import models.User;
import repository.EmprestimoRepository;
import repository.LivroRepository;
import repository.UserRepository;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepo;
    private LivroRepository livroRepo;
    private UserRepository userRepo;

    public EmprestimoService(EmprestimoRepository e, LivroRepository l, UserRepository u) {
        this.emprestimoRepo = e;
        this.livroRepo = l;
        this.userRepo = u;
    }

    // Emprestar livro
    public String emprestarLivro(int userId, String tituloLivro, String dataEmprestimo, int idEmprestimo) {

        User user = userRepo.procurarPorId(userId);
        if (user == null) {
            return "Utilizador não encontrado.";
        }

        Livro livro = livroRepo.procurarPorTitulo(tituloLivro);
        if (livro == null) {
            return "Livro não encontrado.";
        }

        if (!livro.isDisponivel()) {
            return "Livro não está disponível.";
        }

        Emprestimo novo = new Emprestimo(idEmprestimo, user, livro, dataEmprestimo, null, false);

        emprestimoRepo.adicionarEmprestimo(novo);
        livro.setDisponivel(false);

        return "Livro emprestado com sucesso!";
    }

    // Devolver livro
    public String devolverLivro(int idEmprestimo, String dataDev) {

        Emprestimo emp = emprestimoRepo.procurarPorId(idEmprestimo);

        if (emp == null) {
            return "Empréstimo não encontrado.";
        }

        if (emp.isDevolvido()) {
            return "Este empréstimo já foi devolvido.";
        }

        emp.setDataDevolucao(dataDev);
        emp.setDevolvido(true);
        emp.getLivro().setDisponivel(true);

        return "Livro devolvido com sucesso!";
    }

    // Listar empréstimos ativos
    public String listarAtivos() {
        var lista = emprestimoRepo.listarAtivos();

        if (lista.isEmpty()) {
            return "Não existem empréstimos ativos.";
        }

        String txt = "Empréstimos ativos:\n";
        for (Emprestimo e : lista) {
            txt += e.toString() + "\n";
        }
        return txt;
    }

    // Listar todos
    public String listarTodos() {
        var lista = emprestimoRepo.listarEmprestimos();

        if (lista.isEmpty()) {
            return "Não existem empréstimos registados.";
        }

        String txt = "Histórico de empréstimos:\n";
        for (Emprestimo e : lista) {
            txt += e.toString() + "\n";
        }
        return txt;
    }
}
