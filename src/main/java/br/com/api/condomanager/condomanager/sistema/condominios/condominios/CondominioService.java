package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.CondominioProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class CondominioService {
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	Util utils;
	
	public CondominiosResponseDTO cadastrarCondominio(CondominiosRequestDTO request) {
			
		this.checkCondominio(request.getCnpj());
		
		CondominioEntity cond = new CondominioEntity();
		cond.setNome(request.getNome());
		cond.setCnpj(request.getCnpj());
		cond.setEmail(request.getEmail());
		cond.setEndereco(request.getEndereco().getEndereco());
		cond.setNumero(request.getEndereco().getNumero());
		cond.setBairro(request.getEndereco().getBairro());
		cond.setComplemento(request.getEndereco().getComplemento());
		cond.setIdCidade(request.getEndereco().getIdCidade());
		cond.setIdEstado(request.getEndereco().getIdEstado());
		
		condominioRepository.save(cond);
		
		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
		
		return response;
		
	}
	
	public List<CondominioProjection> buscarCondominios() {

		List<CondominioProjection> listCondominios = condominioRepository.findAllProjectedBy();
		
		if(!listCondominios.isEmpty()) {
			return listCondominios;
		}
		
		throw new ErroFluxoException("Nenhum condomínio cadastrado!");
	}
	
	private boolean checkCondominio(String cnpj) {
		
		CondominioEntity cond = condominioRepository.findByCnpj(cnpj);
		if(cond == null) {
			return true;
		}
		
		throw new ErroFluxoException("Condomínio já cadastrado!");
		
	}

	public CondominiosResponseDTO deletarCondominio(Long id) {

		Optional<CondominioEntity> cond = condominioRepository.findById(id);

		if(!cond.isPresent()) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}

		condominioRepository.delete(cond.get());

		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ cond.get().getNome() +"' foi salvo com sucesso!");

		return response;
	}
	
}
