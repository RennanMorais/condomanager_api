package br.com.api.condomanager.condomanager.sistema.condominios.reserva;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.AutenticacaoService;
import br.com.api.condomanager.condomanager.model.ReservaEntity;
import br.com.api.condomanager.condomanager.repository.ReservaRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.request.ReservaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.response.ReservaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

@Service
public class ReservaService {

	@Autowired
	AutenticacaoService autenticationService;
	
	@Autowired
	ReservaRepository reservaRepository;
	
	public ReservaResponseDTO reservar(ReservaRequestDTO request, String authorization) {
		
		this.autenticationService.validaUserToken(authorization);
		
		if(request != null) {
			
			this.reservaDataCheck(request.getIdCondominio(), request.getIdArea(), request.getData());
			
			ReservaEntity reserva = new ReservaEntity();
			reserva.setIdCondominio(request.getIdCondominio());
			reserva.setIdMorador(request.getIdMorador());
			reserva.setIdArea(request.getIdArea());
			reserva.setEvento(request.getEvento());
			reserva.setData(request.getData());
			reserva.setInicio(request.getInicio());
			reserva.setTermino(request.getTermino());
			
			reservaRepository.save(reserva);
			
			ReservaResponseDTO response = new ReservaResponseDTO();
			response.setEvento(request.getEvento());
			response.setData(request.getData());
			
			return response;
			
		}
		
		throw new CondomanagerException("Não foi possivel finalizar a reserva, verifique os dados e tente novamente.");
	}
	
	private boolean reservaDataCheck(Long idCondominio, Long idArea, Date data) {
		
		List<ReservaEntity> reserva = this.reservaRepository.findByDate(idCondominio, idArea, data);
		
		if(reserva.isEmpty()) {
			return true;
		}
		
		throw new CondomanagerException("Já existe uma reserva nesta data!");
		
	}
	
}
