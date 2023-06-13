package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface PetProjection {

	Long getId();
	String getNome();
	String getTipo();
	UsuarioProjection getMorador();
	
}
