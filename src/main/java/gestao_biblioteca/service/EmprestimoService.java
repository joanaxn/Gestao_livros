package gestao_biblioteca.service;

import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.models.Livro;
import gestao_biblioteca.models.User;
import gestao_biblioteca.repository.EmprestimoRepository;
import gestao_biblioteca.repository.LivroRepository;
import gestao_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepo;
    private final LivroRepository livroRepo;
    private final UserRepository userRepo;

    private int nextEmprestimoId = 1;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepo,
                             LivroRepository livroRepo,
                             UserRepository userRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
        this.userRepo = userRepo;
    }

    public String emprestarLivro(int userId, String titulo, String dataEmprestimo) {

        User user = userRepo.procurarPorId(userId);
        if (user == null) return "User não encontrado.";

        Livro livro = livroRepo.procurarPorTitulo(titulo);
        if (livro == null) return "Livro não encontrado.";

        if (!livro.isDisponivel()) return "Livro indisponível.";

        // limitar 3 ativos
        long ativos = user.getLivrosemprestimos()
                .stream()
                .filter(e -> !e.isDevolvido())
                .count();

        if (ativos >= 3) return "O utilizador já atingiu o limite de 3 empréstimos.";

        Emprestimo emp = new Emprestimo(
                nextEmprestimoId++,
                user,
                livro,
                dataEmprestimo,
                null,
                false
        );

        // adicionar ao repositório
        emprestimoRepo.adicionarEmprestimo(emp);

// criar cópia SEM user para guardar dentro do user
        Emprestimo copia = new Emprestimo(
                emp.getIdEmprestimo(),
                null,                           // <-- TIRA O USER !!!!
                emp.getLivro(),
                emp.getDataEmprestimo(),
                emp.getDataDevolucao(),
                emp.isDevolvido()
        );

// guardar cópia no user (SEM CICLO)
        user.getLivrosemprestimos().add(copia);

// livro fica indisponível
        livro.setDisponivel(false);

        return "Empréstimo registado!";
    }



    public String devolverLivro(int idEmprestimo, String dataDevolucao) {

        Emprestimo emp = emprestimoRepo.procurarPorId(idEmprestimo);

        if (emp == null) return "Empréstimo não encontrado.";
        if (emp.isDevolvido()) return "Este empréstimo já foi devolvido.";

        // marcar o emprestimo original como devolvido
        emp.setDataDevolucao(dataDevolucao);
        emp.setDevolvido(true);

        // o livro fica disponível novamente
        emp.getLivro().setDisponivel(true);

        // AGORA O PASSO IMPORTANTE: atualizar a cópia dentro do user
        User user = emp.getUser();
        if (user != null && user.getLivrosemprestimos() != null) {
            for (Emprestimo e : user.getLivrosemprestimos()) {
                if (e.getIdEmprestimo() == idEmprestimo) {
                    e.setDevolvido(true);
                    e.setDataDevolucao(dataDevolucao);
                    break;
                }
            }
        }

        return "Livro devolvido!";
    }
}