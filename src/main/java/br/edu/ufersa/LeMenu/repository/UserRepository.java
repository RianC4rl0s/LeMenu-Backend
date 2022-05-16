package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ufersa.LeMenu.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.login = ?1 AND u.active = true")
	Optional<User> findByEmail(String email);
}
