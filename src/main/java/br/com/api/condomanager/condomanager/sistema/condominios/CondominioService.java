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
	
	public CondominiosResponseDTO cadastrarCondominio(CondominiosRequestDTO request, String authorization) {
		
		this.autenticationService.validaUserToken(authorization);
		
		if(request != null) {
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
		
		throw new CondomanagerException("Não foi possivel salvar o condomínio, verifique os dados e tente novamente.");
		
	}
	
	public List<CondominioResponse> getCondominios(String authorization) {
		
		this.autenticationService.validaUserToken(authorization);
		
		List<CondominioEntity> listCondominios = new ArrayList<>();
		listCondominios = condominioRepository.findAll();
		
		if(!listCondominios.isEmpty()) {
			List<CondominioResponse> response = new ArrayList<>();
			
			for(int i=0; i < listCondominios.size(); i++) { 
				CondominioResponse cond = new CondominioResponse();
				Endereco endereco = new Endereco();
				cond.setNome(listCondominios.get(i).getNome());
				cond.setCnpj(listCondominios.get(i).getCnpj());
				cond.setEmail(listCondominios.get(i).getEmail());
				
				endereco.setEndereco(listCondominios.get(i).getEndereco());
				endereco.setBairro(listCondominios.get(i).getBairro());
				endereco.setNumero(listCondominios.get(i).getNumero());
				endereco.setComplemento(listCondominios.get(i).getComplemento());
				
				cond.setEndereco(endereco);
				
				response.add(cond);
			}
			
			return response;
		}
		
		throw new CondomanagerException("Nenhum condomínio cadastrado!");
	}
	
}
