package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaComumRequestDTO {

	@NotBlank(message = "{campo.nulo.vazio}")
	private String area;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
}
