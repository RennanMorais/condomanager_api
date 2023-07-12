package br.com.api.condomanager.condomanager.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

	private String nome;
	
	private String email;
	
	private Long nivelAcesso;
}
