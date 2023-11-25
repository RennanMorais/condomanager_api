package br.com.api.condomanager.condomanager.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartamentoRequestDTO {

    @NotBlank(message = "{campo.nulo.vazio}")
    @NotNull(message = "{campo.nulo.vazio}")
    private String numero;

    @NotNull(message = "{campo.nulo.vazio}")
    private Long idPredio;
    
    @NotNull(message = "{campo.nulo.vazio}")
    private Boolean dispAluguel;

}
