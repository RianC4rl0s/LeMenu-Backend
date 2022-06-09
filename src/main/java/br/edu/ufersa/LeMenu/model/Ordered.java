package br.edu.ufersa.LeMenu.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_ordered")
public class Ordered {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToMany(mappedBy = "cart")
	private Set<OrderingTable> orderedTable;
	
	public Ordered(){};
	public Ordered(Long id, String status, String description,Product product) {
		this.id = id;
		this.status = status;
		this.description = description;
		this.product = product;
	}
	
	public Ordered(Ordered model) {
		this.id = model.getId();
		this.status = model.getStatus();
		this.description = model.getDescription();
		this.product = model.getProduct();
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
