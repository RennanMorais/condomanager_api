package br.com.api.condomanager.condomanager.sistema.dto.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface OcorrenciaProjection {

    Long getId();

    @JsonFormat(pattern = "dd/MM/yyyy")
    Date getData();
    String getDescricao();

    CondominioProjection getCondominio();

    UsuarioProjection getMorador();

    Long getStatus();

    String getResposta();

}
