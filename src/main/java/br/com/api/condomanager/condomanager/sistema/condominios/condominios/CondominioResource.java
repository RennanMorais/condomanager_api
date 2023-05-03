package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.CondominioProjection;

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
	public List<CondominioProjection> getXCondominios() {
		return this.condominioService.buscarCondominios();
	}

	@DeleteMapping(value = "/condominio/deletar/{id}")
	public ResponseEntity<CondominiosResponseDTO> deletarCondominio(@PathVariable Long id) {
		return ResponseEntity.ok(this.condominioService.deletarCondominio(id));
	}
	
}
