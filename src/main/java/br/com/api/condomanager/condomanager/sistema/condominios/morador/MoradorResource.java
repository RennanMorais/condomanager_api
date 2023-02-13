package br.com.api.condomanager.condomanager.sistema.condominios.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.morador.dto.MoradorRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.morador.dto.MoradorResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class MoradorResource {

	@Autowired
	MoradorService moradorService;
	
	@PostMapping("/condominio/morador/cadastrar")
	public ResponseEntity<MoradorResponseDTO> cadastrarMorador(@RequestBody MoradorRequestDTO request) {
		return ResponseEntity.ok(moradorService.cadastrarMorador(request));
	}
}
