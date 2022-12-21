package br.com.api.condomanager.condomanager.autenticacao;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.security.JwtTokenProvider;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

	private final UsuarioRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public LoginResponseDto autenticar(LoginRequestDto loginDto) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			
			LoginResponseDto response = new LoginResponseDto();
			response.setToken(jwtTokenProvider.gerarToken(loginDto.getEmail(), userRepository.findByEmail(loginDto.getEmail()).getNomeAccess()));
			
			return response;
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
