package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DisponibilidadeAluguelDTO {
	
	@NotNull(message = "{campo.nulo.vazio}")
    private Long idApartamento;

	@NotNull(message = "{campo.nulo.vazio}")
    private Boolean dispAluguel;
	
}
