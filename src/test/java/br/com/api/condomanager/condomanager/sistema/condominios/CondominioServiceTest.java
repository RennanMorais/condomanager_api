package br.com.api.condomanager.condomanager.sistema.condominios;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.CondominioService;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.util.Endereco;

class CondominioServiceTest {

	CondominiosRequestDTO request;
	CondominiosResponseDTO response;
	List<CondominioEntity> listCondominios;
	
	@InjectMocks
	CondominioService condominioService;
	
	@Mock
	CondominioRepository condominioRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		request = new CondominiosRequestDTO();
		request.setNome("x");
		request.setCnpj("x");
		request.setEmail("x");
		
		Endereco endereco = new Endereco();
		endereco.setBairro("x");
		endereco.setCidade("x");
		endereco.setComplemento("x");
		endereco.setEndereco("x");
		endereco.setEstado("x");
		endereco.setNumero("1");
		request.setEndereco(endereco);
		
		response = new CondominiosResponseDTO();
		response.setCodigo(200);
		response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
		
		CondominioEntity condominio = new CondominioEntity();
		condominio.setBairro("X");
		condominio.setCnpj("X");
		condominio.setComplemento("X");
		condominio.setEmail("X");
		condominio.setEndereco("X");
		condominio.setId(1L);
		
		listCondominios = new ArrayList<>();
		listCondominios.add(condominio);
	}
	
//	@Test
//	void cadastrarCondominio() {
//		
//		String token = "token";
//		
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		Assertions.assertDoesNotThrow(() -> this.condominioService.cadastrarCondominio(request, token));
//		
//	}
//	
//	@Test
//	void cadastrarCondominioRequestNulo() {
//		
//		String token = "token";
//		request = null;
//		
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		Assertions.assertThrows(CondomanagerException.class, () -> this.condominioService.cadastrarCondominio(request, token));
//		
//	}
//	
//	@Test
//	void getCondominiosTest() {
//		String token = "token";
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		when(this.condominioRepository.findAll()).thenReturn(listCondominios);
//		Assertions.assertDoesNotThrow(() -> this.condominioService.buscarCondominios(token));
//	}
//	
//	@Test
//	void getCondominiosThrowTest() {
//		String token = "token";
//		listCondominios.remove(0);
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		Assertions.assertThrows(CondomanagerException.class, () -> this.condominioService.buscarCondominios(token));
//	}
	
}
