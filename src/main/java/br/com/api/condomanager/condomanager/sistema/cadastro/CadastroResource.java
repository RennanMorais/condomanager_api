package br.com.api.condomanager.condomanager.sistema.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.cadastro.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.response.UserResponseDto;

@RequestMapping("/condomanager/sistema")
@RestController
public class CadastroResource {
	
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/cadastro", produces = "application/json")
	public ResponseEntity<UserResponseDto> cadastroUsuario(@Valid @RequestBody UserRequestDto userRequest) {
		return ResponseEntity.ok(usuarioService.cadastrar(userRequest));
	}
	
}
