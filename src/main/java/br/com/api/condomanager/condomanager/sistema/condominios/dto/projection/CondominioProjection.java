package br.com.api.condomanager.condomanager.sistema.condominios.dto.projection;

import br.com.api.condomanager.condomanager.model.EnderecoEntity;

public interface CondominioProjection {
	
	Long getId();
	
	String getNome();
	
	String getCnpj();

	String getEmail();

	EnderecoProjection getEndereco();
	
}
