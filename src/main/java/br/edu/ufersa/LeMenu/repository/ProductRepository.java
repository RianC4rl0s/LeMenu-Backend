package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	public Optional<Product> findById (Long id);
	public Optional<Product> findByName (String name);
}
