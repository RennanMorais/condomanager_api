package br.com.api.condomanager.condomanager.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;
import br.com.api.condomanager.condomanager.util.Util;

@SpringBootTest
class AutenticacaoServiceTest {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	Util util;
	
	@Test
	void autenticacaoUsuarioTest() throws InvalidLoginException {
		LoginRequestDto login = new LoginRequestDto("fulano@teste.com", "123456");
		LoginResponseDto response = this.autenticacaoService.autenticar(login);
		
		Assertions.assertEquals(login.getEmail(), response.getEmail());
		Assertions.assertNotNull(response.getNome());
		Assertions.assertNotNull(response.getToken());
	}
	
}
