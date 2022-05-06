package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.User;
import br.edu.ufersa.LeMenu.repository.ClerkRepository;

@Service
public class ClerkServices {

	@Autowired
	private ClerkRepository userRepo;
	
	public Optional<User> findById(Long id){
		return userRepo.findById(id);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
}
