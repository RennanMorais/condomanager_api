package br.com.api.condomanager.condomanager.sistema.dto.projection;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;

import javax.persistence.*;
import java.util.Date;

public interface ReservaProjection {

    Long getId();

    String getEvento();

    Date getData();

    UsuarioProjection getMorador();

    AreaComumProjection getAreaComum();

    Long getStatus();

    String getResposta();

}
