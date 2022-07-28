package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {
	
	private Long idCondominio;
	private Long idMorador;
	private Long idArea;
	private String evento;
	private Date data;
	private Date inicio;
	private Date termino;
	
}
