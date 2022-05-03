package br.com.api.condomanager.condomanager.sistema;

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
import br.com.api.condomanager.condomanager.service.UsuarioService;
import br.com.api.condomanager.condomanager.sistema.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

@RequestMapping("condomanager/sistema")
@RestController
public class Cadastro {
	
	final UsuarioService usuarioService;
	
	public Cadastro(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping(value = "cadastro", produces = "application/json")
	public ResponseEntity<UserResponseDto> cadastroUsuario(@Validated @RequestBody UserRequestDto userRequest) 
			throws DadosPessoaisException {
		return ResponseEntity.ok(usuarioService.cadastrar(userRequest));
	}
	
	//Validação dos campos
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
