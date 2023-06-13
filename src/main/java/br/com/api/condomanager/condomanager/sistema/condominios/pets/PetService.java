package br.com.api.condomanager.condomanager.sistema.condominios.pets;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.PetEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.PetRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.PetRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PetResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.PetProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		pet.setMorador(user.get());
		pet.setNome(request.getNome());
		pet.setTipo(request.getTipo());
		
		try {
			petRepository.save(pet);
		} catch(Exception e) {
			throw new ErroFluxoException("Erro interno: " + e.getMessage());
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Pet cadastrado com sucesso.");
		
		return response;
	}
	
	public List<PetProjection> listarPets() {
		
		List<PetProjection> response = this.petRepository.findAllProjectedBy();
		
		if(response == null) {
			log.error("Falha ao consultar dados de pets no banco de dados");
			throw new ErroFluxoException("Falha ao consultar pets");
		}
		
		if(response.isEmpty()) {
			log.error("A lista de pets retornou vazia");
			throw new ErroFluxoException("Nenhum pet cadastrado!");
		}
		
		return response;
		
	}
	
	public PetProjection getPet(Long id) {
		
		PetProjection response = this.petRepository.findProjectionById(id);
		
		if(response == null) {
			log.error("Falha ao consultar dados de pets no banco de dados");
			throw new ErroFluxoException("Pet não encontrado");
		}
		
		return response;
		
	}
	
	public PetResponseDTO editarPet(Long id, PetRequestDTO request) {
		
		Optional<PetEntity> pet = this.petRepository.findById(id);
		Optional<UserEntity> user = this.usuarioRepository.findById(request.getIdMorador());
		
		if(!pet.isPresent()) {
			log.error("Falha ao consultar dados de pets no banco de dados");
			throw new ErroFluxoException("Pet não encontrado");
		} else if(!user.isPresent()) {
			log.error("Falha ao consultar dados do usuário no banco de dados");
			throw new ErroFluxoException("Usuário não encontrado");
		}
		
		pet.get().setMorador(user.get());
		pet.get().setNome(request.getNome());
		pet.get().setTipo(request.getTipo());
		
		try {
			this.petRepository.save(pet.get());
		} catch(IllegalArgumentException | PersistenceException e) {
			log.error("Falha ao salvar pet editado.");
			throw new ErroFluxoException("Falha ao salvar pet no banco de dados.");
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Pet editado com sucesso.");
		
		return response;
		
	}
	
	public PetResponseDTO deletarPet(Long id) {
		
		Optional<PetEntity> pet = this.petRepository.findById(id);
		
		if(!pet.isPresent()) {
			log.error("Falha ao consultar dados de pets no banco de dados");
			throw new ErroFluxoException("Pet não encontrado");
		}
		
		try {
			this.petRepository.delete(pet.get());
		} catch(IllegalArgumentException | PersistenceException e) {
			log.error("Falha ao deletar pet do banco de dados.");
			throw new ErroFluxoException("Falha ao deletar pet no banco de dados.");
		}
		
		PetResponseDTO response = new PetResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Pet deletado com sucesso.");
		
		return response;
	}
	
}
