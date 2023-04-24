package br.com.api.condomanager.condomanager.sistema.condominios.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.AcessoEnum;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.MoradorRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.MoradorResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class MoradorService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	Util utils;
	
	@Autowired
	PasswordEncoder encoder;

	public MoradorResponseDTO cadastrarMorador(MoradorRequestDTO moradorRequest) {
		
		if(!usuarioRepository.existsByCpf(moradorRequest.getCpf().trim())) {
			UserEntity usuario = new UserEntity();
			usuario.setCodigo(utils.gerarCodigo("user"));
			usuario.setName(moradorRequest.getName());
			usuario.setCpf(moradorRequest.getCpf());
			usuario.setRg(moradorRequest.getRg());
			usuario.setEmail(moradorRequest.getEmail());
			usuario.setPhone(moradorRequest.getPhone());
			usuario.setIdAccess(AcessoEnum.MORADOR.getNivel());
			usuario.setTipo(AcessoEnum.MORADOR.getDescricao());
			
			CondominioEntity condominio;
			PredioEntity predio;
			
			if(validarCondominioPredio(moradorRequest)) {
				condominio = condominioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoCondominio()));
				predio = predioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoPredio()));
				usuario.setIdCondominio(condominio.getId());
				usuario.setIdPredio(predio.getId());
			}
			
			usuario.setCondominio(condominioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoCondominio())).getNome());
			usuario.setPredio(predioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoPredio())).getNome());
			usuario.setApto(moradorRequest.getApto());
			usuario.setPassword(this.encoder.encode(moradorRequest.getCpf()));
			
			usuarioRepository.save(usuario);
		} else {
			throw new DadosPessoaisException("CPF já cadastrado!");
		}
		
		MoradorResponseDTO moradorResponse = new MoradorResponseDTO();
		moradorResponse.setCodigo("200");
		moradorResponse.setMensagem("Morador cadastrado com sucesso!");
		
		return moradorResponse;
	}
	
	private boolean validarCondominioPredio(MoradorRequestDTO moradorRequest) {
		
		CondominioEntity condominio = condominioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoCondominio()));
		PredioEntity predio = predioRepository.findByCodigo(String.valueOf(moradorRequest.getCodigoPredio()));
		
		if(condominio != null) {
			throw new ErroFluxoException("Condomínio inválido ou não encontrado.");
		} else if(predio != null) {
			throw new ErroFluxoException("Prédio inválido ou não encontrado.");
		} else if(!condominio.getId().equals(predio.getIdCondominio())) {
			throw new ErroFluxoException("O prédio não faz parte do condomínio. Verifique e tente novamente!");
		}
		
		return true;
	}
	
}
