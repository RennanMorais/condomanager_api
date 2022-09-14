package br.com.api.condomanager.condomanager.sistema.cadastro.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
	private String name;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Email(message = "{email.valid}")
	private String email;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Size(min = 6, message = "{senha.min.carac}")
	private String password;
	
	@CPF(message = "{cpf.valid}")
	private String cpf;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Pattern(regexp = "[0-9]+" , message = "{telefone.valid}")
	private String phone;
}
