package br.com.api.condomanager.condomanager.sistema.dto.request;

import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

	@Email(message = "E-mail inválido.")
	private String email;
	private String senha;
	
}
