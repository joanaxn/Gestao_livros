package gestao_biblioteca.repository;

import gestao_biblioteca.models.Emprestimo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmprestimoRepository {

    private ArrayList<Emprestimo> emprestimos;

    public EmprestimoRepository() {
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emp) {
        emprestimos.add(emp);
    }

    public Emprestimo procurarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getIdEmprestimo() == id) return e;
        }
        return null;
    }

    public ArrayList<Emprestimo> listarTodos() {
        return new ArrayList<>(emprestimos);
    }

    public ArrayList<Emprestimo> listarAtivos() {
        ArrayList<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (!e.isDevolvido()) ativos.add(e);
        }
        return ativos;
    }
}
