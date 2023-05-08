package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface PredioProjection {

	Long getId();
	
	String getNome();
	
	CondominioProjection getCondominio();
	
}
