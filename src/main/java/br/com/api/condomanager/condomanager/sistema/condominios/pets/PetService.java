package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.PetEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.PetRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PetResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public PetResponseDTO salvarPet(PetRequestDTO request) {
		
		Optional<UserEntity> user = usuarioRepository.findById(request.getIdMorador());
		
		if(!user.isPresent()) {
			throw new ErroFluxoException("Morador não encontrado.");
		}
		
		PetEntity pet = new PetEntity();
		pet.setIdMorador(user.get().getId());
		pet.setNome(request.getNome());
		pet.setTipo(request.getTipo());
		
		try {
			petRepository.save(pet);
		} catch(Exception e) {
			throw new ErroFluxoException("Erro interno: " + e.getMessage());
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setNome(request.getNome());
		response.setTipo(request.getTipo());
		
		return response;
	}
	
}
