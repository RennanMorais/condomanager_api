package br.com.api.condomanager.condomanager.sistema.condominios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

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
		} else {
			throw new CondomanagerException("Não foi possivel salvar o condomínio, verifique os dados e tente novamente.");
		}
		
	}
	
}
