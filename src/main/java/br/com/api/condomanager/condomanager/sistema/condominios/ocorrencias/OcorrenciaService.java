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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.OcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.OcorrenciaResponseDTO;
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
		
		Optional<CondominioEntity> cond = condominioRepository.findById(request.getIdCondominio());
		
		if(!cond.isPresent()) {
			throw new ErroFluxoException("Código do condomínio invalido ou não encontrado!");
		}
		
		ocorrencia.setIdCondominio(cond.get().getId());
		ocorrencia.setCondominio(cond.get().getNome());
		
		UserEntity user = userRepository.findByCpf(request.getCpfMorador());
		
		if(user == null) {
			throw new ErroFluxoException("Cpf do morador invalido ou não encontrado!");
		}
		
		ocorrencia.setIdMorador(user.getId());
		ocorrencia.setMorador(user.getName());
		ocorrencia.setContato(user.getPhone());
		ocorrencia.setStatus(OcorrenciaStatusEnum.PENDENTE.getDescricaoStatus());
		
		ocorrenciaRepository.save(ocorrencia);
		
		OcorrenciaResponseDTO response = new OcorrenciaResponseDTO(
				String.valueOf(HttpStatus.ACCEPTED.value()), 
				"Ocorrência registrada com sucesso, aguarde o atendimento.");
		return response;
	}
	
	public OcorrenciaResponseDTO atenderOcorrencia(Long idOcorrencia) {
		
		Optional<OcorrenciaEntity> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
		
		if(!ocorrencia.isPresent()) {
			throw new ErroFluxoException("Código da ocorrência invalido ou não encontrado!");
		}
		
		if(ocorrencia.get().getStatus().equalsIgnoreCase(OcorrenciaStatusEnum.EM_ANDAMENTO.getDescricaoStatus())
				|| ocorrencia.get().getStatus().equalsIgnoreCase(OcorrenciaStatusEnum.FINALIZADO.getDescricaoStatus())) {
			throw new ErroFluxoException("Ocorrencia já está em atendimento ou foi finalizada");
		}
		
		String loginUser = userDetails.getLoginUser();
		UserEntity user = userRepository.findByEmail(loginUser.trim());
		
		if(user != null) {
			if(!user.getIdAccess().equals(AcessoEnum.ADMINISTRADOR.getNivel())) {
				throw new ErroFluxoException("Você não tem permissão para realizar atender ocorrências");
			}
		}
		
		ocorrencia.get().setStatus(OcorrenciaStatusEnum.EM_ANDAMENTO.getDescricaoStatus());
		ocorrenciaRepository.save(ocorrencia.get());
		
		OcorrenciaResponseDTO response = new OcorrenciaResponseDTO(
				String.valueOf(HttpStatus.OK.value()), 
				"Ocorrência agora está em andamento");
		
		return response;
	}
	
}
