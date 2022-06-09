package br.edu.ufersa.LeMenu.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	@GetMapping("/list/order")
	public Set<Ordered> listOrderedCart(@Param("id") Long id) {
		Optional<OrderingTable> ot = service.findById(id);
		if(ot.isEmpty()) {
			return null;
		}else {
			return ot.get().getCart();
		}
		
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
	public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody OrderingTable tb) {
		
		OrderingTable tbTemp = tableRepo.findById(id).get();
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			tbTemp.setCode(tb.getCode());
			tbTemp.setIsOpen(tb.getIsOpen());
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
	@PutMapping("/close/{id}")
	public ResponseEntity<Long> closeTable(@PathVariable Long id) {
		
		OrderingTable tbTemp = tableRepo.findById(id).get();
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			tbTemp.setIsOpen(false);
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
	@PutMapping("/open/{id}")
	public ResponseEntity<Long> openTable(@PathVariable Long id) {
		
		OrderingTable tbTemp = tableRepo.findById(id).get();
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			tbTemp.setIsOpen(true);
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
	@PutMapping("/add-order/{id}")
	public ResponseEntity<Long> addOrder(@PathVariable Long id, @RequestBody Ordered o) {
		
		OrderingTable tbTemp = tableRepo.findById(id).orElse(null);
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			//tbTemp.setCode(tb.getCode());
			//tbTemp.setIsOpen(tb.getIsOpen());
			tbTemp.getCart().add(o);
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
	@PutMapping("/remove-order/{id}")
	public ResponseEntity<Long> removeOrder(@PathVariable Long id, @RequestBody Ordered o) {
		
		OrderingTable tbTemp = tableRepo.findById(id).get();
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			//tbTemp.setCode(tb.getCode());
			//tbTemp.setIsOpen(tb.getIsOpen());
			tbTemp.getCart().removeIf(element -> (element.getDescription().equals(o.getDescription())));
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
}
