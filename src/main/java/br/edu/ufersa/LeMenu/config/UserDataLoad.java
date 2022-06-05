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
			Role adminRole2 = new Role();
			adminRole2.setName("ROLE_SUPER_ADMIN");
			roleRepository.save(adminRole2);
			
			Role clerk = new Role();
			clerk.setName("ROLE_ATTENDANT");
			roleRepository.save(clerk);
			Role clerk2 = new Role();
			clerk2.setName("ROLE_SUPER_ATTENDANT");
			roleRepository.save(clerk2);
			System.out.println("Roles created");
		}		
	}
	
	private void loadUser() {
		if (userRepository.count() == 0) {
			User user = new User();
			user.setLogin("admin@lemenu.com");
			user.setPassword("senha123");
			
			Role roleAdmin = roleRepository.findByName("ROLE_SUPER_ADMIN")
					.orElseThrow(() -> new RuntimeException("Role don't exists"));
			
			user.addRole(roleAdmin);			
//			userRepository.save(user);
			userService.save(user);
//			log.info("Default admin created");
			System.out.println("Default admin created");
			
			User userAttendant = new User();
			userAttendant.setLogin("attendant@lemenu.com");
			userAttendant.setPassword("senha123");
			userAttendant.setName("Teste");
			
			Role roleAttendant = roleRepository.findByName("ROLE_SUPER_ATTENDANT")
					.orElseThrow(() -> new RuntimeException("Role don't exists"));
			
			userAttendant.addRole(roleAttendant);			
//			userRepository.save(user);
			userService.save(userAttendant);
//			log.info("Default admin created");
			System.out.println("Default attendant created");
		}
	}
}
