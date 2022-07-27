package br.com.api.condomanager.condomanager.enums;

public enum AcessoEnum {

	ADMINISTRADOR("Administrador", 1L),
	PORTEIRO("Porteiro", 2L),
	MORADOR("Morador", 3L),
	DESATIVADO("Desativado", 0L);
	
	private String descricao;
	private Long nivel;
	
	AcessoEnum(String descricao, Long nivel) {
		this.descricao = descricao;
		this.nivel = nivel;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Long getNivel() {
		return nivel;
	}
	
}
