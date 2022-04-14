package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Ordered;
import br.edu.ufersa.LeMenu.repository.OrderedRepository;

@Service
public class OrderedServices {

	@Autowired
	private OrderedRepository ordRepo;
	
	public Optional<Ordered> findById (Long id){
		return ordRepo.findById(id);	
	}
	
	public Optional<Ordered> findByStatus (String status) {
		return ordRepo.findByStatus(status);
	}
	
	public List<Ordered> findAll() {
		return ordRepo.findAll().stream().map(Ordered::new).collect(Collectors.toList());
	}
}
