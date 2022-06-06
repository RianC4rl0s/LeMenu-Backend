package br.edu.ufersa.LeMenu.Controller;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.LeMenu.model.Ordered;
import br.edu.ufersa.LeMenu.model.OrderingTable;
import br.edu.ufersa.LeMenu.repository.OrderedRepository;
import br.edu.ufersa.LeMenu.service.OrderedServices;

@RestController
@RequestMapping("/ordered")
public class OrderedController {

	@Autowired
	private OrderedServices service;
	
	private OrderedRepository ordRepo;
	
	@GetMapping("/search/by-id")
	public ResponseEntity<Ordered> findById (@Param("id") Long id){
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search/by-status")
	public ResponseEntity<Ordered> findByStatus (@Param("status") String status){
		return service.findByStatus(status).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search/all")
	public List<Ordered> findAll (){
		return service.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return ordRepo.findById(id)
				.map(ordered -> {
					ordRepo.delete(ordered);
					return ResponseEntity.ok("OK");
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/new") 
	public ResponseEntity<Long> save (@ModelAttribute Ordered model){	
		var orderTemp = ordRepo.save(model);	
		
		if(orderTemp == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(orderTemp.getId());			
		}
	}
	
	@PutMapping("/update/{id}") 
	public ResponseEntity<Ordered> update (@PathVariable Long id, @RequestBody Ordered o){
		Ordered odTemp= ordRepo.findById(id).get();
		if (odTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			odTemp.setStatus(o.getStatus());
			odTemp.setDescription(o.getDescription());
			var odTemp2 = ordRepo.save(odTemp);
			if (odTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(odTemp2);
			}
		}
		//return null;
	}
}
