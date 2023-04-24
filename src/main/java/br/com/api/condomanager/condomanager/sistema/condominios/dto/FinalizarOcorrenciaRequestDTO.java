package br.com.api.condomanager.condomanager.sistema.condominios.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalizarOcorrenciaRequestDTO {

	@NotNull(message = "{campo.nulo.vazio}")
	private String feedback;
	
}
