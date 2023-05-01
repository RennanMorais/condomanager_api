package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.PredioProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class PredioService {
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	Util utils;

	public PredioResponseDTO cadastrarPredio(PredioRequestDTO request) {
		
		PredioEntity predio = new PredioEntity();
		predio.setNome(request.getNome());
		
		Optional<CondominioEntity> condominio = this.condominioRepository.findById(request.getIdCondominio());
		
		if(condominio.isPresent()) {
			predio.setIdCondominio(condominio.get().getId());
		} else {
			throw new ErroFluxoException("Condomínio inexistente");
		}
		
		predioRepository.save(predio);
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Predio cadastrado com sucesso!");
		
		return response;
	}
	
	public List<PredioProjection> getPredios() {
		
		List<PredioProjection> listaPredios = predioRepository.findAllProjectedBy();
		
		if(listaPredios != null) {
			return listaPredios;
		}
		
		throw new ErroFluxoException("Nenhum prédio cadastrado!");
		
	}
	
	public PredioResponseDTO deletarPredio(Long idPredio) {
		
		Optional<PredioEntity> predio = predioRepository.findById(idPredio);
		
		if(!predio.isPresent()) {
			throw new ErroFluxoException("Prédio não encontrado!");
		}
		
		predioRepository.delete(predio.get());
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Predio deletado com sucesso!");
		return response;
	}
	
}
