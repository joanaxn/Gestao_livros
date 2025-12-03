package gestao_biblioteca.controller;

import gestao_biblioteca.dto.DevolucaoRequest;
import gestao_biblioteca.dto.EmprestimoRequest;
import gestao_biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // Criar novo empréstimo
    @PostMapping("/emprestar")
    public String emprestarLivro(@RequestBody EmprestimoRequest request) {

        return emprestimoService.emprestarLivro(
                request.getUserId(),
                request.getTitulo(),
                request.getDataEmprestimo()
        );
    }

    // Devolver livro
    @PostMapping("/devolver")
    public String devolverLivro(@RequestBody DevolucaoRequest request) {

        return emprestimoService.devolverLivro(
                request.getIdEmprestimo(),
                request.getDataDevolucao()
        );
    }
}
