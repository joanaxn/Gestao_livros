package repository;

import models.Emprestimo;

import java.util.ArrayList;

public class EmprestimoRepository {

    private ArrayList<Emprestimo> emprestimos;

    public EmprestimoRepository() {
        this.emprestimos = new ArrayList<>();
    }

    // adicionar empréstimo
    public void adicionarEmprestimo(Emprestimo emp) {
        emprestimos.add(emp);
    }

    // procurar por ID
    public Emprestimo procurarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getIdEmprestimo() == id) {
                return e;
            }
        }
        return null;
    }

    // listar todos
    public ArrayList<Emprestimo> listarEmprestimos() {
        return new ArrayList<>(emprestimos);
    }

    // listar apenas ativos
    public ArrayList<Emprestimo> listarAtivos() {
        ArrayList<Emprestimo> ativos = new ArrayList<>();

        for (Emprestimo e : emprestimos) {
            if (!e.isDevolvido()) {
                ativos.add(e);
            }
        }

        return ativos;
    }
}
