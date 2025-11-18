package gestao_biblioteca.controller;

import gestao_biblioteca.dto.DevolucaoRequest;
import gestao_biblioteca.dto.EmprestimoRequest;
import gestao_biblioteca.models.Emprestimo;
import gestao_biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/emprestar")
    public String emprestarLivro(@RequestBody EmprestimoRequest request) {
        return emprestimoService.emprestarLivro(
                request.userId,
                request.titulo,
                request.dataEmprestimo,
                request.idEmprestimo
        );
    }

    @PostMapping("/devolver")
    public String devolverLivro(@RequestBody DevolucaoRequest request) {
        return emprestimoService.devolverLivro(
                request.idEmprestimo,
                request.dataDevolucao
        );
    }

    @GetMapping("/listarAtivos")
    public ArrayList<Emprestimo> listarAtivos() {
        return emprestimoService.listarAtivos();
    }

    @GetMapping("/listarTodos")
    public ArrayList<Emprestimo> listarTodos() {
        return emprestimoService.listarTodos();
    }
}
