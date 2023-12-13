package br.com.api.condomanager.condomanager.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinirSenhaDTO {

	private String email;
	
	private String novaSenha;
	
}
