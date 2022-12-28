package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.api.condomanager.condomanager.sistema.cadastro.dto.request.UserRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoradorRequestDTO extends UserRequestDto {

	@NotBlank(message = "{campo.nulo.vazio}")
	@NotNull(message = "{campo.nulo.vazio}")
	private String rg;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idPredio;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@NotNull(message = "{campo.nulo.vazio}")
	private String apto;
	
}
