package br.com.api.condomanager.condomanager.autenticacao.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodigoAcessoRequestDTO extends CodigoVerificacaoDTO {

	@NotBlank(message = "{campo.nulo.vazio}")
	@Email(message = "{email.valid}")
	private String email;
	
}
