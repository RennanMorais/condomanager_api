package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/condomanager/sistema")
public class petsResource {

	@GetMapping("/pets")
	public void getPets() {
		
	}
	
	@PostMapping("/pets/cadastrar")
	public void cadastrarPet() {
		
	}
	
}
