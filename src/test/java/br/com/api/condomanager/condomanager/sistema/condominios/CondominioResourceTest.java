package br.com.api.condomanager.condomanager.sistema.condominios;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.condomanager.condomanager.sistema.condominios.condominios.CondominioResource;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.CondominioService;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominioResponse;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.util.Endereco;

class CondominioResourceTest {
	
	CondominiosRequestDTO request;
	CondominiosResponseDTO response;
	List<CondominioResponse> listResponse;

	@InjectMocks
	CondominioResource condominioResource;
	
	@Mock
	CondominioService condominioService;
	
	private MockMvc mockMvc;
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(condominioResource).build();
		
		Endereco endereco = new Endereco();
		endereco.setEndereco("rua teste");
		
		request = new CondominiosRequestDTO();
		request.setNome("x");
		request.setCnpj("12312312312312");
		request.setEmail("x@X.com");
		request.setEndereco(endereco);
		
		response = new CondominiosResponseDTO();
		response.setCodigo(200);
		response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
	
		CondominioResponse responseGet = new CondominioResponse();
		responseGet.setNome("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(responseGet);
	}
	
//	@Test
//	void cadastrarCondominioTest() throws JsonProcessingException, Exception  {
//		
//		when(this.condominioService.cadastrarCondominio(Mockito.<CondominiosRequestDTO>any(), 
//				Mockito.<String>any())).thenReturn(response);
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/condomanager/sistema/condominio/cadastrar")
//				.content(mapper.writeValueAsString(request))
//				.header("authorization", "")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//			.andExpect(MockMvcResultMatchers.status().is(200))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.codigo").value(response.getCodigo()))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value(response.getMensagem()));
//	}
//	
//	@Test
//	void getCondominiosTest() throws JsonProcessingException, Exception {
//		
//		when(this.condominioService.buscarCondominios(Mockito.<String>any())).thenReturn(listResponse);
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/condomanager/sistema/condominio")
//				.header("authorization", "")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//			.andExpect(MockMvcResultMatchers.status().is(200));
//		
//	}
	
}
