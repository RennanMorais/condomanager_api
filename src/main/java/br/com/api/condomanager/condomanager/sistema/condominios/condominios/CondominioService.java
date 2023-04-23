package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominioResponse;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Endereco;
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
		cond.setCodigo(utils.gerarCodigo("cond"));
		cond.setNome(request.getNome());
		cond.setCnpj(request.getCnpj());
		cond.setEmail(request.getEmail());
		cond.setEndereco(request.getEndereco().getEndereco());
		cond.setNumero(request.getEndereco().getNumero());
		cond.setBairro(request.getEndereco().getBairro());
		cond.setComplemento(request.getEndereco().getComplemento());
		cond.setCidade(request.getEndereco().getCidade());
		cond.setEstado(request.getEndereco().getEstado());
		
		condominioRepository.save(cond);
		
		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
		
		return response;
		
	}
	
	public List<CondominioResponse> buscarCondominios() {

		List<CondominioEntity> listCondominios = condominioRepository.findAll();
		
		if(!listCondominios.isEmpty()) {
			List<CondominioResponse> response = new ArrayList<>();
			
			for(CondominioEntity condominio : listCondominios) { 
				CondominioResponse cond = new CondominioResponse();
				cond.setCodigo(condominio.getCodigo());
				cond.setNome(condominio.getNome());
				cond.setCnpj(condominio.getCnpj());
				cond.setEmail(condominio.getEmail());
				
				Endereco endereco = new Endereco();
				endereco.setEndereco(condominio.getEndereco());
				endereco.setBairro(condominio.getBairro());
				endereco.setNumero(condominio.getNumero());
				endereco.setComplemento(condominio.getComplemento());
				endereco.setCidade(condominio.getCidade());
				endereco.setEstado(condominio.getEstado());
				
				cond.setEndereco(endereco);
				
				response.add(cond);
			}
			
			return response;
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
	
}
