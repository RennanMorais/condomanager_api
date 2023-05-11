package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.api.condomanager.condomanager.sistema.dto.projection.ReservaProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.ReservaStatusEnum;
import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.ReservaEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.ReservaRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.AprovarReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ReservaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ReservasDadosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.DateUtil;

@Service
public class ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	public ReservaResponseDTO reservar(ReservaRequestDTO request) {
		
			
		CondominioEntity condominio;
		UserEntity usuario;
		AreaComumEntity areaComum;
		
		try {
			condominio = this.buscarDadosCondominio(request.getIdCondominio());
			usuario = this.buscarUsuario(request.getIdMorador());
			areaComum = this.buscarNomeAreaComum(request.getIdAreaComum());
		} catch (Exception e) {
			throw new ErroFluxoException("Falha ao consultar dados.");
		}
		
		ReservaEntity reserva = new ReservaEntity();
		reserva.setCondominio(condominio);
		reserva.setMorador(usuario);
		reserva.setAreaComum(areaComum);
		reserva.setEvento(request.getEvento());
		
		Date dataFormatada = DateUtil.toDate(request.getData());
		this.reservaDataCheck(condominio.getId(), areaComum.getId(), dataFormatada);
		
		reserva.setData(dataFormatada);
		reserva.setStatus(ReservaStatusEnum.PENDENTE.getCodigo());
		
		reservaRepository.save(reserva);
		
		ReservaResponseDTO response = new ReservaResponseDTO();
		response.setEvento(reserva.getEvento());
		response.setData(DateUtil.dateToString(reserva.getData()));
		
		return response;
	}
	
	public List<ReservaProjection> listarReservas() {

		List<ReservaProjection> reservas = this.reservaRepository.findAllProjectedBy();
		
		if(reservas == null || reservas.isEmpty()) {
			throw new ErroFluxoException("Nennuma reserva registrada.");
		}

		return reservas;
	}
	
	private boolean reservaDataCheck(Long idCondominio, Long idArea, Date data) {

		ReservaEntity reserva = this.reservaRepository.findByDate(idCondominio, idArea, data);

		if(reserva != null) {
			throw new ErroFluxoException("Já existe uma reserva nesta data!");
		}
		return true;
	}
	
	private CondominioEntity buscarDadosCondominio(Long idCondominio) {
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(idCondominio);
		
		if(condominio.isPresent()) {
			return condominio.get();
		}
		
		throw new ErroFluxoException("Condominio não encontrado");
	}
	
	private UserEntity buscarUsuario(Long idUsuario) {
		
		Optional<UserEntity> usuario = usuarioRepository.findById(idUsuario);
		
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new ErroFluxoException("Usuário não encontrado");
	}
	
	private AreaComumEntity buscarNomeAreaComum(Long idAreaComum) {
		
		Optional<AreaComumEntity> area = areaComumRepository.findById(idAreaComum);
		
		if(area.isPresent()) {
			return area.get();
		}
		
		throw new ErroFluxoException("Falha ao consultar nome da área comum.");
	}
	
	public AprovarReservaResponseDTO aprovarReserva(Long id) {
		
		if(id != null) {
			Optional<ReservaEntity> reserva = reservaRepository.findById(id);
			if(reserva.get() != null) {
				
				if(ReservaStatusEnum.APROVADO.getCodigo().equals(reserva.get().getStatus())
						|| ReservaStatusEnum.CANCELADO.getCodigo().equals(reserva.get().getStatus())) {
					AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
					response.setCodigo("400");
					response.setMensagem("Não é possivel aprovar uma reserva com status Aprovada ou Cancelada.");
					
					return response;
				}
				
				reserva.get().setStatus(ReservaStatusEnum.APROVADO.getCodigo());
				reservaRepository.save(reserva.get());
				
				AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
				response.setCodigo("200");
				response.setMensagem("Reserva do evento "+ reserva.get().getEvento() + " foi aprovada com sucesso.");
				
				return response;
			}
			
			throw new ErroFluxoException("Falha ao aprovar reserva. Reserva não encontrada.");
		}
		
		throw new ErroFluxoException("Falha ao aprovar reserva. Tente novamente.");
	}
	
	public AprovarReservaResponseDTO cancelarReserva(Long id) {
		
		if(id != null) {
			Optional<ReservaEntity> reserva = reservaRepository.findById(id);
			if(reserva.get() != null) {
				
				if(ReservaStatusEnum.APROVADO.getCodigo().equals(reserva.get().getStatus())
						|| ReservaStatusEnum.CANCELADO.getCodigo().equals(reserva.get().getStatus())) {
					AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
					response.setCodigo("400");
					response.setMensagem("Não é possivel aprovar uma reserva com status Aprovada ou Cancelada.");
					
					return response;
				}
				
				reserva.get().setStatus(ReservaStatusEnum.CANCELADO.getCodigo());
				reservaRepository.save(reserva.get());
				
				AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
				response.setCodigo("200");
				response.setMensagem("Reserva do evento "+ reserva.get().getEvento() + " foi cancelada com sucesso.");
				
				return response;
			}
			
			throw new ErroFluxoException("Falha ao cancelar reserva. Reserva não encontrada.");
		}
		
		throw new ErroFluxoException("Falha ao cancelar reserva. Tente novamente.");
	}
}
