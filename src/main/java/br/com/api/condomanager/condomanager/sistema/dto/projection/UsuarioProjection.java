package br.com.api.condomanager.condomanager.sistema.dto.projection;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public interface UsuarioProjection {

    Long getId();

    String getNome();

    String getEmail();

    String getTelefone();

    String getDdd();

    CondominioProjection getCondominio();

    PredioProjection getPredio();

    String getAvatar();

}
