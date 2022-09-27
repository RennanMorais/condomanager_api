package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class petRequestDTO {
	
	private String nome;
	private String tipo;
	private String sexo;
	private Long idMorador;
	private String morador;
	private String telefone;
	
}
