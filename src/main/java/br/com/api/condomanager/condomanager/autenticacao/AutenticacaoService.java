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
import br.com.api.condomanager.condomanager.model.NivelAcessoEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.NivelAcessoRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutenticacaoService {

	private final NivelAcessoRepository nivelAcessoRepository;
	private final UsuarioRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	

	public LoginResponseDto autenticar(LoginRequestDto loginDto) throws LoginException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			
			UserEntity user = userRepository.findByEmail(loginDto.getEmail());
			NivelAcessoEntity nivelAcesso = nivelAcessoRepository.findById(user.getIdNivelAcesso()).get();
			
			if(user == null || nivelAcesso == null) {
				log.error("ERRO: ".concat(" ".concat(this.getClass().getName())));
				throw new LoginException("Falha ao consultar dados do usuário");
			}
			
			LoginResponseDto response = new LoginResponseDto();
			AcessoDTO acesso = new AcessoDTO();
			acesso.setNivel(nivelAcesso.getNivel());
			acesso.setAccessToken(jwtTokenProvider.gerarToken(loginDto.getEmail(), user.getNome(), nivelAcesso.getNivel()));
			response.setAcesso(acesso);
			
			return response;
		} catch (AuthenticationException e) {
			log.error("ERRO: ".concat(" ".concat(this.getClass().getName())));
			throw new LoginException("Usuário e/ou senha incorretos, tente novamente!");
		}
	}
}
