package br.com.api.condomanager.condomanager.sistema.condominios.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.api.condomanager.condomanager.util.validators.DateFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long codigoCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long codigoMorador;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long codigoArea;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String evento;
	
	@DateFormat
	@NotNull(message = "{campo.nulo.vazio}")
	private String data;
	
}
