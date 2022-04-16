package br.edu.ufersa.LeMenu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.OrderingTable;
import br.edu.ufersa.LeMenu.repository.OrderingTableRepository;

@Service
public class TableServices {
	
	@Autowired
	private OrderingTableRepository tableRepo;
	
	public Optional<OrderingTable> findById (Long id){
		return tableRepo.findById(id);	
	}
	
	public Optional<OrderingTable> findByCode (String code) {
		return tableRepo.findByCode(code);
	}
	
	public List<OrderingTable> findAll() {
		return tableRepo.findAll().stream().map(OrderingTable::new).collect(Collectors.toList());
	}
}
