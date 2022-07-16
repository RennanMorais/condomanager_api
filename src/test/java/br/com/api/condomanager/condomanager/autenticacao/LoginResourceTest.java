package br.com.api.condomanager.condomanager.autenticacao;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;

@SpringBootTest
class LoginResourceTest {
	
	@InjectMocks
	private LoginResource loginResource;
	
	@Mock
	private AutenticacaoService autenticacaoService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private PasswordEncoder encoder;
	
	private MockMvc mockMvc;
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginResource).setValidator(Mockito.mock(Validator.class)).build();
	}
	
	@Test
	void loginTest() throws JsonProcessingException, Exception {
		
		LoginRequestDto login = new LoginRequestDto();
		login.setEmail("X");
		login.setPassword("X");
		
		LoginResponseDto loginResponse = new LoginResponseDto();
		loginResponse.setName("X");
		loginResponse.setEmail("X");
		loginResponse.setToken("X");
		
		when(this.autenticacaoService.autenticar(Mockito.<LoginRequestDto>any())).thenReturn(loginResponse);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/condomanager/sistema/login")
				.content(mapper.writeValueAsString(login))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(400));
		
	}
	
}
