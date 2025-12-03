package gestao_biblioteca.repository;

import gestao_biblioteca.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // JPA cria automaticamente esta query com base no nome do método
    User findByEmail(String email);
}
