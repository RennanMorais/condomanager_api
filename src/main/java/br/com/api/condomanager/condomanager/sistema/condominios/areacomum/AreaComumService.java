package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AreaComumProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class AreaComumService {
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	public AreaComumResponseDTO cadastrarAreaComum(AreaComumRequestDTO request) {

		AreaComumEntity area = new AreaComumEntity();
		area.setNome(request.getArea());
		
		CondominioEntity condominio = this.condominioRepository.findById(request.getIdCondominio()).get();
		
		if(condominio != null) {
			area.setIdCondominio(condominio.getId());
		} else {
			throw new CondomanagerException("Condomínio inexistente");
		}
		
		areaComumRepository.save(area);
		
		AreaComumResponseDTO response = new AreaComumResponseDTO();
		response.setArea(area.getNome());
		response.setCondominio(condominio.getNome());
		response.setIdCondominio(condominio.getId());
		
		return response;

	}
	
	public List<AreaComumProjection> listarAreaComum() {
		
		List<AreaComumProjection> listaAreas = new ArrayList<>();
		listaAreas = areaComumRepository.findAllProjectedBy();
		
		if(!listaAreas.isEmpty()) {
			return listaAreas;
		}
		
		throw new ErroFluxoException("Nenhuma área comum cadastrada!");
		
	}
	
}
