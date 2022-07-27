package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

class AreaComumServiceTest {
	AreaComumRequestDTO request;
	AreaComumResponseDTO response;
	List<AreaComumResponseDTO> listResponse;
	List<AreaComumEntity> listAreaComum;
	Optional<CondominioEntity> condominio;
	
	@InjectMocks
	AreaComumService areaComumService;
	
	@Mock
	AutenticacaoService autenticationService;
	
	@Mock
	AreaComumRepository areaComumRepository;
	
	@Mock
	CondominioRepository condominioRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		request = new AreaComumRequestDTO();
		request.setArea("x");
		request.setIdCondominio(1L);
		
		response = new AreaComumResponseDTO();
		response.setArea("X");
		response.setCondominio("X");
		
		listResponse = new ArrayList<>();
		listResponse.add(response);
		
		condominio = Optional.ofNullable(new CondominioEntity());
		condominio.get().setBairro("x");
		condominio.get().setComplemento("x");
		condominio.get().setEndereco("x");
		condominio.get().setNumero("1");
		
		AreaComumEntity area = new AreaComumEntity();
		area.setArea("X");
		area.setCondominio("X");
		area.setIdCondominio(1L);
		
		listAreaComum = new ArrayList<>();
		listAreaComum.add(area);
	}
	
	@Test
	void CadastrarAreaComumTest() {
		
		String token = "token";
		
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		when(this.condominioRepository.findById(Mockito.<Long>any())).thenReturn(condominio);
		Assertions.assertDoesNotThrow(() -> this.areaComumService.cadastrarAreaComum(request, token));
		
	}
	
	@Test
	void CadastrarAreaComumRequestNuloTest() {
		
		String token = "token";
		request = null;
		
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		Assertions.assertThrows(CondomanagerException.class, () -> this.areaComumService.cadastrarAreaComum(request, token));
		
	}
	
	@Test
	void cadastrarAreaComumCondominioVazio() {
		
		String token = "token";
		condominio = null;
		
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		when(this.condominioRepository.findById(Mockito.<Long>any())).thenReturn(condominio);
		Assertions.assertThrows(CondomanagerException.class, () -> this.areaComumService.cadastrarAreaComum(request, token));
		
		
	}
	
	@Test
	void getAreaComumTest() {
		String token = "token";
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		when(this.areaComumRepository.findAll()).thenReturn(listAreaComum);
		Assertions.assertDoesNotThrow(() -> this.areaComumService.getAreaComum(token));
	}
	
	@Test
	void getAreaComumThrowTest() {
		String token = "token";
		listAreaComum.remove(0);
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		when(this.areaComumRepository.findAll()).thenReturn(listAreaComum);
		Assertions.assertThrows(CondomanagerException.class, () -> this.areaComumService.getAreaComum(token));
	}
}
