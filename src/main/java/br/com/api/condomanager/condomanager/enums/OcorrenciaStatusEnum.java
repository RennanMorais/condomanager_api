package br.com.api.condomanager.condomanager.enums;

public enum OcorrenciaStatusEnum {

	PENDENTE("Pendente"),
	EM_ANDAMENTO("Em Andamento"),
	FINALIZADO("Finalizado");
	
	private String descricaoStatus;
	
	OcorrenciaStatusEnum(String descricao) {
		this.descricaoStatus = descricao;
	}
	
	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	
}
