package br.com.api.condomanager.condomanager.autenticacao.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String nome;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Email(message = "{email.valid}")
	private String email;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Size(min = 6, message = "{senha.min.carac}")
	private String senha;
	
	@CPF(message = "{cpf.valid}")
	@NotBlank(message = "{campo.nulo.vazio}")
	private String cpf;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Pattern(regexp = "[0-9]+" , message = "{telefone.valid}")
	private String telefone;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Pattern(regexp = "[0-9]+" , message = "{telefone.valid}")
	private String ddd;
}
