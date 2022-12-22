package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.PredioResponseDTO;

class PredioServiceTest {
	PredioRequestDTO request;
	PredioResponseDTO response;
	List<PredioResponseDTO> listResponse;
	Optional<CondominioEntity> condominio;
	List<PredioEntity> listPredios;
	
	@InjectMocks
	PredioService predioService;
	
	@Mock
	CondominioRepository condominioRepository;
	
	@Mock
	PredioRepository predioRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		request = new PredioRequestDTO();
		request.setNome("x");
		request.setIdCondominio(1L);
		
		response = new PredioResponseDTO();
		response.setNome("X");
		response.setCondominio("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(response);
		
		condominio = Optional.ofNullable(new CondominioEntity());
		condominio.get().setBairro("x");
		condominio.get().setComplemento("x");
		condominio.get().setEndereco("x");
		condominio.get().setNumero("1");
		
		PredioEntity predio = new PredioEntity();
		predio.setId(1L);
		predio.setNome("X");
		predio.setCondominio("X");
		predio.setIdCondominio(1L);
		
		listPredios = new ArrayList<>();
		listPredios.add(predio);
	}
	
//	@Test
//	void cadastrarCondominio() {
//		
//		String token = "token";
//		
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		when(this.condominioRepository.findById(Mockito.<Long>any())).thenReturn(condominio);
//		Assertions.assertDoesNotThrow(() -> this.predioService.cadastrarPredio(request, token));
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
//		Assertions.assertThrows(CondomanagerException.class, () -> this.predioService.cadastrarPredio(request, token));
//		
//	}
//	
//	@Test
//	void cadastrarCondominioCondominioVazio() {
//		
//		String token = "token";
//		condominio = null;
//		
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		when(this.condominioRepository.findById(Mockito.<Long>any())).thenReturn(condominio);
//		Assertions.assertThrows(CondomanagerException.class, () -> this.predioService.cadastrarPredio(request, token));
//		
//		
//	}
//	
//	@Test
//	void getPrediosTest() {
//		String token = "token";
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		when(this.predioRepository.findAll()).thenReturn(listPredios);
//		Assertions.assertDoesNotThrow(() -> this.predioService.getPredios(token));
//	}
//	
//	@Test
//	void getPrediosThrowTest() {
//		String token = "token";
//		listPredios.remove(0);
//		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
//		when(this.predioRepository.findAll()).thenReturn(listPredios);
//		Assertions.assertThrows(CondomanagerException.class, () -> this.predioService.getPredios(token));
//	}
}
