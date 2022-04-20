package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Clerk;
import br.edu.ufersa.LeMenu.repository.ClerkRepository;

@Service
public class ClerkServices {

	@Autowired
	private ClerkRepository userRepo;
	
	public Optional<Clerk> findById(Long id){
		return userRepo.findById(id);
	}
	
	public List<Clerk> findAll(){
		return userRepo.findAll();
	}
}
