package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PetResponseDTO;

@RestController
@RequestMapping("/condomanager/sistema")
public class PetResource {

	@Autowired
	PetService service;
	
	@GetMapping("/pets")
	public ResponseEntity<PetResponseDTO> getPets(@Valid @RequestBody PetRequestDTO request) {
		return ResponseEntity.ok(this.service.salvarPet(request));
	}
	
	@PostMapping("/pets/cadastrar")
	public void cadastrarPet() {
		
	}
	
}
