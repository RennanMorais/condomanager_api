package br.com.api.condomanager.condomanager.autenticacao;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.autenticacao.dto.CodigoAcessoRequestDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.CodigoAcessoResponseDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.RedefinirSenhaDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.RedefinirSenhaResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class LoginResource {

	@Autowired
	private AutenticacaoService authService;
	
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<LoginResponseDto> autenticar(@RequestBody @Valid LoginRequestDto loginDto) throws LoginException {
		return ResponseEntity.ok(authService.autenticar(loginDto));
	}
	
	@PostMapping(value = "/login/recuperar/senha", produces = "application/json")
	public ResponseEntity<CodigoAcessoResponseDTO> gerarCodigoAcesso(@RequestBody @Valid CodigoAcessoRequestDTO request) throws LoginException {
		return ResponseEntity.ok(authService.gerarCodigoEsqueceuSenha(request));
	}
	
	@PostMapping(value = "/login/recuperar/validar/codigo", produces = "application/json")
	public ResponseEntity<CodigoAcessoResponseDTO> validarCodigoVerificacao(@RequestBody @Valid CodigoAcessoRequestDTO request) throws LoginException {
		return ResponseEntity.ok(authService.enviarCodigoVerificacao(request));
	}
	
	@PostMapping(value = "/login/redefinir/senha", produces = "application/json")
	public ResponseEntity<RedefinirSenhaResponseDTO> redefinirSenha(@RequestBody @Valid RedefinirSenhaDTO request) throws LoginException {
		return ResponseEntity.ok(authService.redefinirSenha(request));
	}
}
