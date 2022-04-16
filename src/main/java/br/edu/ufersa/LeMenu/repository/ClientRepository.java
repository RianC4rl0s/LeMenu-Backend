package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	public Optional<Client> findById (Long id);
	public Optional<Client> findByName (String name);
}
