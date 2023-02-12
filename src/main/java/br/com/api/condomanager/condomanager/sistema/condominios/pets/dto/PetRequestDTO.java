package br.com.api.condomanager.condomanager.sistema.condominios.pets.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequestDTO {
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String nome;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String tipo;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String sexo;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private Long idMorador;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String morador;
	
	@NotBlank(message = "{campo.nulo.vazio}")
	@Pattern(regexp = "[0-9]+" , message = "{telefone.valid}")
	private String telefone;
	
}
