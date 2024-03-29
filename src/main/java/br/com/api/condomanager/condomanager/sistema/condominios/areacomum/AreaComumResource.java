package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

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

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.sistema.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.AreaComumProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class AreaComumResource {
	
	@Autowired
	AreaComumService areaComumService;

	@Autowired
	MyUserDetails userDetails;

	@PostMapping("/areacomum/cadastrar")
	public ResponseEntity<AreaComumResponseDTO> cadastrarAreaComum(@Valid @RequestBody AreaComumRequestDTO request) {
		return ResponseEntity.ok(this.areaComumService.cadastrarAreaComum(request));
	}
	
	@GetMapping(value = "/areacomum")
	public List<AreaComumProjection> getAreaComum() {
		return this.areaComumService.listarAreaComum();
	}

	@GetMapping(value = "/areacomum/{id}")
	public AreaComumProjection getAreaComum(@PathVariable Long id) {
		return this.areaComumService.getAreaComum(id);
	}

	@PutMapping(value = "/areacomum/editar/{id}")
	public ResponseEntity<AreaComumResponseDTO> editarAreaComum(@PathVariable Long id, @Valid @RequestBody AreaComumRequestDTO request) {
		return ResponseEntity.ok(this.areaComumService.editarAreaComum(id, request));
	}

	@DeleteMapping(value = "/areacomum/deletar/{id}")
	public ResponseEntity<AreaComumResponseDTO> deletarAreaComum(@PathVariable Long id) {
		return ResponseEntity.ok(this.areaComumService.deletarAreaComum(id));
	}
}
