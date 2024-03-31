package br.com.api.condomanager.condomanager.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredioRequestDTO {
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String nome;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Integer qtdPavimentos;
	
}
