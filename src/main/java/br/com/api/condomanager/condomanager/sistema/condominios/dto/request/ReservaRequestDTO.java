package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Long idMorador;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Long idArea;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String evento;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Date data;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private LocalDateTime inicio;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private LocalDateTime termino;
	
}
