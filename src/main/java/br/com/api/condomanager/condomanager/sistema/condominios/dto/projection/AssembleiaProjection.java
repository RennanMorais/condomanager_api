package br.com.api.condomanager.condomanager.sistema.condominios.dto.projection;

import java.util.Date;

public interface AssembleiaProjection {

	Long getId();

    String getTitulo();

    String getDescricao();

    Date getData();
    
    Long getIdCondominio();
    
    Long getIddAreaComum();
	
}
