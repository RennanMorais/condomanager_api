package br.com.api.condomanager.condomanager.sistema.condominios.dto.request;

import br.com.api.condomanager.condomanager.util.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominiosRequestDTO{

	private String nome;
	private String cnpj;
	private String email;
	private Endereco endereco;
	
}
