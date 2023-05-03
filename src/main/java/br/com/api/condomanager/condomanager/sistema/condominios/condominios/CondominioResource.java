package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;

import javax.validation.Valid;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.CondominioProjection;

@RequestMapping("/condomanager/sistema")
@RestController
public class CondominioResource {

	@Autowired
	private CondominioService condominioService;

	@Autowired
	private MyUserDetails userDetails;
	
	@PostMapping("/condominio/cadastrar")
	public ResponseEntity<CondominiosResponseDTO> cadastrarCondominio(@Valid @RequestBody CondominiosRequestDTO request) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.condominioService.cadastrarCondominio(request));
	}
	
	@GetMapping(value = "/condominio")
	public List<CondominioProjection> listarCondominios() {
		return this.condominioService.buscarCondominios();
	}

	@GetMapping(value = "/condominio/{id}")
	public ResponseEntity<CondominioProjection> getCondominio(Long id) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.condominioService.getCondominio(id));
	}

	@PutMapping(value = "/condominio/editar/{id}")
	public ResponseEntity<CondominiosResponseDTO> editarCondominio(
			@PathVariable Long id,
			@RequestBody CondominiosRequestDTO request) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.condominioService.editarCondominio(id, request));
	}

	@DeleteMapping(value = "/condominio/deletar/{id}")
	public ResponseEntity<CondominiosResponseDTO> deletarCondominio(@PathVariable Long id) {
		Util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(this.condominioService.deletarCondominio(id));
	}
	
}
