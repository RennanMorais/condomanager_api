package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

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

import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumResponseDTO;

class AreaComumResourceTest {
	AreaComumRequestDTO request;
	AreaComumResponseDTO response;
	List<AreaComumResponseDTO> listResponse;

	@InjectMocks
	AreaComumResource areaComumResource;
	
	@Mock
	AreaComumService areaComumService;
	
	private MockMvc mockMvc;
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(areaComumResource).build();
		
		request = new AreaComumRequestDTO();
		request.setArea("x");
		request.setCodigoCondominio(1L);
		
		response = new AreaComumResponseDTO();
		response.setArea("X");
		response.setCondominio("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(response);
	}
	
	@Test
	void cadastrarAreaComumTest() throws JsonProcessingException, Exception  {
		
		when(this.areaComumService.cadastrarAreaComum(Mockito.<AreaComumRequestDTO>any())).thenReturn(response);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/condomanager/sistema/areacomum/cadastrar")
				.content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.area").value(response.getArea()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.condominio").value(response.getCondominio()));
	}
	
	@Test
	void getAreaComumTest() throws JsonProcessingException, Exception {
		
		when(this.areaComumService.listarAreaComum()).thenReturn(listResponse);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/condomanager/sistema/areacomum", "")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(MockMvcResultMatchers.status().is(200));
		
	}
}
