package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Product;
import br.edu.ufersa.LeMenu.repository.ProductRepository;

@Service
public class ProductServices {

	@Autowired
	private ProductRepository proRepo;
	
	public Optional<Product> findById (Long id){
		return proRepo.findById(id);	
	}
	
	public Optional<Product> findByName (String name) {
		return proRepo.findByName(name);
	}
	
	public List<Product> findAll() {
		return proRepo.findAll().stream().map(Product::new).collect(Collectors.toList());
	}
}
