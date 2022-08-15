package br.com.api.condomanager.condomanager.sistema.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.condomanager.condomanager.sistema.cadastro.ExceptionResponse;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

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
}
