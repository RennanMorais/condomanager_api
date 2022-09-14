package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.ReservaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.ReservaResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class ReservaResource {

	@Autowired
	ReservaService reservaService;
	
	@PostMapping("/reservas/reservar")
	public ResponseEntity<ReservaResponseDTO> reservar(@Valid @RequestBody ReservaRequestDTO request, 
			@RequestHeader String authorization) {
		return ResponseEntity.ok(this.reservaService.reservar(request, authorization));
	}
	
}
