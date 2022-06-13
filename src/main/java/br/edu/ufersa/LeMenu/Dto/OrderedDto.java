package br.edu.ufersa.LeMenu.Dto;

import br.edu.ufersa.LeMenu.model.Ordered;
import br.edu.ufersa.LeMenu.model.Product;

public class OrderedDto {
	private Long id;

	private String status;
	private String description;
	private Product product;
	private OTableDto orderedTable;
	
	
	
	public OrderedDto(Long id, String status, String description, Product product, OTableDto orderedTable) {
		this.id = id;
		this.status = status;
		this.description = description;
		this.product = product;
		this.orderedTable = orderedTable;
	}
	public OrderedDto(Ordered o) {
		this.id = o.getId();
		this.status = o.getStatus();
		this.description = o.getDescription();
		this.product = o.getProduct();
		//this.orderedTable = new OTableDto(o.getOrderedTable());
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public OTableDto getOrderedTable() {
		return orderedTable;
	}
	public void setOrderedTable(OTableDto orderedTable) {
		this.orderedTable = orderedTable;
	}
	
	
	
}
