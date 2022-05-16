package br.edu.ufersa.LeMenu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.Role;
import br.edu.ufersa.LeMenu.model.User;
import br.edu.ufersa.LeMenu.repository.RoleRepository;
import br.edu.ufersa.LeMenu.repository.UserRepository;
import br.edu.ufersa.LeMenu.service.UserService;

@Service
public class UserDataLoad implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository; 
	
	@Autowired
	UserRepository userRepository;
	

	@Autowired
	UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadRoles();
		loadUser();
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
	
	private void loadUser() {
		if (userRepository.count() == 0) {
			User user = new User();
			user.setLogin("admin@lemenu.com");
			user.setPassword("senha123");
			
			Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
					.orElseThrow(() -> new RuntimeException("Role don't exists"));
			
			user.addRole(roleAdmin);			
//			userRepository.save(user);
			userService.save(user);
//			log.info("Default admin created");
			System.out.println("Default admin created");
		}
	}
}
