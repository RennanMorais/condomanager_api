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
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class AreaComumService {
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	Util utils;
	
	public AreaComumResponseDTO cadastrarAreaComum(AreaComumRequestDTO request) {

		AreaComumEntity area = new AreaComumEntity();
		area.setCodigo(utils.gerarCodigo("area"));
		area.setArea(request.getArea());
		
		CondominioEntity condominio = this.condominioRepository.findByCodigo(String.valueOf(request.getCodigoCondominio()));
		
		if(condominio != null) {
			area.setCondominio(condominio.getNome());
			area.setIdCondominio(condominio.getId());
		} else {
			throw new CondomanagerException("Condomínio inexistente");
		}
		
		areaComumRepository.save(area);
		
		AreaComumResponseDTO response = new AreaComumResponseDTO();
		response.setCodigo(area.getCodigo());
		response.setArea(area.getArea());
		response.setCondominio(area.getCondominio());
		
		return response;

	}
	
	public List<AreaComumResponseDTO> listarAreaComum() {
		
		List<AreaComumEntity> listAreaComum = new ArrayList<>();
		listAreaComum = areaComumRepository.findAll();
		
		if(!listAreaComum.isEmpty()) {
			List<AreaComumResponseDTO> response = new ArrayList<>();
			
			for(AreaComumEntity areaItem : listAreaComum) { 
				AreaComumResponseDTO area = new AreaComumResponseDTO();
				area.setCodigo(areaItem.getCodigo());
				area.setArea(areaItem.getArea());
				area.setCondominio(areaItem.getCondominio());
				
				response.add(area);
			}
			
			return response;
		}
		
		throw new ErroFluxoException("Nenhuma área comum cadastrada!");
		
	}
	
}
