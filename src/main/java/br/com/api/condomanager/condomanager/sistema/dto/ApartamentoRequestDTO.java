package br.com.api.condomanager.condomanager.sistema.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApartamentoRequestDTO {

    @NotBlank(message = "{campo.nulo.vazio}")
    @NotNull(message = "{campo.nulo.vazio}")
    private String numero;

    @NotNull(message = "{campo.nulo.vazio}")
    private Long idPredio;

}
