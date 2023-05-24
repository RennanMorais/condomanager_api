package br.com.api.condomanager.condomanager.sistema.dto.projection;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface AssembleiaProjection {

	Long getId();

    String getTitulo();

    String getDescricao();

    @JsonFormat(pattern = "dd/MM/yyyy")
    Date getData();
    
    CondominioProjection getCondominio();
    
    AreaComumProjection getAreaComum();

}
