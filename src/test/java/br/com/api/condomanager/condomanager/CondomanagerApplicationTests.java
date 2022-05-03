package br.com.api.condomanager.condomanager;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.api.condomanager.condomanager.service.AutenticacaoService;
import br.com.api.condomanager.condomanager.service.UsuarioService;
import br.com.api.condomanager.condomanager.sistema.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidLoginException;
import br.com.api.condomanager.condomanager.util.Util;
import io.jsonwebtoken.lang.Assert;

@SpringBootTest
class CondomanagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	Util util;
	
	@Test
	public void cadastroUsuarioTest() throws DadosPessoaisException, MethodArgumentNotValidException {
		UserRequestDto userRequest = new UserRequestDto();
		
		userRequest.setNome("Fulano Teste");
		userRequest.setEmail(String.valueOf(new Date()) + "@teste.com");
		userRequest.setSenha("123456");
		userRequest.setCpf(String.valueOf(new Random().ints(10)));
		userRequest.setTelefone("11971833250");
		userRequest.setNivelAcesso(BigInteger.valueOf(1));
		
		Assertions.assertTrue(usuarioService.validarEmailExistente(userRequest.getEmail()));
		UserResponseDto response = usuarioService.cadastrar(userRequest);
		
		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setNome(userRequest.getNome());
		responseDto.setEmail(userRequest.getEmail());
		responseDto.setNivelAcesso(userRequest.getNivelAcesso());
		
		Assertions.assertEquals(responseDto.getNome(), response.getNome());
		Assertions.assertEquals(responseDto.getEmail(), response.getEmail());
		Assertions.assertEquals(responseDto.getNivelAcesso(), response.getNivelAcesso());
		
	}
	
	@Test
	public void autenticacaoUsuarioTest() throws InvalidLoginException {
		LoginRequestDto login = new LoginRequestDto("fulano@teste.com", "123456");
		LoginResponseDto response = autenticacaoService.autenticar(login);
		
		Assertions.assertEquals(login.getEmail(), response.getEmail());
		Assertions.assertNotNull(response.getNome());
		Assertions.assertNotNull(response.getToken());
	}
	
	@Test
	public void validacaoCpfValidoTest() {
		String cpf = "34094987029";
		
		Boolean validar = util.validarCpf(cpf);
		
		Assert.isTrue(validar);
	}
 
}
