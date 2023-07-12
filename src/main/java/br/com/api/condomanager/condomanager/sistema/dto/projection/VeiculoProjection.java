package br.com.api.condomanager.condomanager.sistema.dto.projection;

import javax.persistence.Column;

public interface VeiculoProjection {

    Long getId();

    Long getIdTipo();

    String getMarca();

    String getModelo();

    String getPlaca();

    UsuarioProjection getMorador();

}
