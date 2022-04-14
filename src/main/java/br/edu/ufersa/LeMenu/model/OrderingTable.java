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

@Entity	
@Table(name="tb_ordering_table")
public class OrderingTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	
	@ManyToMany
	@JoinTable(
	  name = "order_cart", 
	  joinColumns = @JoinColumn(name = "ordering_table_id"), 
	  inverseJoinColumns = @JoinColumn(name = "ordered_id"))
	private Set<Ordered> cart;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	private boolean isOpen = false;
	// Constructors

	public OrderingTable() {
	}

	public OrderingTable(Long id, String code, Set<Ordered> cart, Client client) {
		this.id = id;
		this.code = code;
		this.cart = cart;
		this.client = client;
	}

	public OrderingTable(String code, Client client) {

		this.code = code;
		this.client = client;
	}
	
	public OrderingTable(OrderingTable model) {
		this.id = model.getId();
		this.code = model.getcode();
		this.cart = model.getCart();
		this.client = model.getClient();
	}

	// Methods
	public void openTable(Long id) {
		isOpen =true;
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

	
	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
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
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}
