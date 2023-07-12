package br.com.api.condomanager.condomanager.sistema.condominios.predios;

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

import br.com.api.condomanager.condomanager.sistema.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.PredioProjection;

@RequestMapping("/condomanager/sistema")
@RestController
public class PredioResource {

	@Autowired
	PredioService predioservice;
	
	@PostMapping("/predio/cadastrar")
	public ResponseEntity<PredioResponseDTO> cadastrarPredio(@Valid @RequestBody PredioRequestDTO request) {
		return ResponseEntity.ok(this.predioservice.cadastrarPredio(request));
	}
	
	@GetMapping(value = "/predios/condominio/{id}")
	public ResponseEntity<List<PredioProjection>> getPrediosPorCondominio(@PathVariable Long id) {
		return ResponseEntity.ok(this.predioservice.getPrediosPorCondominio(id));
	}
	
	@GetMapping(value = "/predios/listar")
	public ResponseEntity<List<PredioProjection>> getPredios() {
		return ResponseEntity.ok(this.predioservice.getPredios());
	}
	
	@PutMapping(value = "/predio/listar/{id}")
	public ResponseEntity<PredioResponseDTO> editarPredio(@PathVariable Long id, @Valid @RequestBody PredioRequestDTO request) {
		return ResponseEntity.ok(this.predioservice.editarPredio(id, request));
	}
	
	@DeleteMapping("/predio/deletar/{id}")
	public ResponseEntity<PredioResponseDTO> deletarPredio(@PathVariable Long id) {
		return ResponseEntity.ok(this.predioservice.deletarPredio(id));
	}
	
}
