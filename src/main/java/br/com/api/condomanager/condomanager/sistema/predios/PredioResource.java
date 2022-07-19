package br.com.api.condomanager.condomanager.sistema.predios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.predios.dto.response.PredioResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class PredioResource {

	@Autowired
	PredioService predioservice;
	
	@PostMapping("/predio/cadastrar")
	public ResponseEntity<PredioResponseDTO> cadastrarPredio() {
		return ResponseEntity.ok(predioservice.cadastrarPredio());
	}
	
}
