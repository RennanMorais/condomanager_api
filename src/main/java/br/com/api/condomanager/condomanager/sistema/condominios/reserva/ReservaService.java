package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

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
		
		if(request != null) {
			
			CondominioEntity condominio;
			String nomeUsuario;
			String nomeAreaComum;
			
			try {
				condominio = this.buscarDadosCondominio(request.getIdCondominio());
				nomeUsuario = this.buscarNomeUsuario(request.getIdMorador());
				nomeAreaComum = this.buscarNomeAreaComum(request.getIdArea());
			} catch (Exception e) {
				throw new CondomanagerException("Falha ao consultar dados.");
			}
			
			ReservaEntity reserva = new ReservaEntity();
			reserva.setIdCondominio(request.getIdCondominio());
			reserva.setCondominio(condominio.getNome());
			reserva.setIdMorador(request.getIdMorador());
			reserva.setMorador(nomeUsuario);
			reserva.setIdArea(request.getIdArea());
			reserva.setArea(nomeAreaComum);
			reserva.setEvento(request.getEvento());
			
			Date dataFormatada = toDate(request.getData());
			this.reservaDataCheck(request.getIdCondominio(), request.getIdArea(), dataFormatada);
			
			reserva.setData(dataFormatada);
			reserva.setStatus(ReservaStatusEnum.PENDENTE.getDescricao());
			
			reservaRepository.save(reserva);
			
			ReservaResponseDTO response = new ReservaResponseDTO();
			response.setEvento(request.getEvento());
			response.setData(request.getData());
			
			return response;
			
		}
		
		throw new CondomanagerException("Não foi possivel finalizar a reserva, verifique os dados e tente novamente.");
	}
	
	public List<ReservasDadosResponseDTO> listarReservas() {
		
		List<ReservasDadosResponseDTO> listaResponse = new ArrayList<>();
		List<ReservaEntity> reservas;
		try {
			reservas = this.reservaRepository.findAll();
		} catch(Exception e) {
			throw new CondomanagerException("Nennuma reserva registrada.");
		}
		
		if(reservas != null) {
			for(ReservaEntity r : reservas) {
				ReservasDadosResponseDTO response = new ReservasDadosResponseDTO();
				response.setCondominio(r.getCondominio());
				response.setMorador(r.getMorador());
				response.setArea(r.getArea());
				response.setEvento(r.getEvento());
				response.setData(dateToString(r.getData()));
				response.setStatus(r.getStatus());
				listaResponse.add(response);
			}
		}
		
		return listaResponse;
	}
	
	private boolean reservaDataCheck(Long idCondominio, Long idArea, Date data) {
		
		ReservaEntity reserva = this.reservaRepository.findByDate(idCondominio, idArea, data);
		
		if(reserva == null) {
			return true;
		}
		
		throw new CondomanagerException("Já existe uma reserva nesta data!");
		
	}
	
	private CondominioEntity buscarDadosCondominio(Long idCondominio) {
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(idCondominio);
		
		if(condominio != null) {
			return condominio.get();
		}
		
		throw new CondomanagerException("Falha ao consultar condomínio.");
	}
	
	private String buscarNomeUsuario(Long idUsuario) {
		
		Optional<UserEntity> usuario = usuarioRepository.findById(idUsuario);
		
		if(usuario != null) {
			return usuario.get().getName();
		}
		
		throw new CondomanagerException("Falha ao consultar nome do usuario.");
	}
	
	private String buscarNomeAreaComum(Long idArea) {
		
		Optional<AreaComumEntity> area = areaComumRepository.findById(idArea);
		
		if(area != null) {
			return area.get().getArea();
		}
		
		throw new CondomanagerException("Falha ao consultar nome da área comum.");
	}
	
	private Date toDate(String data) {
		Calendar c = null;
		
		if(data != null) {
			int dia = Integer.valueOf(data.substring(0, 2));
			int mes = Integer.valueOf(data.substring(3, 5));
			int ano = Integer.valueOf(data.substring(6, 10));
			c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, dia);
			c.set(Calendar.MONTH, mes - 1);
			c.set(Calendar.YEAR, ano);
		}
		
		return c.getTime();
	}
	
	private String dateToString(Date data) {
		String dataFormatada = "";
		
		if(data != null) {
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = simpleDate.format(data);;
		}
		return dataFormatada;
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
			
			throw new CondomanagerException("Falha ao aprovar reserva. Reserva não encontrada.");
		}
		
		throw new CondomanagerException("Falha ao aprovar reserva. Tente novamente.");
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
			
			throw new CondomanagerException("Falha ao cancelar reserva. Reserva não encontrada.");
		}
		
		throw new CondomanagerException("Falha ao cancelar reserva. Tente novamente.");
	}
}
