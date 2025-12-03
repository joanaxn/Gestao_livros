package gestao_biblioteca.dto;

public class DevolucaoRequest {

    private Long idEmprestimo;
    private String dataDevolucao;

    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }
}
