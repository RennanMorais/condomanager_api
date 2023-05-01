package br.com.api.condomanager.condomanager.sistema.condominios.dto.projection;

public interface CondominioProjection {
	
	Long getId();
	
	String getNome();
	
	String getCnpj();

	String getEmail();

	String getEndereco();

	String getNumero();
	
	String getComplemento();

	String getBairro();

	String getIdEstado();

	String getIdCidade();
	
}
