package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.Ordered;

public interface OrderedRepository extends JpaRepository<Ordered, Long>{
	
	public Optional<Ordered> findById (Long id);
	public Optional<Ordered> findByStatus (String status);
}
