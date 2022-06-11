package br.edu.ufersa.LeMenu.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ufersa.LeMenu.model.Client;
import br.edu.ufersa.LeMenu.model.OrderingTable;

public class OTableDto {

	private Long id;
	
	private String code;
	@JsonProperty("isOpen")
	private boolean isOpen;
	
	private Client client;

	
	public OTableDto() {}
	public OTableDto(Long id, String code, boolean isOpen, Client client) {
		
		this.id = id;
		this.code = code;
		this.isOpen = isOpen;
		this.client = client;
	}
	public OTableDto(OrderingTable ot) {
		this.id = ot.getId();
		this.code = ot.getCode();
		this.isOpen = ot.getIsOpen();
		this.client = ot.getClient();
	}

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

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
