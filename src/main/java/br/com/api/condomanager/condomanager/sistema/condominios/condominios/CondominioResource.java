package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominioResponse;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.condominios.dto.CondominiosResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class CondominioResource {

	@Autowired
	CondominioService condominioService;
	
	@PostMapping("/condominio/cadastrar")
	public ResponseEntity<CondominiosResponseDTO> cadastrarCondominio(@Valid @RequestBody CondominiosRequestDTO request) {
		return ResponseEntity.ok(this.condominioService.cadastrarCondominio(request));
	}
	
	@GetMapping(value = "/condominio")
	public List<CondominioResponse> getXCondominios() {
		return this.condominioService.buscarCondominios();
	}
	
}
