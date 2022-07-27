package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.PredioResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class PredioResource {

	@Autowired
	PredioService predioservice;
	
	@PostMapping("/predio/cadastrar")
	public ResponseEntity<PredioResponseDTO> cadastrarPredio(@RequestBody PredioRequestDTO request, 
			@RequestHeader String authorization) {
		return ResponseEntity.ok(predioservice.cadastrarPredio(request, authorization));
	}
	
	@GetMapping(value = "/predios")
	public List<PredioResponseDTO> getPredios(@RequestHeader String authorization) {
		return this.predioservice.getPredios(authorization);
	}
	
}
