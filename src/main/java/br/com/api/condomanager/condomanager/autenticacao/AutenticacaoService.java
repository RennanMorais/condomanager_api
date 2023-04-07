package br.com.api.condomanager.condomanager.autenticacao;

import javax.security.auth.login.LoginException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.security.JwtTokenProvider;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

	private final UsuarioRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public LoginResponseDto autenticar(LoginRequestDto loginDto) throws LoginException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			
			LoginResponseDto response = new LoginResponseDto();
			response.setToken(jwtTokenProvider.gerarToken(loginDto.getEmail(), userRepository.findByEmail(loginDto.getEmail()).getNomeAccess()));
			
			return response;
		} catch (AuthenticationException e) {
			throw new LoginException("Usuário e/ou senha incorretos, tente novamente!");
		}
	}
}
