package br.com.api.condomanager.condomanager.sistema.areacomum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.areacomum.dto.request.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.areacomum.dto.response.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

@Service
public class AreaComumService {
	
	@Autowired
	AutenticacaoService autenticationService;
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	public AreaComumResponseDTO cadastrarAreaComum(AreaComumRequestDTO request, String authorization) {
		
		this.autenticationService.validaUserToken(authorization);
		
		if(request != null) {
			
			AreaComumEntity area = new AreaComumEntity();
			area.setArea(request.getArea());
			
			Optional<CondominioEntity> condominio = this.condominioRepository.findById(request.getIdCondominio());
			
			if(condominio != null) {
				area.setCondominio(condominio.get().getNome());
				area.setIdCondominio(condominio.get().getId());
			} else {
				throw new CondomanagerException("Condomínio inexistente");
			}
			
			areaComumRepository.save(area);
			
			AreaComumResponseDTO response = new AreaComumResponseDTO();
			response.setArea(area.getArea());
			response.setCondominio(area.getCondominio());
			
			return response;
			
		}
		
		throw new CondomanagerException("Não foi possivel salvar a área comum, verifique os dados e tente novamente.");
	}
	
	public List<AreaComumResponseDTO> getAreaComum(String authorization) {
		
		this.autenticationService.validaUserToken(authorization);
		
		List<AreaComumEntity> listAreaComum = new ArrayList<>();
		listAreaComum = areaComumRepository.findAll();
		
		if(listAreaComum.size() > 0) {
			List<AreaComumResponseDTO> response = new ArrayList<>();
			
			for(int i=0; i < listAreaComum.size(); i++) { 
				AreaComumResponseDTO area = new AreaComumResponseDTO();
				area.setArea(listAreaComum.get(i).getArea());
				area.setCondominio(listAreaComum.get(i).getCondominio());
				
				response.add(area);
			}
			
			return response;
		}
		
		throw new CondomanagerException("Nenhuma área comum cadastrada!");
		
	}
	
}
