package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface EnderecoProjection {

    Long getId();
    String getEndereco();
    String getNumero();
    String getComplemento();
    String getBairro();
    EstadoProjection getEstado();
    CidadeProjection getCidade();
}
