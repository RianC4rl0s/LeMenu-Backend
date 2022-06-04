package br.edu.ufersa.LeMenu.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_ordering_table")
public class OrderingTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private boolean isOpen;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "order_cart", joinColumns = @JoinColumn(name = "ordering_table_id"), inverseJoinColumns = @JoinColumn(name = "ordered_id"))
	private Set<Ordered> cart;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	// Constructors

	public OrderingTable() {
	}

	public OrderingTable(Long id, String code, Set<Ordered> cart, Client client,boolean isOpen) {
		this.id = id;
		this.code = code;
		this.cart = cart;
		this.client = client;
		this.isOpen = isOpen;
	}

	public OrderingTable(String code, Client client) {

		this.code = code;
		this.client = client;
	}
	public OrderingTable(String code, boolean isOpen) {

		this.code = code;
		this.isOpen = isOpen;
	}
	public OrderingTable(OrderingTable model) {
		this.id = model.getId();
		this.code = model.getCode();
		this.cart = model.getCart();
		this.client = model.getClient();
		this.isOpen = model.getIsOpen();
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Ordered> getCart() {
		return cart;
	}

	public void setCart(Set<Ordered> cart) {
		this.cart = cart;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	// Methods
	public void openTable(Long id) {
		isOpen = true;
	}

	public void openTable(String code) {
		isOpen = true;
	}

	public void closeTable(Long id, Client cliend) {
		isOpen = false;
	}

	public void closeTable(String code, Client cliend) {
		isOpen = false;
	}

}
