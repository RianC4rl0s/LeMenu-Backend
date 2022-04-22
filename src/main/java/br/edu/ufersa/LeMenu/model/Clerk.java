package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Clerk extends User{
	private String cpf;
	private String phone;
	private String login;
	private String password;	
	
	public Clerk() {}
	
	public Clerk(User model) {
		super(model.getId(), model.getName());
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
