package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Client;
import br.edu.ufersa.LeMenu.repository.ClientRepository;

@Service
public class ClientServices {
	
	@Autowired
	private ClientRepository clientRepo;
	
	public Optional<Client> findById (Long id){
		return clientRepo.findById(id);	
	}
	
	public Optional<Client> findByName (String name) {
		return clientRepo.findByName(name);
	}
	public List<Client> findAll() {
		return clientRepo.findAll().stream().map(Client::new).collect(Collectors.toList());
	}
}
