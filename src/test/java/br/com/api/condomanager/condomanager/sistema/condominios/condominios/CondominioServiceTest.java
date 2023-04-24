package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.util.Endereco;
import br.com.api.condomanager.condomanager.util.Util;

class CondominioServiceTest {

	CondominiosRequestDTO request;
	CondominiosResponseDTO response;
	List<CondominioEntity> listCondominios;
	CondominioEntity condominio;
	
	@InjectMocks
	CondominioService condominioService;
	
	@Mock
	CondominioRepository condominioRepository;
	
	@Mock
	Util utils;
	
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
		
		condominio = new CondominioEntity();
		condominio.setBairro("X");
		condominio.setCnpj("X");
		condominio.setComplemento("X");
		condominio.setEmail("X");
		condominio.setEndereco("X");
		condominio.setId(1L);
		
		listCondominios = new ArrayList<>();
		listCondominios.add(condominio);
	}
	
	@Test
	void cadastrarCondominio() {
		when(condominioRepository.findByCnpj(Mockito.<String>any())).thenReturn(null);
		Assertions.assertDoesNotThrow(() -> this.condominioService.cadastrarCondominio(request));
	}
	
	@Test
	void listarCondominiosTest() {
		when(condominioRepository.findAll()).thenReturn(listCondominios);
		Assertions.assertDoesNotThrow(() -> this.condominioService.buscarCondominios());
	}
	
}
