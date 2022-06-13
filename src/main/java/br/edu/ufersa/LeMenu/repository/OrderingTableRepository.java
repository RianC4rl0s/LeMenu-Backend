package br.edu.ufersa.LeMenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.LeMenu.model.OrderingTable;

@Transactional
public interface OrderingTableRepository extends JpaRepository<OrderingTable, Long>{
	public Optional<OrderingTable> findById (Long id);
	public Optional<OrderingTable> findByCode (String tableCode);
}
