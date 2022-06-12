package br.edu.ufersa.LeMenu.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.edu.ufersa.LeMenu.Dto.OTableDto;
import br.edu.ufersa.LeMenu.Dto.OrderedDto;
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
	public ResponseEntity<OTableDto> findById(@Param("id") Long id) {
		OTableDto odto = new OTableDto(service.findById(id).get());
		Optional<OTableDto> oo = Optional.of(odto);
		return oo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/list/order")
	public List<OrderedDto> listOrderedCart(@Param("id") Long id) {
		Optional<OrderingTable> ot = service.findById(id);
		if(ot.isEmpty()) {
			return null;
		}else {
			ArrayList<Ordered> ao = new ArrayList<>(ot.get().getCart());
			ArrayList<OrderedDto> odto = new ArrayList<OrderedDto>();
			for(int i= 0; i < ao.size();i++) {
				OrderedDto temp = new OrderedDto( ao.get(i));
				OTableDto tdto = new OTableDto(ao.get(i).getOrderedTable());
				temp.setOrderedTable(tdto);
				odto.add(temp);
			}
			
			return odto;
		}
		
	}
	@GetMapping("/search/by-code")
	public ResponseEntity<OTableDto> findByCode(@Param("code") String code) {
		OTableDto odto = new OTableDto(service.findByCode(code).get());
		Optional<OTableDto> oo = Optional.of(odto);
		return oo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		//return service.findByCode(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search/all")
	public List<OTableDto> findAll() {
		ArrayList<OrderingTable> o = new ArrayList<>(service.findAll());
		ArrayList<OTableDto> oo = new ArrayList<>();
		for(int i = 0; i< o.size();i++) {
			oo.add(new OTableDto(o.get(i)));
		}
		
		return oo;
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
	@PutMapping("/close")
	public ResponseEntity<Long> closeTable(@Param("id") Long id) {
		
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
	@PutMapping("/open")
	public ResponseEntity<Long> openTable(@Param("id") Long id) {
		
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
	@PutMapping("/add-order")
	public ResponseEntity<Long> addOrder(@Param("id") Long id, @RequestBody Ordered o) {
		
		OrderingTable tbTemp = tableRepo.findById(id).orElse(null);
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			//tbTemp.setCode(tb.getCode());
			//tbTemp.setIsOpen(tb.getIsOpen());
			System.out.println(o.toString());
			tbTemp.getCart().add(o);
			o.setOrderedTable(tbTemp);
			System.out.println(o.toString());
			var tbTemp2 = tableRepo.save(tbTemp);
			if (tbTemp2 == null) {
				
				return ResponseEntity.badRequest().build();
			} else {
				System.out.println(tbTemp2.toString());
				return ResponseEntity.status(HttpStatus.CREATED).body(tbTemp2.getId());
			}
		}
	}
	@PutMapping("/remove-order")
	public ResponseEntity<Long> removeOrder(@Param("id") Long id, @RequestBody Ordered o) {
		
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
