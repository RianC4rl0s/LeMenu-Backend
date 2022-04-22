package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.Clerk;

public interface ClerkRepository extends JpaRepository<Clerk, Long>{
	public Optional<Clerk> findById (Long id);
}
