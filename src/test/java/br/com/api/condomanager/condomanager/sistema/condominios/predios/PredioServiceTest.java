package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

class PredioServiceTest {
	PredioRequestDTO request;
	PredioResponseDTO response;
	List<PredioResponseDTO> listResponse;
	CondominioEntity condominio;
	List<PredioEntity> listPredios;
	
	@InjectMocks
	PredioService predioService;
	
	@Mock
	CondominioRepository condominioRepository;
	
	@Mock
	PredioRepository predioRepository;
	
	@Mock
	Util utils;;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		request = new PredioRequestDTO();
		request.setNome("x");
		request.setCodigoCondominio(1L);
		
		response = new PredioResponseDTO();
		response.setNome("X");
		response.setCondominio("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(response);
		
		condominio = new CondominioEntity();
		condominio.setBairro("x");
		condominio.setComplemento("x");
		condominio.setEndereco("x");
		condominio.setNumero("1");
		
		PredioEntity predio = new PredioEntity();
		predio.setId(1L);
		predio.setNome("X");
		predio.setCondominio("X");
		predio.setIdCondominio(1L);
		
		listPredios = new ArrayList<>();
		listPredios.add(predio);
	}
	
	@Test
	void cadastrarCondominio() {
		when(this.condominioRepository.findByCodigo(Mockito.<String>any())).thenReturn(condominio);
		Assertions.assertDoesNotThrow(() -> this.predioService.cadastrarPredio(request));
	}
	
	@Test
	void cadastrarCondominioCondominioVazio() {
		when(this.condominioRepository.findByCodigo(Mockito.<String>any())).thenReturn(null);
		Assertions.assertThrows(ErroFluxoException.class, () -> this.predioService.cadastrarPredio(request));
	}
	
	@Test
	void getPrediosTest() {
		when(this.predioRepository.findAll()).thenReturn(listPredios);
		Assertions.assertDoesNotThrow(() -> this.predioService.getPredios());
	}
	
	@Test
	void getPrediosThrowTest() {
		listPredios.clear();
		when(this.predioRepository.findAll()).thenReturn(listPredios);
		Assertions.assertThrows(ErroFluxoException.class, () -> this.predioService.getPredios());
	}
}
