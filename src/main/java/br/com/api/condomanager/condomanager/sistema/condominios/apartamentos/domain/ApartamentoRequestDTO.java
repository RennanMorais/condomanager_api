package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApartamentoRequestDTO {

	@NotBlank(message = "{campo.nulo.vazio}")
    @NotNull(message = "{campo.nulo.vazio}")
    private String numero;

    @NotNull(message = "{campo.nulo.vazio}")
    private Long idPredio;
    
    @NotBlank(message = "{campo.nulo.vazio}")
    @NotNull(message = "{campo.nulo.vazio}")
    private String pavimento;
    
    @NotNull(message = "{campo.nulo.vazio}")
    private Boolean dispAluguel;

}
