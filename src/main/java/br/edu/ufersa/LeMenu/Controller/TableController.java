package br.edu.ufersa.LeMenu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import br.edu.ufersa.LeMenu.model.Ordered;
import br.edu.ufersa.LeMenu.model.OrderingTable;
import br.edu.ufersa.LeMenu.repository.OrderingTableRepository;
import br.edu.ufersa.LeMenu.service.TableServices;

@RestController
@RequestMapping("/table")
public class TableController {

	@Autowired
	private TableServices service;
	@Autowired
	private OrderingTableRepository tableRepo;

	@GetMapping("/search/by-id")
	public ResponseEntity<OrderingTable> findById(@Param("id") Long id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search/by-code")
	public ResponseEntity<OrderingTable> findByCode(@Param("code") String code) {
		return service.findByCode(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search/all")
	public List<OrderingTable> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return tableRepo.findById(id).map(table -> {
			tableRepo.delete(table);
			return ResponseEntity.ok("OK");
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/new")
	public ResponseEntity<Long> save(@RequestBody OrderingTable model) {
		var tableTemp = tableRepo.save(model);
		

		if (tableTemp == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(tableTemp.getId());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Ordered> update(@PathVariable Long id, @RequestBody OrderingTable tb) {

		return null;
	}
}
