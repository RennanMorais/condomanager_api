package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Long idCondominio;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Long idMorador;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Long idArea;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private String evento;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Date data;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Date inicio;
	
	@NotBlank(message = "${campo.vazio.nulo}")
	private Date termino;
	
}
