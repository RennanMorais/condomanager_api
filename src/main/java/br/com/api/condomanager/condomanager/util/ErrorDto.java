package br.com.api.condomanager.condomanager.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorDto implements Serializable {

	public static ErrorDto with(HttpStatus status, String mensagem) {
		return new ErrorDto(status, Arrays.asList(
				new CodeErrorDto(String.valueOf(status.value()),mensagem)));
	}
	
	@JsonIgnore
	private HttpStatus status;

	@Builder.Default
	private List<CodeErrorDto> erros = new ArrayList<>();

	@AllArgsConstructor
	@Getter
	@Setter
	public static class CodeErrorDto implements Serializable {
		
		private String codigo;
		
		private String mensagem;

	}
	
}