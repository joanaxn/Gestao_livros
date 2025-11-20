package gestao_biblioteca.service;

import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.models.Livro;
import gestao_biblioteca.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestao_biblioteca.repository.EmprestimoRepository;
import gestao_biblioteca.repository.LivroRepository;
import gestao_biblioteca.repository.UserRepository;

import java.util.ArrayList;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepo;
    private final LivroRepository livroRepo;
    private final UserRepository userRepo;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepo, LivroRepository livroRepo, UserRepository userRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
        this.userRepo = userRepo;
    }

    public String emprestarLivro(int userId, String titulo, String dataEmprestimo, int idEmprestimo) {

        User user = userRepo.procurarPorId(userId);
        if (user == null) return "User não encontrado.";

        Livro livro = livroRepo.procurarPorTitulo(titulo);
        if (livro == null) return "Livro não encontrado.";

        if (!livro.isDisponivel()) return "Livro indisponível.";

        Emprestimo emp = new Emprestimo(idEmprestimo, user, livro, dataEmprestimo, null, false);

        emprestimoRepo.adicionarEmprestimo(emp);
        livro.setDisponivel(false);

        return "Empréstimo registado!";
    }

    public String devolverLivro(int idEmprestimo, String dataDevolucao) {

        Emprestimo emp = emprestimoRepo.procurarPorId(idEmprestimo);

        if (emp == null) return "Empréstimo não encontrado.";

        if (emp.isDevolvido()) return "Este empréstimo já foi devolvido.";

        emp.setDataDevolucao(dataDevolucao);
        emp.setDevolvido(true);
        emp.getLivro().setDisponivel(true);

        return "Livro devolvido!";
    }

    public ArrayList<Emprestimo> listarAtivos() {
        return emprestimoRepo.listarAtivos();
    }

    public ArrayList<Emprestimo> listarTodos() {
        return emprestimoRepo.listarTodos();
    }


    public String confirmarEmprestimo(int idEmprestimo) {

        Emprestimo emp = emprestimoRepo.procurarPorId(idEmprestimo);

        if (emp == null) {
            return "Empréstimo não encontrado.";
        }

        // Se já estiver devolvido não podes confirmar
        if (emp.isDevolvido()) {
            return "Este empréstimo já foi devolvido.";
        }

        // Marcar livro como indisponível
        Livro livro = emp.getLivro();
        livro.setDisponivel(false);

        return "Empréstimo confirmado!";
    }
}
