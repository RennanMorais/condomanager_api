package br.com.api.condomanager.condomanager.autenticacao;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;

@RequestMapping("/condomanager/sistema")
@RestController
public class LoginResource {

	@Autowired
	AutenticacaoService authService;
	
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<LoginResponseDto> autenticar(@RequestBody @Valid LoginRequestDto loginDto) throws LoginException {
		return ResponseEntity.ok(authService.autenticar(loginDto));
	}
}
