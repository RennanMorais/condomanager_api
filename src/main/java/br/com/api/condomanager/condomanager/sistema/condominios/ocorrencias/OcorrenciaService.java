package br.com.api.condomanager.condomanager.sistema.condominios.ocorrencias;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.enums.AcessoEnum;
import br.com.api.condomanager.condomanager.enums.OcorrenciaStatusEnum;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.OcorrenciaEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.OcorrenciaRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.FinalizarOcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.DateUtil;

@Service
public class OcorrenciaService {

	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	MyUserDetails userDetails;
	
	public OcorrenciaResponseDTO registrarOcorrencia(OcorrenciaRequestDTO request) {
		
		OcorrenciaEntity ocorrencia = new OcorrenciaEntity();
		ocorrencia.setData(DateUtil.toDate(request.getData()));
		ocorrencia.setDescricao(request.getDescricao());
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(request.getIdCondominio());
		
		if(!condominio.isPresent()) {
			throw new ErroFluxoException("Código do condomínio invalido ou não encontrado!");
		}
		
		ocorrencia.setIdCondominio(condominio.get().getId());
		
		UserEntity user = userRepository.findByCpf(request.getCpfMorador());
		
		if(user == null) {
			throw new ErroFluxoException("Cpf do morador invalido ou não encontrado!");
		}
		
		ocorrencia.setIdMorador(user.getId());
		ocorrencia.setIdStatus(OcorrenciaStatusEnum.PENDENTE.getIdStatus());
		
		ocorrenciaRepository.save(ocorrencia);
		
		OcorrenciaResponseDTO response = new OcorrenciaResponseDTO(
				String.valueOf(HttpStatus.ACCEPTED.value()), 
				"Ocorrência registrada com sucesso, aguarde o atendimento.");
		
		return response;
	}
	
	public OcorrenciaResponseDTO atenderOcorrencia(Long idOcorrencia) {
		
		OcorrenciaEntity ocorrencia = this.validarOcorrencia(idOcorrencia, "atender");
		
		ocorrencia.setIdStatus(OcorrenciaStatusEnum.EM_ANDAMENTO.getIdStatus());
		ocorrenciaRepository.save(ocorrencia);
		
		OcorrenciaResponseDTO response = new OcorrenciaResponseDTO(
				String.valueOf(HttpStatus.OK.value()), 
				"Ocorrência agora está em andamento");
		
		return response;
	}
	
	public OcorrenciaResponseDTO finalizarOcorrencia(Long codigoOcorrencia, FinalizarOcorrenciaRequestDTO request) {
		
		OcorrenciaEntity ocorrencia = this.validarOcorrencia(codigoOcorrencia, "finalizar");
		
		ocorrencia.setIdStatus(OcorrenciaStatusEnum.FINALIZADO.getIdStatus());
		ocorrencia.setResposta(request.getResposta());
		ocorrenciaRepository.save(ocorrencia);
		
		OcorrenciaResponseDTO response = new OcorrenciaResponseDTO(
				String.valueOf(HttpStatus.OK.value()), 
				"Ocorrência finalizada, verifique a mensagem de feedback.");
		
		return response;
	}

	private OcorrenciaEntity validarOcorrencia(Long idOcorrencia, String chamada) {
		Optional<OcorrenciaEntity> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
		
		if(!ocorrencia.isPresent()) {
			throw new ErroFluxoException("Código da ocorrência invalido ou não encontrado!");
		}
		
		if(chamada.equalsIgnoreCase("atender")) {
			if(ocorrencia.get().getIdStatus().equals(OcorrenciaStatusEnum.EM_ANDAMENTO.getIdStatus())
					|| ocorrencia.get().getIdStatus().equals(OcorrenciaStatusEnum.FINALIZADO.getIdStatus())) {
				throw new ErroFluxoException("Ocorrencia já está em atendimento ou foi finalizada");
			}
		} else if(chamada.equalsIgnoreCase("finalizar")) {
			if(ocorrencia.get().getIdStatus().equals(OcorrenciaStatusEnum.PENDENTE.getIdStatus())
					|| ocorrencia.get().getIdStatus().equals(OcorrenciaStatusEnum.FINALIZADO.getIdStatus())) {
				throw new ErroFluxoException("Ocorrencia ainda está pendente ou já foi finalizada");
			}
		}
		
		String loginUser = userDetails.getLoginUser();
		UserEntity user = userRepository.findByEmail(loginUser.trim());
		
		if(user != null) {
			if(!user.getIdNivelAcesso().equals(AcessoEnum.ADMINISTRADOR.getNivel())) {
				throw new ErroFluxoException("Você não tem permissão para realizar atender ocorrências");
			}
		}
		
		return ocorrencia.get();
	}
	
}
