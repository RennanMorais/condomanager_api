package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PavimentoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PavimentoRepository;
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
	
	@Autowired
	PavimentoRepository pavimentoRepository;

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
		
		this.inserirPavimentos(request.getQtdPavimentos(), predio.getId());
		
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
	
	public PredioProjection getPredio(Long id) {
		
		PredioProjection listaPredios = predioRepository.findProjectionById(id);
		
		if(listaPredios != null) {
			return listaPredios;
		}
		
		throw new ErroFluxoException("Nenhum prédio cadastrado!");
		
	}
	
	public PredioResponseDTO editarPredio(Long idPredio, PredioRequestDTO request) {
    	
    	Optional<PredioEntity> predio = predioRepository.findById(idPredio);
    	
    	if(!predio.isPresent()) {
    		throw new ErroFluxoException("Prédio não encontrado!");
    	}
    	
    	Optional<CondominioEntity> condominio = this.condominioRepository.findById(request.getIdCondominio());
		
		if(!condominio.isPresent()) {
			throw new ErroFluxoException("Condomínio inexistente");
		}
    	
    	predio.get().setNome(request.getNome());
    	predio.get().setCondominio(condominio.get());;
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
			throw new ErroFluxoException("Falha ao deletar o predio no banco de dados.");
		}
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Predio deletado com sucesso!");
		return response;
	}
	
	private void inserirPavimentos(Integer qtdPav, Long idPredio) {
		
		Optional<PredioEntity> predio = predioRepository.findById(idPredio);
		
		if(!predio.isPresent()) {
			throw new ErroFluxoException("Prédio não encontrado!");
		}
		
		for(Integer i = 0; i <= qtdPav; i++) {
			
			PavimentoEntity pavimento = new PavimentoEntity();
			if(i == 0) {
				pavimento.setPavimento("T");
				pavimento.setPredio(idPredio);
			} else {
				pavimento.setPavimento(String.valueOf(i));
				pavimento.setPredio(idPredio);
			}
			this.pavimentoRepository.save(pavimento);
		}
	}
	
}
