package br.edu.ufersa.LeMenu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	@ManyToMany(cascade = { CascadeType.MERGE })
	private List<Privilege> privileges = new ArrayList<>();
	
	public Role() {}
	
	public Role(Long id, String name, List<User> users, List<Privilege> privileges) {
		this.id = id;
		this.name = name;
		this.users = users;
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege) {
		privileges.add(privilege);
	}
	
	public void removePrivilege(Privilege privilege) {
		privileges.remove(privilege);
	}
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}