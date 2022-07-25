package br.com.api.condomanager.condomanager.autenticacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.ExpiredTokenException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SpringBootTest
class AutenticacaoServiceTest {
	
	@InjectMocks
	AutenticacaoService autenticacaoService;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	PasswordEncoder encoder;
	
	@Mock
	TokenService tokenService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void autenticacaoUsuarioTest() throws InvalidLoginException {
		LoginRequestDto login = new LoginRequestDto();
		login.setEmail("email");
		login.setPassword("senha");
		
		UserEntity user = new UserEntity();
		user.setId(Long.valueOf("1"));
		user.setEmail(login.getEmail());
		user.setPassword("senha");
		
		String tokenGenerate = this.tokenService.generateToken(user);
		
		when(this.usuarioRepository.findByEmail(login.getEmail())).thenReturn(user);
		when(this.encoder.matches(user.getPassword(), login.getPassword())).thenReturn(true);
		when(this.tokenService.generateToken(Mockito.<UserEntity> any())).thenReturn(tokenGenerate);
		
		LoginResponseDto response = this.autenticacaoService.autenticar(login);
		
		assertEquals(login.getEmail(), response.getEmail());
		assertEquals(tokenGenerate, response.getToken());
	}
	
	@Test
	void autenticacaoUsuarioInvalidoTest() throws InvalidLoginException {
		LoginRequestDto login = new LoginRequestDto();
		login.setEmail("email");
		login.setPassword("senha");
		
		UserEntity user = new UserEntity();
		user.setId(Long.valueOf("1"));
		user.setEmail(login.getEmail());
		user.setPassword(encoder.encode(login.getPassword()));
		
		String tokenGenerate = this.tokenService.generateToken(user);
		
		when(this.usuarioRepository.findByEmail(Mockito.<String> any())).thenReturn(user);
		when(this.encoder.encode(Mockito.<String> any())).thenReturn(null);
		when(this.tokenService.generateToken(Mockito.<UserEntity> any())).thenReturn(tokenGenerate);
		
		assertThrows(InvalidLoginException.class, () -> this.autenticacaoService.autenticar(login));
	}
}
