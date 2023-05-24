package br.com.api.condomanager.condomanager.sistema.condominios.morador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.sistema.dto.MoradorRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.MoradorResponseDTO;
import br.com.api.condomanager.condomanager.util.Util;

@RequestMapping("/condomanager/sistema")
@RestController
public class MoradorResource {

	@Autowired
	MoradorService moradorService;

	@Autowired
	private MyUserDetails userDetails;

	@Autowired
	private Util util;
	
	@PostMapping("/condominio/morador/cadastrar")
	public ResponseEntity<MoradorResponseDTO> cadastrarMorador(@Valid @RequestBody MoradorRequestDTO request) {
		util.validarAdmin(userDetails.getLoginUser().trim());
		return ResponseEntity.ok(moradorService.cadastrarMorador(request));
	}
}
