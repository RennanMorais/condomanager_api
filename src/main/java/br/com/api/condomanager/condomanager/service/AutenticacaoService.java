package br.com.api.condomanager.condomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.model.User;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;

@Service
public class AutenticacaoService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	final TokenService tokenService;
	
	public AutenticacaoService(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	//Autenticação de usuário
	public LoginResponseDto autenticar(LoginRequestDto loginDto) {
		
		User user = usuarioRepository.findByEmail(loginDto.getEmail());
		
		if(!encoder.matches(loginDto.getSenha(), user.getSenha())) {
			throw new InvalidLoginException("Usuario e/ou senha, incorretos!");
		}
		
		LoginResponseDto response = new LoginResponseDto();
		response.setNome(user.getNome());
		response.setEmail(user.getEmail());
		response.setToken(tokenService.generateToken(user));
		
		return response;
	}
	
}
