package br.com.api.condomanager.condomanager.sistema.condominios.morador;

import java.util.Optional;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import br.com.api.condomanager.condomanager.repository.ApartamentoRepository;
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
import br.com.api.condomanager.condomanager.sistema.dto.MoradorRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.MoradorResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class MoradorService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	PredioRepository predioRepository;

	@Autowired
	ApartamentoRepository apartamentoRepository;

	@Autowired
	PasswordEncoder encoder;

	public MoradorResponseDTO cadastrarMorador(MoradorRequestDTO moradorRequest) {
		
		if(!usuarioRepository.existsByCpf(moradorRequest.getCpf().trim())) {
			UserEntity usuario = new UserEntity();
			usuario.setNome(moradorRequest.getNome());
			usuario.setCpf(moradorRequest.getCpf());
			usuario.setRg(moradorRequest.getRg());
			usuario.setEmail(moradorRequest.getEmail());
			usuario.setTelefone(moradorRequest.getTelefone());
			usuario.setDdd(moradorRequest.getDdd());
			usuario.setNivelAcesso(AcessoEnum.MORADOR.getNivel());
			
			Optional<CondominioEntity> condominio = condominioRepository.findById(moradorRequest.getIdCondominio());
			Optional<PredioEntity> predio = predioRepository.findById(moradorRequest.getIdPredio());
			Optional<ApartamentoEntity> apto = apartamentoRepository.findById(moradorRequest.getIdApto());
			
			if(!condominio.isPresent() || !predio.isPresent() || apto.isPresent()) {
				throw new ErroFluxoException("Falha ao consultar dados, verifique e tente novamente!");
			}
			
			if(validarCondominioPredio(moradorRequest)) {
				usuario.setCondominio(condominio.get());
				usuario.setPredio(predio.get());
			}

			usuario.setCondominio(condominio.get());
			usuario.setPredio(predio.get());
			usuario.setApartamento(apto.get());
			usuario.setSenha(this.encoder.encode(moradorRequest.getCpf()));
			
			usuarioRepository.save(usuario);
		} else {
			throw new DadosPessoaisException("Já existe um morador cadastrado com este CPF, verifique e tente novamente!");
		}
		
		MoradorResponseDTO moradorResponse = new MoradorResponseDTO();
		moradorResponse.setCodigo("200");
		moradorResponse.setMensagem("Morador cadastrado com sucesso!");
		
		return moradorResponse;
	}
	
	private boolean validarCondominioPredio(MoradorRequestDTO moradorRequest) {
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(moradorRequest.getIdCondominio());
		Optional<PredioEntity> predio = predioRepository.findById(moradorRequest.getIdPredio());
		
		if(!condominio.isPresent()) {
			throw new ErroFluxoException("Condomínio inválido ou não encontrado.");
		} else if(!predio.isPresent()) {
			throw new ErroFluxoException("Prédio inválido ou não encontrado.");
		} else if(!condominio.get().getId().equals(predio.get().getCondominio().getId())) {
			throw new ErroFluxoException("O prédio não faz parte do condomínio. Verifique e tente novamente!");
		}
		
		return true;
	}
	
}
