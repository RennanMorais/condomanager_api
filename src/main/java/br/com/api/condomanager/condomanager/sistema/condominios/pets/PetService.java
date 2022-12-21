package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.PetEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.PetRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.PetResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public PetResponseDTO salvarPet(PetRequestDTO request) {
		
		if(request != null) {
			
			Optional<UserEntity> user = usuarioRepository.findById(request.getIdMorador());
			if(user == null) {
				throw new CondomanagerException("Morador não encontrado.");
			}
			
			PetEntity pet = new PetEntity();
			pet.setIdMorador(request.getIdMorador());
			pet.setMorador(user.get().getName());
			pet.setNome(request.getNome());
			pet.setSexo(request.getSexo());
			pet.setTelefone(user.get().getPhone());
			pet.setTipo(request.getTipo());
			
			try {
				petRepository.save(pet);
			} catch(Exception e) {
				throw new CondomanagerException("Erro interno: " + e.getMessage());
			}
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setNome(request.getNome());
		response.setSexo(request.getSexo());
		response.setTipo(request.getTipo());
		
		return response;
	}
	
}
