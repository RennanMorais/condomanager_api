package br.com.api.condomanager.condomanager.sistema.dto.projection;

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
