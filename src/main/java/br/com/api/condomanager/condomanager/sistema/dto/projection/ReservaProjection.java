package br.com.api.condomanager.condomanager.sistema.dto.projection;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ReservaProjection {

    Long getId();

    String getEvento();

    @JsonFormat(pattern = "dd/MM/yyyy")
    Date getData();

    UsuarioProjection getMorador();

    AreaComumProjection getAreaComum();

    Long getStatus();

    String getResposta();

}
