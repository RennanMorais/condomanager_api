package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotNull(message = "${campo.vazio.nulo}")
	private Long idCondominio;
	
	@NotNull
	private Long idMorador;
	
	@NotNull
	private Long idArea;
	
	@NotNull
	private String evento;
	
	@NotNull
	private Date data;
	
	@NotNull
	private LocalDateTime inicio;
	
	@NotNull
	private LocalDateTime termino;
	
}
