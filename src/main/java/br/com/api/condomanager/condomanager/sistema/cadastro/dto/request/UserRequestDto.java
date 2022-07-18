package br.com.api.condomanager.condomanager.sistema.cadastro.dto.request;

import java.math.BigInteger;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
	
	@NotEmpty(message = "O nome não pode estar vazio.")
	@NotNull(message = "O nome não pode ser nulo.")
	private String name;
	
	@Email(message = "E-mail inválido.")
	private String email;
	
	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
	private String password;
	
	@CPF(message = "CPF inválido.")
	private String cpf;
	
	@Pattern(regexp = "[0-9]+" , message = "Telefone inválido.")
	@NotEmpty(message = "O telefone não pode estar vazio.")
	private String phone;
	
	private BigInteger nivelAcesso;
}
