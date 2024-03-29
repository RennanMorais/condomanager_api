package br.com.api.condomanager.condomanager.sistema.condominios.ocorrencias;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.FinalizarOcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.OcorrenciaProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class OcorrenciaResource {

	@Autowired
	OcorrenciaService service;
	
	@PostMapping("/ocorrencia/registrar")
	public OcorrenciaResponseDTO registrarOcorrencia(@Valid @RequestBody OcorrenciaRequestDTO request) {
		return this.service.registrarOcorrencia(request);
	}

	@GetMapping("/ocorrencia/buscar/{id}")
	public ResponseEntity<OcorrenciaProjection> buscarOcorrencia(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.buscarOcorrencia(id));
	}

	@GetMapping("/ocorrencia/listar")
	public ResponseEntity<List<OcorrenciaProjection>> listarOcorrencias() {
		return ResponseEntity.ok(this.service.listarOcorrencias());
	}
	
	@PostMapping("/ocorrencia/atender/{id}")
	public OcorrenciaResponseDTO atenderOcorrencia(@PathVariable Long id) {
		return this.service.atenderOcorrencia(id);
	}
	
	@PostMapping("/ocorrencia/finalizar/{id}")
	public OcorrenciaResponseDTO finalizarOcorrencia(@PathVariable Long id, @Valid @RequestBody FinalizarOcorrenciaRequestDTO request) {
		return this.service.finalizarOcorrencia(id, request);
	}
	
}
