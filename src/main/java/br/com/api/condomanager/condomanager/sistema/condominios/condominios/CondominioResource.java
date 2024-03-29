package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.CondominioProjection;

@RequestMapping("/condomanager/sistema")
@RestController
public class CondominioResource {

	@Autowired
	private CondominioService condominioService;
	
	@PostMapping("/condominio/cadastrar")
	public ResponseEntity<CondominiosResponseDTO> cadastrarCondominio(@Valid @RequestBody CondominiosRequestDTO request) {
		return ResponseEntity.ok(this.condominioService.cadastrarCondominio(request));
	}
	
	@GetMapping(value = "/condominio")
	public List<CondominioProjection> listarCondominios() {
		return this.condominioService.buscarCondominios();
	}

	@GetMapping(value = "/condominio/{id}")
	public ResponseEntity<CondominioProjection> getCondominio(@PathVariable Long id) {
		return ResponseEntity.ok(this.condominioService.getCondominio(id));
	}

	@PutMapping(value = "/condominio/editar/{id}")
	public ResponseEntity<CondominiosResponseDTO> editarCondominio(
			@PathVariable Long id,
			@Valid @RequestBody CondominiosRequestDTO request) {
		return ResponseEntity.ok(this.condominioService.editarCondominio(id, request));
	}

	@DeleteMapping(value = "/condominio/deletar/{id}")
	public ResponseEntity<CondominiosResponseDTO> deletarCondominio(@PathVariable Long id) {
		return ResponseEntity.ok(this.condominioService.deletarCondominio(id));
	}
	
	@GetMapping(value = "/condominio/principal")
	public ResponseEntity<CondominioProjection> buscarCondominioPrincipal() {
		return ResponseEntity.ok(this.condominioService.buscarCondominioPrincipal());
	}
	
	@PutMapping(value = "/condominio/principal/alterar/{id}")
	public ResponseEntity<CondominiosResponseDTO> editarCondominio(@PathVariable Long id) {
		return ResponseEntity.ok(this.condominioService.alterarCondominioPrincipal(id));
	}
	
}
