package br.com.api.condomanager.condomanager.sistema.condominios.veiculos.dto;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRequestDTO {

	private BigInteger idCondominio;
	private BigInteger idPredio;
	private BigInteger morador;
	private BigInteger tipoVeiculo;
	private String marca;
	private String modelo;
	private String placa;
	
}
