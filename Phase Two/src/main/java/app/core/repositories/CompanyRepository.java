package app.core.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByEmailOrNameIgnoreCase(String email, String name);

	Optional<Company> findByEmailAndPassword(String email, String password);

	Company getById(Long id);

}
