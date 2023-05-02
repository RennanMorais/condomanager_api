package br.com.api.condomanager.condomanager.sistema.condominios.assembleias;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AssembleiaProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.DateUtil;

@Service
public class AssembleiaService {
	
	@Autowired
	AssembleiaRepository assembleiaRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	AreaComumRepository areaComumRepository;

    public AssembleiaResponseDTO agendarAssembleia(AssembleiaRequestDTO dto) {
    	
    	Date dataFormatada = DateUtil.toDate(dto.getData());
    	this.assembleiaDataCheck(dataFormatada);
    	
    	AssembleiaEntity assembleia = new AssembleiaEntity();
    	assembleia.setTitulo(dto.getTitulo());
    	assembleia.setDescricao(dto.getDescricao() == null ? "":dto.getDescricao());
    	assembleia.setData(DateUtil.toDate(dto.getData()));
    	assembleia.setHora(LocalDateTime.now());
    	
    	Optional<CondominioEntity> cond = condominioRepository.findById(dto.getIdCondominio());
    	Optional<AreaComumEntity> area = areaComumRepository.findById(dto.getIdAreaComum());
    	
    	if(cond.isPresent() & area.isPresent()) {
    		if(cond.get().getId().equals(area.get().getIdCondominio())) {
    			assembleia.setIdCondominio(cond.get().getId());
            	assembleia.setIdAreaComum(area.get().getId());
            	assembleiaRepository.save(assembleia);
        	} else {
        		throw new ErroFluxoException("A área comum não pertence a este condominio.");
        	}
    	} else {
    		throw new ErroFluxoException("Condominio e/ou Área Comum invalidos ou não encontrados");
    	}
    	
    	AssembleiaResponseDTO response = new AssembleiaResponseDTO();
    	response.setCodigo("200");
		response.setMensagem("Assembleia agendada com sucesso!");
    	
        return response;
    }
    
    private boolean assembleiaDataCheck(Date data) {
		
		AssembleiaEntity ass = assembleiaRepository.findByDate(data);
		
		if(ass != null) {
			throw new ErroFluxoException("Já existe uma assembleia agendada nesta data!");
		}
		return true;
	}
    
    public List<AssembleiaProjection> buscarAssembleias() {
    	
    	List<AssembleiaProjection> assembleias = assembleiaRepository.findAllProjectedBy();
    	
    	if(assembleias == null) {
    		throw new ErroFluxoException("Nenhuma assembléia encontrada");
    	}
    	
    	return assembleias;
    	
    }

	public AssembleiaResponseDTO deletarAssembleia(Long id) {

		Optional<AssembleiaEntity> assembleia = assembleiaRepository.findById(id);

		if(!assembleia.isPresent()) {
			throw new ErroFluxoException("Agendamento não encontrado!");
		}

		assembleiaRepository.delete(assembleia.get());

		AssembleiaResponseDTO response = new AssembleiaResponseDTO();
		response.setCodigo("200");
		response.setMensagem("Assembleia deletada com sucesso!");

		return response;
	}

}
