package br.com.api.condomanager.condomanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.model.User;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.ExpiredTokenException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;

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
	
	public boolean validate(String token) {
		try {
            Claims claims = tokenService.decodeToken(token);
            
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
            	throw new ExpiredTokenException("Token expirado!");
            }
            
            return true;
            
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException("Token inválido!");
        }
    }
	
}
