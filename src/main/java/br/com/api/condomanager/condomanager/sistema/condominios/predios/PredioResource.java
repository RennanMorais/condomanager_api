package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.PredioProjection;

@RequestMapping("/condomanager/sistema")
@RestController
public class PredioResource {

	@Autowired
	PredioService predioservice;
	
	@PostMapping("/predio/cadastrar")
	public ResponseEntity<PredioResponseDTO> cadastrarPredio(@Valid @RequestBody PredioRequestDTO request) {
		return ResponseEntity.ok(predioservice.cadastrarPredio(request));
	}
	
	@GetMapping(value = "/predio/listar")
	public List<PredioProjection> getPredios() {
		return this.predioservice.getPredios();
	}
	
	@DeleteMapping("/predio/deletar/{id}")
	public ResponseEntity<PredioResponseDTO> deletarPredio(@PathVariable Long id) {
		return ResponseEntity.ok(predioservice.deletarPredio(id));
	}
	
}
