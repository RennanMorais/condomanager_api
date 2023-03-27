package br.com.api.condomanager.condomanager.sistema.condominios.dto;

import br.com.api.condomanager.condomanager.util.DateFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssembleiaRequestDTO {

    @NotBlank(message = "{campo.nulo.vazio}")
    private String titulo;

    @NotBlank(message = "{campo.nulo.vazio}")
    private String descricao;

    @DateFormat
    @NotNull(message = "{campo.nulo.vazio}")
    private String data;

    @NotBlank(message = "{campo.nulo.vazio}")
    private Long idCondominio;

}
