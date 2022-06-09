package br.edu.ufersa.LeMenu.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.LeMenu.model.Clerk;
import br.edu.ufersa.LeMenu.model.Role;
import br.edu.ufersa.LeMenu.model.User;
import br.edu.ufersa.LeMenu.repository.RoleRepository;
import br.edu.ufersa.LeMenu.repository.UserRepository;
import br.edu.ufersa.LeMenu.service.UserServiceImpl;

@RestController
@RequestMapping("/clerk")
public class ClerkController {

	@Autowired
	private UserServiceImpl service;

	@Autowired
	UserRepository repo;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;
	@GetMapping("/search/all")
	public List<User> findAll() {
		Role roleAttendant = roleRepository.findByName("ROLE_ATTENDANT")
				.orElseThrow(() -> new RuntimeException("Role don't exists"));
		List<User> temp = service.findAll();
		List<User> users =  new ArrayList<>();
		for(int i = 0 ; i< temp.size();i++) {
			if(temp.get(i).getRoles().contains(roleAttendant)) {
				users.add(temp.get(i));
			}
		}
		return users;
	}
	
	@GetMapping("/search/alladm")
	public List<User> findAllAdm() {
		Role roleAttendant = roleRepository.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new RuntimeException("Role don't exists"));
		List<User> temp = service.findAll();
		List<User> users =  new ArrayList<>();
		for(int i = 0 ; i< temp.size();i++) {
			if(temp.get(i).getRoles().contains(roleAttendant)) {
				users.add(temp.get(i));
			}
		}
		return users;
	}

	@PostMapping("/new")
	public ResponseEntity<Long> save(@RequestBody Clerk model) {
		Role roleAttendant = roleRepository.findByName("ROLE_ATTENDANT")
				.orElseThrow(() -> new RuntimeException("Role don't exists"));
		model.addRole(roleAttendant);
		
		String encodedPassword = encoder.encode(model.getPassword());
		model.setPassword(encodedPassword);
		
		var userTemp = repo.save(model);

		if (userTemp == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(userTemp.getId());
		}
	}
	@PostMapping("/newadm")
	public ResponseEntity<Long> saveAdm(@RequestBody Clerk model) {
		Role roleAttendant = roleRepository.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new RuntimeException("Role don't exists"));
		model.addRole(roleAttendant);
		
		String encodedPassword = encoder.encode(model.getPassword());
		model.setPassword(encodedPassword);
		
		var userTemp = repo.save(model);

		if (userTemp == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(userTemp.getId());
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>("", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody Clerk model) {

		Clerk userTemp = (Clerk) repo.findById(id).get();
		if (userTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			if (model.getName() != null && !model.getName().equals("")) {
				userTemp.setName(model.getName());
			}
			if (model.getLogin() != null && !model.getLogin().equals("")) {
				userTemp.setLogin(model.getLogin());
			}
			if (model.getPassword() != null && !model.getPassword().equals("")) {
				userTemp.setPassword(model.getPassword());
			}
			if (model.getCpf() != null && !model.getCpf().equals("")) {
				userTemp.setCpf(model.getCpf());
			}
			if (model.getPhone() != null && !model.getPhone().equals("")) {
				userTemp.setPhone(model.getPhone());
			}
			var temp2 = repo.save(userTemp);
			if (temp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(temp2.getId());
			}
		}
	}
}