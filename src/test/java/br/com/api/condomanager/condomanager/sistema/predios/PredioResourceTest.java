package br.com.api.condomanager.condomanager.sistema.predios;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.condomanager.condomanager.sistema.predios.dto.request.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.predios.dto.response.PredioResponseDTO;

class PredioResourceTest {

	PredioRequestDTO request;
	PredioResponseDTO response;
	List<PredioResponseDTO> listResponse;

	@InjectMocks
	PredioResource predioResource;
	
	@Mock
	PredioService predioService;
	
	private MockMvc mockMvc;
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(predioResource).build();
		
		request = new PredioRequestDTO();
		request.setNome("x");
		request.setIdCondominio(1L);
		
		response = new PredioResponseDTO();
		response.setNome("X");
		response.setCondominio("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(response);
	}
	
	@Test
	void cadastrarCondominioTest() throws JsonProcessingException, Exception  {
		
		when(this.predioService.cadastrarPredio(Mockito.<PredioRequestDTO>any(), 
				Mockito.<String>any())).thenReturn(response);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/condomanager/sistema/predio/cadastrar")
				.content(mapper.writeValueAsString(request))
				.header("authorization", "")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(response.getNome()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.condominio").value(response.getCondominio()));
	}
	
	@Test
	void getPrediosTest() throws JsonProcessingException, Exception {
		
		when(this.predioService.getPredios(Mockito.<String>any())).thenReturn(listResponse);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/condomanager/sistema/predios", "")
				.header("authorization", "")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(200));
		
	}
	
}
