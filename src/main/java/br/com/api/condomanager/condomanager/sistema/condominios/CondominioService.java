package br.com.api.condomanager.condomanager.sistema.condominios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.CondominioResponse;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.util.Endereco;

@Service
public class CondominioService {

	@Autowired
	AutenticacaoService autenticationService;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	public CondominiosResponseDTO cadastrarCondominio(CondominiosRequestDTO request) {
		
		if(request != null) {
			
			if(this.checkCondominio(request.getCnpj())) {
				CondominioEntity cond = new CondominioEntity();
				cond.setNome(request.getNome());
				cond.setCnpj(request.getCnpj());
				cond.setEmail(request.getEmail());
				cond.setEndereco(request.getEndereco().getEndereco());
				cond.setNumero(request.getEndereco().getNumero());
				cond.setBairro(request.getEndereco().getBairro());
				cond.setComplemento(request.getEndereco().getComplemento());
				
				condominioRepository.save(cond);
				
				CondominiosResponseDTO response = new CondominiosResponseDTO();
				response.setCodigo(HttpStatus.OK.value());
				response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
				
				return response;
			}
		}
		
		throw new CondomanagerException("Não foi possivel salvar o condomínio, verifique os dados e tente novamente.");
		
	}
	
	public List<CondominioResponse> buscarCondominios() {
		
		List<CondominioEntity> listCondominios = new ArrayList<>();
		listCondominios = condominioRepository.findAll();
		
		if(!listCondominios.isEmpty()) {
			List<CondominioResponse> response = new ArrayList<>();
			
			for(CondominioEntity condominio : listCondominios) { 
				CondominioResponse cond = new CondominioResponse();
				cond.setNome(condominio.getNome());
				cond.setCnpj(condominio.getCnpj());
				cond.setEmail(condominio.getEmail());
				
				Endereco endereco = new Endereco();
				endereco.setEndereco(condominio.getEndereco());
				endereco.setBairro(condominio.getBairro());
				endereco.setNumero(condominio.getNumero());
				endereco.setComplemento(condominio.getComplemento());
				
				cond.setEndereco(endereco);
				
				response.add(cond);
			}
			
			return response;
		}
		
		throw new CondomanagerException("Nenhum condomínio cadastrado!");
	}
	
	private boolean checkCondominio(String cnpj) {
		
		CondominioEntity cond = condominioRepository.findByCnpj(cnpj);
		if(cond == null) {
			return true;
		}
		
		throw new CondomanagerException("Condomínio já cadastrado!");
		
	}
	
}
