package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "{campo.nulo.vazio}")
	private String data;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private LocalDateTime inicio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private LocalDateTime termino;
	
}
