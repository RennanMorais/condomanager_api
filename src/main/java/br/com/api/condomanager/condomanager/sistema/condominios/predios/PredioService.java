package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.PredioProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredioService {
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;

	public PredioResponseDTO cadastrarPredio(PredioRequestDTO request) {
		
		PredioEntity predio = new PredioEntity();
		predio.setNome(request.getNome());
		
		Optional<CondominioEntity> condominio = this.condominioRepository.findById(request.getIdCondominio());
		
		if(condominio.isPresent()) {
			predio.setCondominio(condominio.get());
		} else {
			throw new ErroFluxoException("Condomínio inexistente");
		}
		
		predioRepository.save(predio);
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Predio cadastrado com sucesso!");
		
		return response;
	}
	
	
	//Verificar
	public List<PredioProjection> getPrediosPorCondominio(Long id) {
		
		Optional<CondominioEntity> condominio = this.condominioRepository.findById(id);
		
		if(!condominio.isPresent()) {
			throw new ErroFluxoException("Condomínio inexistente");
		}
		
		List<PredioProjection> predios = this.predioRepository.findAllProjectedByCondominio(condominio.get());
		
		if(predios == null) {
			throw new ErroFluxoException("Condomínio inexistente");
		}
		
		return predios;
	}
	
	public List<PredioProjection> getPredios() {
		
		List<PredioProjection> listaPredios = predioRepository.findAllProjectedBy();
		
		if(listaPredios != null) {
			return listaPredios;
		}
		
		throw new ErroFluxoException("Nenhum prédio cadastrado!");
		
	}
	
	public PredioResponseDTO editarPredio(Long idPredio, PredioRequestDTO request) {
    	
    	Optional<PredioEntity> predio = predioRepository.findById(idPredio);
    	
    	if(!predio.isPresent()) {
    		throw new ErroFluxoException("Apartamento não encontrado!");
    	}
    	
    	predio.get().setNome(null);
    	predioRepository.save(predio.get());
    	
    	PredioResponseDTO response = new PredioResponseDTO();
        response.setCodigo("200");
        response.setMensagem("Prédio editado com sucesso!");

        return response;
    }
	
	public PredioResponseDTO deletarPredio(Long idPredio) {
		
		Optional<PredioEntity> predio = predioRepository.findById(idPredio);
		
		if(!predio.isPresent()) {
			throw new ErroFluxoException("Prédio não encontrado!");
		}
		
		try {
			this.predioRepository.delete(predio.get());
		} catch(IllegalArgumentException | PersistenceException e) {
			log.error("Falha ao deletar predio do banco de dados.");
			throw new ErroFluxoException("Falha ao deletar pet no banco de dados.");
		}
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Predio deletado com sucesso!");
		return response;
	}
	
}
