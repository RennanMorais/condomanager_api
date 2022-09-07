package br.com.api.condomanager.condomanager.sistema.exceptions.handler;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.cadastro.ExceptionResponse;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> allExceptions(Exception e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DadosPessoaisException.class)
	public final ResponseEntity<ExceptionResponse> badRequestException(DadosPessoaisException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidLoginException.class)
	public final ResponseEntity<ExceptionResponse> unauthorizedException(InvalidLoginException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(CondomanagerException.class)
	public final ResponseEntity<ExceptionResponse> condomanagerException(CondomanagerException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
	public final ResponseEntity<ExceptionResponse> validarCamposException(UnexpectedTypeException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ExceptionResponse> validarCamposFormatoException(MethodArgumentNotValidException e) {
		
		String mensagem = e.getMessage();
		int pos = mensagem.indexOf("message");
		
		String mensagemTratada = mensagem.substring(pos);
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), mensagemTratada);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
