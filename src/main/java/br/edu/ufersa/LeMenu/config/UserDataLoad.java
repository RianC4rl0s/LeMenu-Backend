package br.edu.ufersa.LeMenu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Role;
import br.edu.ufersa.LeMenu.repository.RoleRepository;
import br.edu.ufersa.LeMenu.repository.UserRepository;

@Service
public class UserDataLoad implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository; 
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadRoles();
	}
	
	
	private void loadRoles() {		
		System.out.println(roleRepository.count());
		if(roleRepository.count() == 0) {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
			
			Role estRole = new Role();
			estRole.setName("ROLE_ESTABLISHMENT");
			roleRepository.save(estRole);
			System.out.println("Roles created");
		}		
	}
}
