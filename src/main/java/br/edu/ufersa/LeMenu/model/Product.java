package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity	
@Table(name="tb_product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private double sale;
	private String image;
	
	
	
	
	public Product(){}
	public Product(Long id, String name, Double price, double sale, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.sale = sale;
		this.image = image;
	}
	
	public Product(Product model) {
		this.id = model.getId();
		this.name = model.getName();
		this.price = model.getPrice();
		this.sale = model.getSale();
		this.image = model.getImage();
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double sale) {
		this.sale = sale;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
