package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.reserva.dto.AprovarReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.reserva.dto.ReservaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.reserva.dto.ReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.reserva.dto.ReservasDadosResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class ReservaResource {

	@Autowired
	ReservaService reservaService;
	
	@PostMapping("/reservas/reservar")
	public ResponseEntity<ReservaResponseDTO> reservar(@Valid @RequestBody ReservaRequestDTO request) {
		return ResponseEntity.ok(this.reservaService.reservar(request));
	}
	
	@GetMapping("/reservas")
	public List<ReservasDadosResponseDTO> listarReservas() {
		return this.reservaService.listarReservas();
	}
	
	@PostMapping("/reservas/aprovar/{id}")
	public ResponseEntity<AprovarReservaResponseDTO> aprovarReserva(@PathVariable Long id) {
		return ResponseEntity.ok(this.reservaService.aprovarReserva(id)); 
	}
	
	@PostMapping("/reservas/cancelar/{id}")
	public ResponseEntity<AprovarReservaResponseDTO> cancelarReserva(@PathVariable Long id) {
		return ResponseEntity.ok(this.reservaService.cancelarReserva(id)); 
	}
}
