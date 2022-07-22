package br.com.api.condomanager.condomanager.sistema.cadastro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.util.Util;

@SpringBootTest
class UsuarioServiceTest {
	
	UserRequestDto userRequest;
	
	UserResponseDto userResponse;
	
	@InjectMocks
	UsuarioService usuarioService;
	
	@Mock
	AutenticacaoService autenticacaoService;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	PasswordEncoder encoder;
	
	@Autowired
	Util util;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		userRequest = new UserRequestDto();
		userRequest.setName("X");
		userRequest.setEmail("X");
		userRequest.setPassword("123456");
		userRequest.setCpf("X");
		userRequest.setPhone("X");
		
		userResponse = new UserResponseDto();
		userResponse.setNome("X");
		userResponse.setEmail("X");
		userResponse.setNivelAcesso(null);
		
	}
 	
	@Test
	void cadastroUsuarioTest() throws DadosPessoaisException, MethodArgumentNotValidException {
		
		UserResponseDto response = this.usuarioService.cadastrar(userRequest);
		
		Assertions.assertEquals(userRequest.getName(), response.getNome());
		Assertions.assertEquals(userRequest.getEmail(), response.getEmail());
		Assertions.assertNotNull(response.getNivelAcesso());
		
	}
	
	@Test
	void validacaoCpfValidoTest() {
		String cpf = "34094987029";
		Boolean validar = util.validarCpf(cpf);
		Assertions.assertTrue(validar);
	}
}
