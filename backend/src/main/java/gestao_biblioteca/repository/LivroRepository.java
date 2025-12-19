package gestao_biblioteca.repository;

import gestao_biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByTituloIgnoreCase(String titulo);

    Livro findByIsbn(String isbn);

    List<Livro> findByAutorIgnoreCase(String autor);

    List<Livro> findByDisponivelTrue();
}
