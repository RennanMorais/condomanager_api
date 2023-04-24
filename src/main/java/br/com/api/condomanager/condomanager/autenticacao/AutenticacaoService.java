package br.com.api.condomanager.condomanager.autenticacao;

import javax.security.auth.login.LoginException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.AcessoDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.security.JwtTokenProvider;
import br.com.api.condomanager.condomanager.model.UserEntity;
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
			
			UserEntity user = userRepository.findByEmail(loginDto.getEmail());
			
			LoginResponseDto response = new LoginResponseDto();
			AcessoDTO acesso = new AcessoDTO();
			acesso.setNivel(user.getNomeAccess());
			acesso.setAccessToken(jwtTokenProvider.gerarToken(loginDto.getEmail(), user.getName(), user.getNomeAccess()));
			response.setAcesso(acesso);
			
			return response;
		} catch (AuthenticationException e) {
			throw new LoginException("Usuário e/ou senha incorretos, tente novamente!");
		}
	}
}
