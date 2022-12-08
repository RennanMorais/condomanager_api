package br.com.api.condomanager.condomanager.sistema.cadastro;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.cadastro.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

@RequestMapping("/condomanager/sistema")
@RestController
public class CadastroResource {
	
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/cadastro", produces = "application/json")
	public ResponseEntity<UserResponseDto> cadastroUsuario(@Valid @RequestBody UserRequestDto userRequest) 
			throws DadosPessoaisException {
		return ResponseEntity.ok(usuarioService.cadastrar(userRequest));
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
