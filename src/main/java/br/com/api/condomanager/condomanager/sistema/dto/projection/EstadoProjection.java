package br.com.api.condomanager.condomanager.sistema.dto.projection;

import javax.persistence.Column;

public interface EstadoProjection {

    Long getId();
    String getNome();
    String getUf();
}
