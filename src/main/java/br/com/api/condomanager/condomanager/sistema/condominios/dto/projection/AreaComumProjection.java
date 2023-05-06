package br.com.api.condomanager.condomanager.sistema.condominios.dto.projection;

public interface AreaComumProjection {
	
	Long getId();
	
	String getNome();
	
	CondominioProjection getCondominio();
}
