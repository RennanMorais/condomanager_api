package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface AreaComumProjection {
	
	Long getId();
	
	String getNome();
	
	CondominioProjection getCondominio();
}
