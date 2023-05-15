package br.com.api.condomanager.condomanager.sistema.dto.projection;

import java.util.Date;

public interface AssembleiaProjection {

	Long getId();

    String getTitulo();

    String getDescricao();

    Date getData();
    
    CondominioProjection getCondominio();
    
    AreaComumProjection getAreaComum();

}
