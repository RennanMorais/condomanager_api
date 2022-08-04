package br.com.api.condomanager.condomanager.enums;

public enum ReservaStatusEnum {
	
	PENDENTE("Pendente"),
	APROVADO("Aprovado"),
	CANCELADO("Cancelado");

	private String descricao;
	
	ReservaStatusEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
