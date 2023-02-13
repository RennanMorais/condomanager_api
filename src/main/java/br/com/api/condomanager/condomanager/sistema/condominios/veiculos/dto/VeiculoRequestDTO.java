package br.com.api.condomanager.condomanager.sistema.condominios.veiculos.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRequestDTO {

	@NotNull(message = "{campo.nulo.vazio}")
	private BigInteger idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private BigInteger idPredio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private BigInteger morador;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private BigInteger tipoVeiculo;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String marca;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String modelo;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String placa;
	
}
