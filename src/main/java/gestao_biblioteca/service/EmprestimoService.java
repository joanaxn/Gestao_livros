package gestao_biblioteca.service;

import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.models.Livro;
import gestao_biblioteca.models.User;
import gestao_biblioteca.repository.EmprestimoRepository;
import gestao_biblioteca.repository.LivroRepository;
import gestao_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepo;
    private final LivroRepository livroRepo;
    private final UserRepository userRepo;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepo,
                             LivroRepository livroRepo,
                             UserRepository userRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
        this.userRepo = userRepo;
    }

    // -----------------------------------------
    //  REGISTAR EMPRÉSTIMO
    // -----------------------------------------
    public String emprestarLivro(Long userId, String titulo, String dataEmprestimo) {

        // procurar user na BD
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return "User não encontrado.";

        // procurar livro na BD
        Livro livro = livroRepo.findByTituloIgnoreCase(titulo);
        if (livro == null) return "Livro não encontrado.";

        if (!livro.isDisponivel()) return "Livro indisponível.";

        // limitar máximo de 3 empréstimos ativos
        List<Emprestimo> ativos = emprestimoRepo.findByUserId(userId)
                .stream()
                .filter(e -> !e.isDevolvido())
                .toList();

        if (ativos.size() >= 3) {
            return "O utilizador já atingiu o limite de 3 empréstimos.";
        }

        // criar empréstimo
        Emprestimo emp = new Emprestimo(
                user,
                livro,
                dataEmprestimo,
                null,
                false
        );

        // guardar no MySQL
        emprestimoRepo.save(emp);

        // marcar livro como indisponível
        livro.setDisponivel(false);
        livroRepo.save(livro);

        return "Empréstimo registado!";
    }


    // -----------------------------------------
    //  DEVOLVER LIVRO
    // -----------------------------------------
    public String devolverLivro(Long idEmprestimo, String dataDevolucao) {

        Emprestimo emp = emprestimoRepo.findById(idEmprestimo).orElse(null);

        if (emp == null) return "Empréstimo não encontrado.";
        if (emp.isDevolvido()) return "Este empréstimo já foi devolvido.";

        // atualizar estado
        emp.setDevolvido(true);
        emp.setDataDevolucao(dataDevolucao);
        emprestimoRepo.save(emp);

        // tornar livro disponível novamente
        Livro livro = emp.getLivro();
        livro.setDisponivel(true);
        livroRepo.save(livro);

        return "Livro devolvido!";
    }
}
