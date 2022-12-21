package br.com.api.condomanager.condomanager.sistema.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;

@ExtendWith(MockitoExtension.class)
class CadastroResourceTest {
	
	@Mock
	private  UsuarioService usuarioService;
	
	@InjectMocks
	private CadastroResource cadastroResource;
	
	private MockMvc mockMvc;
	
	final ObjectMapper mapper = new ObjectMapper();
	
	Map<String, String> errors;
	
	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(cadastroResource).setValidator(Mockito.mock(Validator.class)).build();
		errors = new HashMap<>();
		errors.put("1", "Teste");
	}
	
//	@Test
//	void testCadastroUsuario() throws Exception {
//		
//		UserResponseDto response = new UserResponseDto("Fulano Teste", "fulano@teste1.com", null);
//		UserRequestDto userRequest = new UserRequestDto("Fulano Teste", "fulano@teste1.com", "123456", "33229561058", "11971833250");
//		
//		when(this.usuarioService.cadastrar(Mockito.<UserRequestDto>any())).thenReturn(response);
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/condomanager/sistema/cadastro")
//				.content(mapper.writeValueAsString(userRequest))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//			.andExpect(MockMvcResultMatchers.status().is(200))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(response.getNome()))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(response.getEmail()))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.nivelAcesso").value(response.getNivelAcesso()));
//	}
	
	@Test
	void testAutenticarUsuario() throws Exception {
		
		LoginRequestDto request = new LoginRequestDto("fulano@teste1.com", "1234567");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/condomanager/sistema/login")
				.content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}
}
