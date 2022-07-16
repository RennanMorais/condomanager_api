package br.com.api.condomanager.condomanager.autenticacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.api.condomanager.condomanager.model.User;

@SpringBootTest
public class TokenServiceTest {
	
	User user;

	@InjectMocks
	TokenService tokenService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setId(1L);
	}
	
	@Test
	void generateTokenTest() {
		String tokenResponse = this.tokenService.generateToken(user);
		Assertions.assertNotNull(tokenResponse);
	}
	
}
