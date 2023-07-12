package br.com.api.condomanager.condomanager.sistema.condominios.pets;

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

import br.com.api.condomanager.condomanager.sistema.dto.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PetResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.PetProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class PetResource {

	@Autowired
	PetService service;
	
	@PostMapping("/pets/cadastrar")
	public ResponseEntity<PetResponseDTO> cadastrarPet(@Valid @RequestBody PetRequestDTO request) {
		return ResponseEntity.ok(this.service.salvarPet(request));
	}
	
	@GetMapping("/pets")
	public ResponseEntity<List<PetProjection>> listarPets() {
		return ResponseEntity.ok(this.service.listarPets());
	}
	
	@GetMapping("/pets/{id}")
	public ResponseEntity<PetProjection> getPet(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getPet(id));
	}
	
	@PutMapping("/pets/editar/{id}")
	public ResponseEntity<PetResponseDTO> editarPet(@PathVariable Long id, @Valid @RequestBody PetRequestDTO request) {
		return ResponseEntity.ok(this.service.editarPet(id, request));
	}
	
	@DeleteMapping("/pets/deletar/{id}")
	public ResponseEntity<PetResponseDTO> deletarPet(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.deletarPet(id));
	}
}
