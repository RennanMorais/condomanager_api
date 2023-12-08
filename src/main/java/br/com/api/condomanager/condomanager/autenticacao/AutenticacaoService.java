package br.com.api.condomanager.condomanager.autenticacao;

import java.time.LocalDateTime;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.condomanager.condomanager.autenticacao.dto.AcessoDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.CodigoAcessoRequestDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.CodigoAcessoResponseDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.security.JwtTokenProvider;
import br.com.api.condomanager.condomanager.model.CodigoAcessoEntity;
import br.com.api.condomanager.condomanager.model.NivelAcessoEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.CodigoAcessoRepository;
import br.com.api.condomanager.condomanager.repository.NivelAcessoRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.util.Util;
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
	private final CodigoAcessoRepository codigoAcessoRepository;
	

	public LoginResponseDto autenticar(LoginRequestDto loginDto) throws LoginException {
		try {
			UserEntity user = userRepository.findByEmail(loginDto.getEmail());
			if(user == null) {
				log.error("ERRO: ".concat(" ".concat(this.getClass().getName())));
				throw new LoginException("Usuário não cadastrado!");
			}
			
			NivelAcessoEntity nivelAcesso = nivelAcessoRepository.findById(user.getNivelAcesso()).get();
			
			if(nivelAcesso.getId().equals(4L)) {
				throw new LoginException("Usuário desativado, entre em contato com o administrador");
			}
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			
			LoginResponseDto response = new LoginResponseDto();
			AcessoDTO acesso = new AcessoDTO();
			acesso.setNivel(nivelAcesso.getNivel());
			acesso.setAccessToken(jwtTokenProvider.gerarToken(user));
			response.setAcesso(acesso);
			
			return response;
		} catch (AuthenticationException e) {
			log.error("ERRO: ".concat(" ".concat(this.getClass().getName())));
			throw new LoginException("Usuário e/ou senha incorretos, tente novamente!");
		}
	}
	
	public CodigoAcessoResponseDTO  gerarCodigoEsqueceuSenha(@RequestBody CodigoAcessoRequestDTO request) throws LoginException {
		
		UserEntity user = this.validarUser(request.getEmail());
		
		CodigoAcessoEntity codAcesso = this.codigoAcessoRepository.findFirstByEmailOrderByCriadoDesc(request.getEmail());
		
		if(codAcesso != null) {
			codAcesso.setAtivo(false);
			this.codigoAcessoRepository.save(codAcesso);
		}
		
		String codigoAcesso = Util.gerarCodigo();
		
		CodigoAcessoEntity entity = new CodigoAcessoEntity();
		entity.setCodigo(codigoAcesso);
		entity.setCriado(LocalDateTime.now());
		entity.setExpiracao(LocalDateTime.now().plusHours(1));
		entity.setMorador(user);
		entity.setEmail(user.getEmail());
		entity.setAtivo(true);
		
		this.codigoAcessoRepository.save(entity);
		
		CodigoAcessoResponseDTO response = new CodigoAcessoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Código de acesso enviado para o e-mail do usuário");
		
		return response;
	}
	
	public CodigoAcessoResponseDTO enviarCodigoVerificacao(CodigoAcessoRequestDTO request) throws LoginException {
		
		CodigoAcessoEntity codAcesso = this.codigoAcessoRepository.findByCodigo(request.getCodigo());
		
		if(codAcesso == null || Boolean.FALSE.equals(codAcesso.getAtivo())) {
			throw new CondomanagerException("Código de verificação inválido");
		} else if(codAcesso.getExpiracao().isBefore(LocalDateTime.now())) {
	
			codAcesso.setAtivo(false);
			this.codigoAcessoRepository.save(codAcesso);
			
			throw new CondomanagerException("Código de verificação expirado");
		}
		
		codAcesso.setVerificado(LocalDateTime.now());
		codAcesso.setAtivo(false);
		this.codigoAcessoRepository.save(codAcesso);
		
		CodigoAcessoResponseDTO response = new CodigoAcessoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Código de verificação validado com sucesso");
		
		return response;
	}
	
	private UserEntity validarUser(String email) throws LoginException {
		UserEntity user = userRepository.findByEmail(email);
		
		if(user == null) {
			log.error("ERRO: ".concat(" ".concat(this.getClass().getName())));
			throw new CondomanagerException("Usuário não cadastrado!");
		}
		
		return user;
	}
}
