package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.PetEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.PetRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PetResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	Util utils;
	
	public PetResponseDTO salvarPet(PetRequestDTO request) {
		
		UserEntity user = usuarioRepository.findByCodigo(String.valueOf(request.getCodigoMorador()));
		
		if(user == null) {
			throw new ErroFluxoException("Morador não encontrado.");
		}
		
		PetEntity pet = new PetEntity();
		pet.setCodigo(utils.gerarCodigo("pet"));
		pet.setIdMorador(user.getId());
		pet.setMorador(user.getName());
		pet.setNome(request.getNome());
		pet.setSexo(request.getSexo());
		pet.setTelefone(user.getPhone());
		pet.setTipo(request.getTipo());
		
		try {
			petRepository.save(pet);
		} catch(Exception e) {
			throw new ErroFluxoException("Erro interno: " + e.getMessage());
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setCodigo(pet.getCodigo());
		response.setNome(request.getNome());
		response.setSexo(request.getSexo());
		response.setTipo(request.getTipo());
		
		return response;
	}
	
}
