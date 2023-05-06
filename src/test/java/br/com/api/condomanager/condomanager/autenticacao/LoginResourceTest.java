package br.com.api.condomanager.condomanager.autenticacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.condomanager.condomanager.autenticacao.dto.AcessoDTO;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.LoginResponseDto;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {
	
	@InjectMocks
	LoginResource resource;
	
	@Mock
	AutenticacaoService authService;
	
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	LoginResponseDto response = new LoginResponseDto();
	LoginRequestDto request = new LoginRequestDto();

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(resource).build();
		
		AcessoDTO acesso = new AcessoDTO();
		acesso.setAccessToken("X");
		acesso.setNivel("admin");
		response.setAcesso(acesso);
		
		request.setEmail("X");
		request.setPassword("x");
	}

	@Test
	void autenticarTest() throws JsonProcessingException, Exception {
		
		//when(this.authService.autenticar(Mockito.<LoginRequestDto>any())).thenReturn(response);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/condomanager/sistema/login")
				.content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
}
