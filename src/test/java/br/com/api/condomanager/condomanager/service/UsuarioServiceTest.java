package br.com.api.condomanager.condomanager.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.api.condomanager.condomanager.sistema.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.util.Util;

@SpringBootTest
class UsuarioServiceTest {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	Util util;
	
	@Test
	void cadastroUsuarioTest() throws DadosPessoaisException, MethodArgumentNotValidException {
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
	void validacaoCpfValidoTest() {
		String cpf = "34094987029";
		Boolean validar = util.validarCpf(cpf);
		Assertions.assertTrue(validar);
	}
}
