package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity	
@Table(name="tb_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double sale;
	private String description;
	@Lob
	private String image;
	private boolean isOnMenu;
	
	
	
	public Product(){}
	public Product(String name, double price, double sale, String image, boolean isOnMenu, String description) {
		this.name = name;
		this.price = price;
		this.sale = sale;
		this.image = image;
		this.isOnMenu = isOnMenu;
		this.description = description;
	}
	public Product(Long id, String name, double price, double sale, String image,boolean isOnMenu, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.sale = sale;
		this.image = image;
		this.isOnMenu= isOnMenu;
		this.description= description;
	}
	public Product(Product model) {
		this.id = model.getId();
		this.name = model.getName();
		this.price = model.getPrice();
		this.sale = model.getSale();
		this.image = model.getImage();
		this.isOnMenu= model.getIsOnMenu();
		this.description= model.getDescription();
		
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
	public void setPrice(double price) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getIsOnMenu() {
		return isOnMenu;
	}
	public void setIsOnMenu(boolean isOnMenu) {
		this.isOnMenu = isOnMenu;
	}
	
	
	
	
}
