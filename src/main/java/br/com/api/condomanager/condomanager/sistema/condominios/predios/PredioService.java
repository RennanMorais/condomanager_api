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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.PredioRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.PredioResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

@Service
public class PredioService {
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;

	public PredioResponseDTO cadastrarPredio(PredioRequestDTO request) {
		
		if(request != null) {
			
			PredioEntity predio = new PredioEntity();
			predio.setNome(request.getNome());
			
			CondominioEntity condominio;
			
			try {
				condominio = this.condominioRepository.findById(request.getIdCondominio()).get();
			} catch(NoSuchElementException e) {
				throw new CondomanagerException("Condomínio invalido.");
			}
			
			if(condominio != null) {
				predio.setCondominio(condominio.getNome());
				predio.setIdCondominio(condominio.getId());
			} else {
				throw new CondomanagerException("Condomínio inexistente");
			}
			
			predioRepository.save(predio);
			
			PredioResponseDTO response = new PredioResponseDTO();
			response.setNome(predio.getNome());
			response.setCondominio(predio.getCondominio());
			
			return response;
		}
		
		throw new CondomanagerException("Não foi possivel salvar o prédio, verifique os dados e tente novamente.");
	}
	
	public List<PredioResponseDTO> getPredios() {
		
		List<PredioEntity> listPredios = new ArrayList<>();
		listPredios = predioRepository.findAll();
		
		if(!listPredios.isEmpty()) {
			List<PredioResponseDTO> response = new ArrayList<>();
			
			for(PredioEntity predioItem : listPredios) {
				PredioResponseDTO predio = new PredioResponseDTO();
				predio.setNome(predioItem.getNome());
				predio.setCondominio(predioItem.getCondominio());
				response.add(predio);
			}
			
			return response;
		}
		
		throw new CondomanagerException("Nenhum prédio cadastrado!");
		
	}
	
}
