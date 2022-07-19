package br.com.api.condomanager.condomanager.sistema.condominios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.CondominiosResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class CondominioResource {

	@Autowired
	CondominioService condominioService;
	
	@PostMapping("/predio/cadastrar")
	public ResponseEntity<CondominiosResponseDTO> cadastrarPredio(CondominiosRequestDTO request, @RequestHeader String authorization) {
		return ResponseEntity.ok(this.condominioService.cadastrarPredio(request, authorization));
	}
	
}
