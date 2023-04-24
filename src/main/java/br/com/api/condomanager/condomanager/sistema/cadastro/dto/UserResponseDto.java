package br.com.api.condomanager.condomanager.sistema.cadastro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
	
	private String codigo;

	private String nome;
	
	private String email;
	
	private Long nivelAcesso;
}
