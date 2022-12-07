package br.com.api.condomanager.condomanager.sistema.exceptions.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.condomanager.condomanager.sistema.cadastro.ExceptionResponse;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;
import br.com.api.condomanager.condomanager.util.ErrorDto;
import br.com.api.condomanager.condomanager.util.ErrorDto.CodeErrorDto;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> anyExceptions(Exception e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<Object> handleInvalidLoginException(InvalidLoginException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<Object> handleInvalidTokenException(SignatureException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Token invalido!");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<CodeErrorDto> erros =  new ArrayList<>();
        if (ex.getBindingResult().getFieldErrors().isEmpty()) {
			erros.addAll(ex.getBindingResult().getAllErrors().stream()
					.map(error -> new CodeErrorDto(
							String.valueOf(HttpStatus.BAD_REQUEST.value()), 
							error.getDefaultMessage()))
					.collect(Collectors.toList()));
		}
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			CodeErrorDto codeError = new CodeErrorDto(
					String.valueOf(HttpStatus.BAD_REQUEST.value()), 
					error.getField() + ": " + error.getDefaultMessage());
			erros.add(codeError);
		}

		return ResponseEntity.badRequest().body(new ErrorDto(HttpStatus.BAD_REQUEST, erros));
	}
}
