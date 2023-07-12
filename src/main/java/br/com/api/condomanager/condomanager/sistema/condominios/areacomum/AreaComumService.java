package br.com.api.condomanager.condomanager.sistema.condominios.areacomum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.AreaComumRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.AreaComumResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.AreaComumProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class AreaComumService {
	
	@Autowired
	private AreaComumRepository areaComumRepository;
	
	@Autowired
	private CondominioRepository condominioRepository;
	
	public AreaComumResponseDTO cadastrarAreaComum(AreaComumRequestDTO request) {

		AreaComumEntity area = new AreaComumEntity();
		area.setNome(request.getArea());
		
		CondominioEntity condominio = this.condominioRepository.findById(request.getIdCondominio()).get();
		
		if(condominio != null) {
			area.setCondominio(condominio);
		} else {
			throw new CondomanagerException("Condomínio inexistente");
		}
		
		areaComumRepository.save(area);
		
		AreaComumResponseDTO response = new AreaComumResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Área comum cadastrada com sucesso!");
		
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

	public AreaComumProjection getAreaComum(Long id) {

		AreaComumProjection areaComum = areaComumRepository.findProjectedById(id);

		if(areaComum == null) {
			throw new ErroFluxoException("Área comum não encontrada.");
		}

		return areaComum;

	}

	public AreaComumResponseDTO editarAreaComum(Long id, AreaComumRequestDTO request) {

		Optional<AreaComumEntity> areaComum = areaComumRepository.findById(id);

		if(!areaComum.isPresent()) {
			throw new ErroFluxoException("Área comum não encontrada.");
		}

		areaComum.get().setNome(request.getArea());
		areaComum.get().getCondominio().setId(request.getIdCondominio());

		areaComumRepository.save(areaComum.get());

		AreaComumResponseDTO response = new AreaComumResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Área comum editada com sucesso");
		return response;
	}

	public AreaComumResponseDTO deletarAreaComum(Long id) {

		Optional<AreaComumEntity> areaComum = areaComumRepository.findById(id);

		if(!areaComum.isPresent()) {
			throw new ErroFluxoException("Área comum não encontrada.");
		}

		areaComumRepository.delete(areaComum.get());

		AreaComumResponseDTO response = new AreaComumResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Área comum excluída com sucesso");
		return response;
	}
	
}
