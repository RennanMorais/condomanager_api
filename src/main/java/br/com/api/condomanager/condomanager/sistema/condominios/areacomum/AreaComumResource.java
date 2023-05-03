package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

import java.util.List;

import javax.validation.Valid;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AreaComumProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class AreaComumResource {
	
	@Autowired
	AreaComumService areaComumService;

	@Autowired
	MyUserDetails userDetails;

	@PostMapping("/areacomum/cadastrar")
	public ResponseEntity<AreaComumResponseDTO> cadastrarAreaComum(@Valid @RequestBody AreaComumRequestDTO request) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.areaComumService.cadastrarAreaComum(request));
	}
	
	@GetMapping(value = "/areacomum")
	public List<AreaComumProjection> getAreaComum() {
		return this.areaComumService.listarAreaComum();
	}

	@GetMapping(value = "/areacomum/{id}")
	public AreaComumProjection getAreaComum(@PathVariable Long id) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return this.areaComumService.getAreaComum(id);
	}

	@PutMapping(value = "/areacomum/editar/{id}")
	public ResponseEntity<AreaComumResponseDTO> editarAreaComum(@PathVariable Long id, @RequestBody AreaComumRequestDTO request) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.areaComumService.editarAreaComum(id, request));
	}

	@DeleteMapping(value = "/areacomum/deletar/{id}")
	public ResponseEntity<AreaComumResponseDTO> deletarAreaComum(@PathVariable Long id) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.areaComumService.deletarAreaComum(id));
	}
}
