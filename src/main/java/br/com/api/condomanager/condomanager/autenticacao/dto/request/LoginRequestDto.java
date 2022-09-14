package br.com.api.condomanager.condomanager.autenticacao.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

	@NotBlank(message = "Campo não pode ser nulo ou vazio.")
	@Email(message = "E-mail inválido.")
	private String email;
	private String password;
	
}
