package gestao_biblioteca.models;

public class Emprestimo {
    private int idEmprestimo;
    private gestao_biblioteca.models.User user;
    private gestao_biblioteca.models.Livro livro;
    private String dataEmprestimo;
    private String dataDevolucao;
    private boolean devolvido;

    public Emprestimo(int idEmprestimo, gestao_biblioteca.models.User user, gestao_biblioteca.models.Livro livro, String dataEmprestimo, String dataDevolucao, boolean devolvido) {
        this.idEmprestimo = idEmprestimo;
        this.user = user;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }


    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public gestao_biblioteca.models.User getUser() {
        return user;
    }

    public void setUser(gestao_biblioteca.models.User user) {
        this.user = user;
    }

    public gestao_biblioteca.models.Livro getLivro() {
        return livro;
    }

    public void setLivro(gestao_biblioteca.models.Livro livro) {
        this.livro = livro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "idEmprestimo=" + idEmprestimo +
                ", user=" + user +
                ", livro=" + livro +
                ", dataEmprestimo='" + dataEmprestimo + '\'' +
                ", dataDevolucao='" + dataDevolucao + '\'' +
                ", devolvido=" + devolvido +
                '}';
    }
}
