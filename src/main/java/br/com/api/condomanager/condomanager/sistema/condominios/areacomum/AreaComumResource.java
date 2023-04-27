package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AreaComumProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class AreaComumResource {
	
	@Autowired
	AreaComumService areaComumService;

	@PostMapping("/areacomum/cadastrar")
	public ResponseEntity<AreaComumResponseDTO> cadastrarAreaComum(@Valid @RequestBody AreaComumRequestDTO request) {
		return ResponseEntity.ok(this.areaComumService.cadastrarAreaComum(request));
	}
	
	@GetMapping(value = "/areacomum")
	public List<AreaComumProjection> getAreaComum() {
		return this.areaComumService.listarAreaComum();
	}
	
}
