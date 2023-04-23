package br.com.api.condomanager.condomanager.sistema.condominios.morador;

import java.util.Optional;

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
			
			if(validarCondominioPredio(moradorRequest)) {
				usuario.setIdCondominio(moradorRequest.getIdCondominio());
				usuario.setIdPredio(moradorRequest.getIdPredio());
			}
			
			usuario.setCondominio(condominioRepository.findById(moradorRequest.getIdCondominio()).get().getNome());
			usuario.setPredio(predioRepository.findById(moradorRequest.getIdPredio()).get().getNome());
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
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(moradorRequest.getIdCondominio());
		Optional<PredioEntity> predio = predioRepository.findById(moradorRequest.getIdPredio());
		
		if(condominio.isPresent()) {
			throw new ErroFluxoException("Condomínio inválido ou não encontrado.");
		}
		
		if(predio.isPresent()) {
			throw new ErroFluxoException("Prédio inválido ou não encontrado.");
		}
		
		if(predio.get().getIdCondominio().equals(moradorRequest.getIdCondominio())) {
			throw new ErroFluxoException("O prédio não faz parte do condomínio. Verifique e tente novamente!");
		}
		
		return true;
	}
	
}
