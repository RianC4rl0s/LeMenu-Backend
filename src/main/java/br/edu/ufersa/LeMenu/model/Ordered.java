package br.edu.ufersa.LeMenu.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="tb_ordered")
@PrimaryKeyJoinColumn(name="id_produto")
public class Ordered extends Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	private String description;
	
	@ManyToMany(mappedBy = "cart")
	private Set<OrderingTable> orderedTable;
	
	public Ordered(){};
	public Ordered(Long id, String status, String description) {
		this.id = id;
		this.status = status;
		this.description = description;
	}
	
	public Ordered(Ordered model) {
		this.id = model.getId();
		this.status = model.getStatus();
		this.description = model.getDescription();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<OrderingTable> getOrderedTable() {
		return orderedTable;
	}
	public void setOrderedTable(Set<OrderingTable> orderedTable) {
		this.orderedTable = orderedTable;
	}
	
	
	
	
}
