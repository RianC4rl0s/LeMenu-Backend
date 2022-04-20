package br.edu.ufersa.LeMenu.Dto;

public class ClientRegisterDto {
	private String name;
	private Long tableId;
	
	public ClientRegisterDto() {}
	
	public ClientRegisterDto(String name, Long tableId) {
	
	this.name = name;
		this.tableId = tableId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	
	
}
