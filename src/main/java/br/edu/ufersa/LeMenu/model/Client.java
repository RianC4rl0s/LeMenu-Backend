package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

//@Entity
@Entity
//@Table(name="tb_client")
@PrimaryKeyJoinColumn(name="id_user")
public class Client extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "client")
	private OrderingTable table;
}
