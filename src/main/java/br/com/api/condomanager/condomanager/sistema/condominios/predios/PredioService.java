package br.com.api.condomanager.condomanager.sistema.condominios.predios;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.PredioResponseDTO;
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
		predio.setCodigo(utils.gerarCodigo("pred"));
		predio.setNome(request.getNome());
		
		CondominioEntity condominio;
		
		try {
			condominio = this.condominioRepository.findByCodigo(String.valueOf(request.getCodigoCondominio()));
		} catch(NoSuchElementException e) {
			throw new ErroFluxoException("Condomínio invalido.");
		}
		
		if(condominio != null) {
			predio.setCondominio(condominio.getNome());
			predio.setIdCondominio(condominio.getId());
		} else {
			throw new ErroFluxoException("Condomínio inexistente");
		}
		
		predioRepository.save(predio);
		
		PredioResponseDTO response = new PredioResponseDTO();
		response.setCodigo(predio.getCodigo());
		response.setNome(predio.getNome());
		response.setCondominio(predio.getCondominio());
		
		return response;
	}
	
	public List<PredioResponseDTO> getPredios() {
		
		List<PredioEntity> listPredios = new ArrayList<>();
		listPredios = predioRepository.findAll();
		
		if(!listPredios.isEmpty()) {
			List<PredioResponseDTO> response = new ArrayList<>();
			
			for(PredioEntity predioItem : listPredios) {
				PredioResponseDTO predio = new PredioResponseDTO();
				predio.setCodigo(predioItem.getCodigo());
				predio.setNome(predioItem.getNome());
				predio.setCondominio(predioItem.getCondominio());
				response.add(predio);
			}
			
			return response;
		}
		
		throw new ErroFluxoException("Nenhum prédio cadastrado!");
		
	}
	
}
