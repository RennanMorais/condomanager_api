package br.com.api.condomanager.condomanager.enums;

public enum OcorrenciaStatusEnum {

	PENDENTE("Pendente", 1L),
	EM_ANDAMENTO("Em Andamento", 2L),
	FINALIZADO("Finalizado", 3L);
	
	private String descricaoStatus;
	private Long idStatus;
	
	OcorrenciaStatusEnum(String descricao, Long idStatus) {
		this.descricaoStatus = descricao;
		this.idStatus = idStatus;
	}
	
	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	
	public Long getIdStatus() {
		return idStatus;
	}
	
}
