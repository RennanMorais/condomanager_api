package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AprovarReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.ReservaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.ReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.ReservasDadosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.DateUtil;
import br.com.api.condomanager.condomanager.util.Util;

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
	
	@Autowired
	Util utils;
	
	public ReservaResponseDTO reservar(ReservaRequestDTO request) {
		
			
		CondominioEntity condominio;
		UserEntity usuario;
		AreaComumEntity areaComum;
		
		try {
			condominio = this.buscarDadosCondominio(request.getCodigoCondominio());
			usuario = this.buscarUsuario(request.getCodigoMorador());
			areaComum = this.buscarNomeAreaComum(request.getCodigoArea());
		} catch (Exception e) {
			throw new ErroFluxoException("Falha ao consultar dados.");
		}
		
		ReservaEntity reserva = new ReservaEntity();
		reserva.setCodigo(utils.gerarCodigo("reserv"));
		reserva.setIdCondominio(condominio.getId());
		reserva.setCondominio(condominio.getNome());
		reserva.setIdMorador(usuario.getId());
		reserva.setMorador(usuario.getName());
		reserva.setIdArea(areaComum.getId());
		reserva.setArea(areaComum.getArea());
		reserva.setEvento(request.getEvento());
		
		Date dataFormatada = DateUtil.toDate(request.getData());
		this.reservaDataCheck(condominio.getId(), areaComum.getId(), dataFormatada);
		
		reserva.setData(dataFormatada);
		reserva.setStatus(ReservaStatusEnum.PENDENTE.getDescricao());
		
		reservaRepository.save(reserva);
		
		ReservaResponseDTO response = new ReservaResponseDTO();
		response.setCodigo(reserva.getCodigo());
		response.setEvento(reserva.getEvento());
		response.setData(DateUtil.dateToString(reserva.getData()));
		
		return response;
	}
	
	public List<ReservasDadosResponseDTO> listarReservas() {
		
		List<ReservasDadosResponseDTO> listaResponse = new ArrayList<>();
		List<ReservaEntity> reservas = this.reservaRepository.findAll();
		
		if(reservas == null || reservas.isEmpty()) {
			throw new ErroFluxoException("Nennuma reserva registrada.");
		}
		
		for(ReservaEntity r : reservas) {
			ReservasDadosResponseDTO response = new ReservasDadosResponseDTO();
			response.setCodigo(r.getCodigo());
			response.setCondominio(r.getCondominio());
			response.setMorador(r.getMorador());
			response.setArea(r.getArea());
			response.setEvento(r.getEvento());
			response.setData(DateUtil.dateToString(r.getData()));
			response.setStatus(r.getStatus());
			listaResponse.add(response);
		}
		
		return listaResponse;
	}
	
	private boolean reservaDataCheck(Long idCondominio, Long idArea, Date data) {
		
		ReservaEntity reserva = this.reservaRepository.findByDate(idCondominio, idArea, data);
		
		if(reserva != null) {
			throw new ErroFluxoException("Já existe uma reserva nesta data!");
		}
		return true;
	}
	
	private CondominioEntity buscarDadosCondominio(Long codigoCondominio) {
		
		CondominioEntity condominio = condominioRepository.findByCodigo(String.valueOf(codigoCondominio));
		
		if(condominio != null) {
			return condominio;
		}
		
		throw new ErroFluxoException("Condominio não encontrado");
	}
	
	private UserEntity buscarUsuario(Long codigoUsuario) {
		
		UserEntity usuario = usuarioRepository.findByCodigo(String.valueOf(codigoUsuario));
		
		if(usuario != null) {
			return usuario;
		}
		
		throw new ErroFluxoException("Usuário não encontrado");
	}
	
	private AreaComumEntity buscarNomeAreaComum(Long codigoArea) {
		
		AreaComumEntity area = areaComumRepository.findByCodigo(String.valueOf(codigoArea));
		
		if(area != null) {
			return area;
		}
		
		throw new ErroFluxoException("Falha ao consultar nome da área comum.");
	}
	
	public AprovarReservaResponseDTO aprovarReserva(Long id) {
		
		if(id != null) {
			Optional<ReservaEntity> reserva = reservaRepository.findById(id);
			if(reserva.get() != null) {
				
				if(ReservaStatusEnum.APROVADO.getDescricao().equals(reserva.get().getStatus())
						|| ReservaStatusEnum.CANCELADO.getDescricao().equals(reserva.get().getStatus())) {
					AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
					response.setCodigo("400");
					response.setMensagem("Não é possivel aprovar uma reserva com status Aprovada ou Cancelada.");
					
					return response;
				}
				
				reserva.get().setStatus(ReservaStatusEnum.APROVADO.getDescricao());
				reservaRepository.save(reserva.get());
				
				AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
				response.setCodigo("200");
				response.setMensagem("Reserva do "+ reserva.get().getArea() +" do evento "+ reserva.get().getEvento() + " foi aprovada com sucesso.");
				
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
				
				if(ReservaStatusEnum.APROVADO.getDescricao().equals(reserva.get().getStatus())
						|| ReservaStatusEnum.CANCELADO.getDescricao().equals(reserva.get().getStatus())) {
					AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
					response.setCodigo("400");
					response.setMensagem("Não é possivel aprovar uma reserva com status Aprovada ou Cancelada.");
					
					return response;
				}
				
				reserva.get().setStatus(ReservaStatusEnum.CANCELADO.getDescricao());
				reservaRepository.save(reserva.get());
				
				AprovarReservaResponseDTO response = new AprovarReservaResponseDTO();
				response.setCodigo("200");
				response.setMensagem("Reserva do "+ reserva.get().getArea() +" do evento "+ reserva.get().getEvento() + " foi cancelada com sucesso.");
				
				return response;
			}
			
			throw new ErroFluxoException("Falha ao cancelar reserva. Reserva não encontrada.");
		}
		
		throw new ErroFluxoException("Falha ao cancelar reserva. Tente novamente.");
	}
}
