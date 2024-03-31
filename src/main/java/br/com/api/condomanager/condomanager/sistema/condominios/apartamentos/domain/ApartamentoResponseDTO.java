package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApartamentoResponseDTO {

    private String codigo;
    private String mensagem;

}
