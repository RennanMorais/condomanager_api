package br.com.api.condomanager.condomanager.enums;

public enum ReservaStatusEnum {
	
	PENDENTE("Pendente", 1L),
	APROVADO("Aprovado", 2L),
	CANCELADO("Cancelado", 3L);

	private String descricao;
	private Long codigo;
	
	ReservaStatusEnum(String descricao, Long codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
}
