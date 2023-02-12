package br.com.api.condomanager.condomanager.sistema.condominios.areacomum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaComumRequestDTO {

	@NotBlank(message = "{campo.nulo.vazio}")
	private String area;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
}
