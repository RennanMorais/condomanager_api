package br.com.api.condomanager.condomanager.autenticacao.cadastro;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse implements Serializable {
	
	private String codigo;
	private String mensagem;
	
}
