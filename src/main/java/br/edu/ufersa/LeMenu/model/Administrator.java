package br.edu.ufersa.LeMenu.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_clerk")
public class Administrator extends Clerk {

	public Administrator(User model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	/*private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 */
	
}
