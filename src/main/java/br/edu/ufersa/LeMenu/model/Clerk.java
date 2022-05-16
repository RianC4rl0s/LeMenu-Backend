package br.edu.ufersa.LeMenu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Clerk extends User{
	
	@Column(unique = true)
	private String cpf;
	@Column(unique = true)
	private String phone;
	
	public Clerk() {}
	
	public Clerk(User model) {
		super(model.getId(), model.getName(),model.getLogin(),model.getPassword(),model.getRoles(),model.isActive());
	}
	
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Clerk(Long id, String name, String login, String password, List<Role> roles, boolean active) {
		super(id, name, login, password, roles, active);
		// TODO Auto-generated constructor stub
	}

	public String getCpf() {
		return cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
