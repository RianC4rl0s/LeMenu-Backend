package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
@Entity
@Table(name="tb_client")
//@PrimaryKeyJoinColumn(name="id_user")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(mappedBy = "client")
	private OrderingTable table;
	
	
	public Client() {}
	public Client(Client model) {
		this.id = model.getId();
		this.name = model.getName();
		this.table = model.getTable();
	}
	public Client(Long id, String name, OrderingTable table) {
		this.id = id;
		this.name = name;
		this.table = table;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OrderingTable getTable() {
		return table;
	}
	public void setTable(OrderingTable table) {
		this.table = table;
	}
	
	
}
