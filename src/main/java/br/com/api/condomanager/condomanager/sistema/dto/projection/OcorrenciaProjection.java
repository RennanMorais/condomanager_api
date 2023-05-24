package br.com.api.condomanager.condomanager.sistema.dto.projection;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
