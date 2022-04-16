package br.edu.ufersa.LeMenu.Dto;

public class ClientRegisterDto {
	private String Nome;
	private Long tableId;
	
	
	
	public ClientRegisterDto() {}
	
	public ClientRegisterDto(String nome, Long tableId) {
	
		Nome = nome;
		this.tableId = tableId;
	}

	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	
	
}
