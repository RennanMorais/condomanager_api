package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.api.condomanager.condomanager.util.DateFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idMorador;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idArea;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String evento;
	
	@DateFormat
	@NotNull(message = "{campo.nulo.vazio}")
	private String data;
	
}
