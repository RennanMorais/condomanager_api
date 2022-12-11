package br.com.api.condomanager.condomanager.autenticacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.model.UserEntity;
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

	public LoginResponseDto autenticar(LoginRequestDto loginDto) {
		
		UserEntity user = usuarioRepository.findByEmail(loginDto.getEmail());
		
		if(user == null) {
			throw new InvalidLoginException("Usuario não encontrado.");
		}
		
		if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new InvalidLoginException("Usuario e/ou senha, incorretos.");
		}
		
		LoginResponseDto response = new LoginResponseDto();
		response.setToken(tokenService.generateToken(user));
		
		user.setToken(response.getToken());
		usuarioRepository.save(user);
		
		return response;
	}
	
	public boolean validate(String token) {
		try {
            Claims claims = tokenService.decodeToken(token);
            
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
            	throw new ExpiredTokenException("Token expirado.");
            }
            
            return true;
            
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException("Token Inválido ou expirado.");
        }
    }
	
	public boolean validaUserToken(String token) {
		
		if(token != null && !token.isEmpty()) {
			UserEntity user = this.usuarioRepository.findByToken(token);
			if(user != null) {
				if(user.getToken().equalsIgnoreCase(token) && this.validate(token)) {
					return true;
				}
			}
		}
		
		throw new InvalidTokenException("Token Inválido ou expirado.");
	}
	
}
