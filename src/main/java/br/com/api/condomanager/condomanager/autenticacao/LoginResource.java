package br.com.api.condomanager.condomanager.autenticacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<LoginResponseDto> autenticar(@RequestBody LoginRequestDto loginDto, @RequestHeader String token) {
		return ResponseEntity.ok(autenticacaoService.autenticar(loginDto));
	}
	
}
