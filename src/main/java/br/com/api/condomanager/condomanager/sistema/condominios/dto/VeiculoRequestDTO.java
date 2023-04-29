package br.com.api.condomanager.condomanager.sistema.condominios.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRequestDTO {

	@NotNull(message = "{campo.nulo.vazio}")
	private Long idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idPredio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idMorador;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long tipoVeiculo;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String marca;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String modelo;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String placa;
	
}
