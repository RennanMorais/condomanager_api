package br.com.api.condomanager.condomanager.sistema.condominios;

import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidTokenException;

@Service
public class CondominioService {

	public CondominiosResponseDTO cadastrarPredio(CondominiosRequestDTO request, String authorization) {
		
		//TODO Verificar token do usuario com token do header
		if(authorization != null || authorization == "") {
			
		}
		
		throw new InvalidTokenException("Token Inválido");
	}
	
}
