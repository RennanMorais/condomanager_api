package br.com.api.condomanager.condomanager.sistema.condominios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservasDadosResponseDTO {

	private String condominio;
	private String morador;
	private String area;
	private String evento;
	private String Data;
	private String status;
	
}
