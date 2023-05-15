package br.com.api.condomanager.condomanager.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.api.condomanager.condomanager.util.validators.DateFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idAreaComum;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String evento;
	
	@DateFormat
	@NotNull(message = "{campo.nulo.vazio}")
	private String data;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idMorador;
	
}
