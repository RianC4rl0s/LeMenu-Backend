package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.User;

public interface ClerkRepository extends JpaRepository<User, Long>{
	public Optional<User> findById (Long id);
}
