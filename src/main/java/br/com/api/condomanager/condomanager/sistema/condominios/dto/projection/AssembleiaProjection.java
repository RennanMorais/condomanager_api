package br.com.api.condomanager.condomanager.sistema.condominios.dto.projection;

import br.com.api.condomanager.condomanager.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface AssembleiaProjection {

	Long getId();

    String getTitulo();

    String getDescricao();

    Date getData();
    
    CondominioProjection getCondominio();
    
    AreaComumProjection getAreaComum();

}
