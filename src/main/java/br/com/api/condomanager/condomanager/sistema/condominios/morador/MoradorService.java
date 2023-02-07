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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.MoradorRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.MoradorResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

@Service
public class MoradorService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	PasswordEncoder encoder;

	public MoradorResponseDTO cadastrarMorador(MoradorRequestDTO moradorRequest) {
		
		if(!usuarioRepository.existsByCpf(moradorRequest.getCpf().trim())) {
			UserEntity usuario = new UserEntity();
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
		
		if(condominio.isEmpty()) {
			throw new CondomanagerException("Condomínio inválido ou não encontrado.");
		}
		
		if(predio.isEmpty()) {
			throw new CondomanagerException("Prédio inválido ou não encontrado.");
		}
		
		if(predio.get().getIdCondominio().equals(moradorRequest.getIdCondominio())) {
			throw new CondomanagerException("O prédio não faz parte do condomínio. Verifique e tente novamente!");
		}
		
		return true;
	}
	
}
