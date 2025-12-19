package gestao_biblioteca.dto;

public class EmprestimoRequest {

    private Long userId;
    private String titulo;
    private String dataEmprestimo;

    public Long getUserId() {
        return userId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }
}
