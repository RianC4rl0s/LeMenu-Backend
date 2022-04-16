package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.LeMenu.model.OrderingTable;

public interface OrderingTableRepository extends JpaRepository<OrderingTable, Long>{
	public Optional<OrderingTable> findById (Long id);
	public Optional<OrderingTable> findByCode (String tableCode);
}
