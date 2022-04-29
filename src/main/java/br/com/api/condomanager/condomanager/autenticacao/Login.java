package br.com.api.condomanager.condomanager.autenticacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.condomanager.condomanager.service.AutenticacaoService;
import br.com.api.condomanager.condomanager.sistema.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.LoginResponseDto;

@RequestMapping("condomanager/sistema")
@RestController
public class Login {
	
	final AutenticacaoService autenticacaoService;
	
	public Login(AutenticacaoService autenticacaoService) {
		this.autenticacaoService = autenticacaoService;
	}

	@PostMapping(value = "login", produces = "application/json")
	public ResponseEntity<LoginResponseDto> autenticar(@RequestBody LoginRequestDto loginDto) {
		return ResponseEntity.ok(autenticacaoService.autenticar(loginDto));
	}
	
}
