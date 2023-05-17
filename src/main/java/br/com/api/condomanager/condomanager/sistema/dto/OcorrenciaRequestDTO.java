package br.com.api.condomanager.condomanager.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.api.condomanager.condomanager.util.validators.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OcorrenciaRequestDTO {
	
	@NotBlank(message = "{campo.nulo.vazio}")
	private String descricao;
	
	@NotNull(message = "{campo.nulo.vazio}")
    private Long idCondominio;
	
	@NotNull(message = "{campo.nulo.vazio}")
	private String cpfMorador;
	
}
