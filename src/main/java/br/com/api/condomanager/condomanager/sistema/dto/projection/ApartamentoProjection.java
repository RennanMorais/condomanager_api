package br.com.api.condomanager.condomanager.sistema.dto.projection;

public interface ApartamentoProjection {

    Long getId();
    String getNumero();
    PredioProjection getPredio();

}
