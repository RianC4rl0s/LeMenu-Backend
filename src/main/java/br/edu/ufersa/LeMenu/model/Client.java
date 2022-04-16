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
	private String nome;
	@OneToOne(mappedBy = "client")
	private OrderingTable table;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public OrderingTable getTable() {
		return table;
	}
	public void setTable(OrderingTable table) {
		this.table = table;
	}
	
	
}
