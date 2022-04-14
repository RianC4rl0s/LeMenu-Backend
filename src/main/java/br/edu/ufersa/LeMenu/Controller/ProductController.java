package br.edu.ufersa.LeMenu.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.LeMenu.model.Ordered;
import br.edu.ufersa.LeMenu.model.Product;
import br.edu.ufersa.LeMenu.repository.ProductRepository;
import br.edu.ufersa.LeMenu.service.ProductServices;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServices service;
	
	private ProductRepository proRepo;
	
	@GetMapping("/search/by-id")
	public ResponseEntity<Product> findById (@Param("id") Long id){
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search/by-name")
	public ResponseEntity<Product> findByName (@Param("name") String name){
		return service.findByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search/all")
	public List<Product> findAll (){
		return service.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return proRepo.findById(id)
				.map(product -> {
					proRepo.delete(product);
					return ResponseEntity.ok("OK");
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/new") 
	public ResponseEntity<Long> save (@ModelAttribute Product model){	
		var productTemp = proRepo.save(model);	
		
		if(productTemp == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(productTemp.getId());			
		}
	}
	
	@PutMapping("/update/{id}") 
	public ResponseEntity<Ordered> update (@PathVariable Long id){
		return null;
	}
}
