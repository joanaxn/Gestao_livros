package gestao_biblioteca.repository;

import gestao_biblioteca.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // listar todos os empréstimos ainda não devolvidos
    List<Emprestimo> findByDevolvidoFalse();

    // listar todos os empréstimos de um user específico
    List<Emprestimo> findByUserId(Long userId);
}
