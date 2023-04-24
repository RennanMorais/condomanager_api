package br.com.api.condomanager.condomanager.enums;

public enum TipoVeiculoEnum {

	CARRO("Carro", 1L),
	MOTO("Moto", 2L),
	VAN("Van", 3L),
	OUTROS("Outros", 4L);
	
	private String descricao;
	private Long tipo;
	
	TipoVeiculoEnum(String descricao, Long tipo) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Long getTipo() {
		return tipo;
	}
	
}
