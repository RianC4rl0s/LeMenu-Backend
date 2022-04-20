package br.edu.ufersa.LeMenu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.LeMenu.model.Clerk;
import br.edu.ufersa.LeMenu.repository.ClerkRepository;
import br.edu.ufersa.LeMenu.service.ClerkServices;

@RestController
@RequestMapping("/clerk")
public class ClerkController {
	
	@Autowired
	private ClerkServices service;
	
	@Autowired ClerkRepository repo;

	@GetMapping("/search/all")
	public List<Clerk> findAll() {
		return service.findAll();
	}
	
	@PostMapping("/new")
	public ResponseEntity<Long> save(@RequestBody Clerk model) {
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
			return new ResponseEntity<>("",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody Clerk model) {
		
		Clerk userTemp = repo.findById(id).get();
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
