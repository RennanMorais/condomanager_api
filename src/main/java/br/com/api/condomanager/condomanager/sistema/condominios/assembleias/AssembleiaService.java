package br.com.api.condomanager.condomanager.sistema.condominios.assembleias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.AssembleiaEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.AssembleiaRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.DateUtil;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class AssembleiaService {
	
	@Autowired
	AssembleiaRepository assembleiaRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	@Autowired
	Util utils;

    public AssembleiaResponseDTO agendarAssembleia(AssembleiaRequestDTO dto) {
    	
    	Date dataFormatada = DateUtil.toDate(dto.getData());
    	this.assembleiaDataCheck(dataFormatada);
    	
    	AssembleiaEntity assembleia = new AssembleiaEntity();
    	assembleia.setCodigo(utils.gerarCodigo("assembl"));
    	assembleia.setTitulo(dto.getTitulo());
    	assembleia.setDescricao(dto.getDescricao() == null ? "":dto.getDescricao());
    	assembleia.setData(DateUtil.toDate(dto.getData()));
    	
    	CondominioEntity cond = condominioRepository.findByCodigo(String.valueOf(dto.getCodigoCondominio()));
    	AreaComumEntity area = areaComumRepository.findByCodigo(String.valueOf(dto.getCodigoArea()));
    	
    	if(cond != null & area != null) {
    		if(cond.getId().equals(area.getIdCondominio())) {
    			assembleia.setIdCondominio(cond.getId());
            	assembleia.setCondominio(cond.getNome());
            	assembleia.setIdArea(area.getId());
            	assembleia.setLocalAreaComum(area.getArea());
            	assembleiaRepository.save(assembleia);
        	} else {
        		throw new ErroFluxoException("A área comum não pertence a este condominio.");
        	}
    	} else {
    		throw new ErroFluxoException("Condominio e/ou Área Comum invalidos ou não encontrados");
    	}
    	
    	AssembleiaResponseDTO response = new AssembleiaResponseDTO();
    	response.setCodigo(assembleia.getCodigo());
    	response.setCondominio(cond.getNome());
    	response.setTitulo(assembleia.getTitulo());
    	response.setData(DateUtil.dateToString(assembleia.getData()));
    	
        return response;
    }
    
    private boolean assembleiaDataCheck(Date data) {
		
		AssembleiaEntity ass = assembleiaRepository.findByDate(data);
		
		if(ass != null) {
			throw new ErroFluxoException("Já existe uma assembleia agendada nesta data!");
		}
		return true;
	}
    
    public List<AssembleiaResponseDTO> buscarAssembleias() {
    	
    	List<AssembleiaEntity> assembleias = assembleiaRepository.findAll();
    	List<AssembleiaResponseDTO> response = new ArrayList<>();
    	
    	if(assembleias != null) {
    		for(AssembleiaEntity a : assembleias) {
        		AssembleiaResponseDTO assResponse = new AssembleiaResponseDTO();
        		assResponse.setCodigo(a.getCodigo());
        		assResponse.setCondominio(a.getCondominio());
        		assResponse.setTitulo(a.getTitulo());
        		assResponse.setData(DateUtil.dateToString(a.getData()));
        		response.add(assResponse);
        	}
    	} else {
    		throw new ErroFluxoException("Nenhuma assembléia encontrada");
    	}
    	
    	return response;
    	
    }

}
