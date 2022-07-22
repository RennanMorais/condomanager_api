package br.com.api.condomanager.condomanager.sistema.condominios;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.util.Endereco;

class CondominioServiceTest {

	CondominiosRequestDTO request;
	CondominiosResponseDTO response;
	
	@InjectMocks
	CondominioService condominioService;
	
	@Mock
	AutenticacaoService autenticationService;
	
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
	}
	
	@Test
	void cadastrarCondominio() {
		
		String token = "token";
		
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		Assertions.assertDoesNotThrow(() -> this.condominioService.cadastrarCondominio(request, token));
		
	}
	
	@Test
	void cadastrarCondominioRequestNulo() {
		
		String token = "token";
		request = null;
		
		when(this.autenticationService.validaUserToken(token)).thenReturn(true);
		Assertions.assertThrows(CondomanagerException.class, () -> this.condominioService.cadastrarCondominio(request, token));
		
	}
	
}
