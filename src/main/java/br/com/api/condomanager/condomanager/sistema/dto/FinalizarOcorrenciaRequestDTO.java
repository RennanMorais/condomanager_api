package br.com.api.condomanager.condomanager.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalizarOcorrenciaRequestDTO {

	@NotNull(message = "{campo.nulo.vazio}")
	@NotBlank(message = "{campo.nulo.vazio}")
	private String resposta;
	
}
