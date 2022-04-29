package br.com.api.condomanager.condomanager.sistema.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
	
	private String nome;
	
	private String email;
	
	private String token;
	
}
