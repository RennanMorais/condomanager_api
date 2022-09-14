package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.api.condomanager.condomanager.util.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominiosRequestDTO{

	@NotBlank(message = "{campo.nulo.vazio}")
	private String nome;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Size(min = 14, message = "{size.valid}")
	@Pattern(regexp = "[0-9]+" , message = "{cnpj.valid}")
	private String cnpj;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Email(message = "{email.valid}")
	private String email;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private Endereco endereco;
	
}
