package br.com.api.condomanager.condomanager.autenticacao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;

@RequestMapping("condomanager/sistema")
@RestController
public class LoginResource {
	
	final AutenticacaoService autenticacaoService;
	
	public LoginResource(AutenticacaoService autenticacaoService) {
		this.autenticacaoService = autenticacaoService;
	}

	@PostMapping(value = "login", produces = "application/json")
	public ResponseEntity<LoginResponseDto> autenticar(@RequestBody @Validated LoginRequestDto loginDto) {
		return ResponseEntity.ok(autenticacaoService.autenticar(loginDto));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String nomeCampo = ((FieldError) error).getField();
			String mensagemErro = error.getDefaultMessage();
			errors.put(nomeCampo, mensagemErro);
		});
		
		return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
