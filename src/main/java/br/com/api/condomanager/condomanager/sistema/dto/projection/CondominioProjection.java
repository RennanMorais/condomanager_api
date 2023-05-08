package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface CondominioProjection {
	
	Long getId();
	
	String getNome();
	
	String getCnpj();

	String getEmail();

	EnderecoProjection getEndereco();
	
}
