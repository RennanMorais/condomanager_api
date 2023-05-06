package br.com.api.condomanager.condomanager.util;

import java.security.SecureRandom;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.enums.AcessoEnum;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.repository.AreaComumRepository;
import br.com.api.condomanager.condomanager.repository.AssembleiaRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.OcorrenciaRepository;
import br.com.api.condomanager.condomanager.repository.PetRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.repository.ReservaRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.repository.VeiculoRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

import javax.swing.text.html.parser.Entity;

@Service
public class Util {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MyUserDetails userDetails;

	public void validarAdmin(String emailoggedUser) {
		UserEntity user = usuarioRepository.findByEmail(emailoggedUser);

		if(user != null) {
			if(!user.getIdNivelAcesso().equals(AcessoEnum.ADMINISTRADOR.getNivel())) {
				throw new AuthorizationServiceException("Usuário não autorizado para utilizar esse serviço!");
			}
		} else {
			throw new ErroFluxoException("Falha ao consultar dados do usuário.");
		}
	}

	public boolean validarCpf(String cpf) {
		
		int multiplicador1 = 10;
		int multiplicador2 = 11;
		int total1 = 0;
		int total2 = 0;
		
		
		for(int i = 0; i < cpf.length() - 2; i++) {
			int n = Integer.parseInt(cpf.substring(i, i+1));
			int nm = n * multiplicador1;
			multiplicador1--;
			total1 = total1 + nm;
		}
		
		long primeiroDigito = total1 * 10 % 11;
		
		if(primeiroDigito == 10) {
			primeiroDigito = 0;
		}
		
		if(primeiroDigito != Integer.parseInt(cpf.substring(9, 10))) {
			throw new DadosPessoaisException("CPF inválido!");
		}
		
		for(int i = 0; i < cpf.length() - 1; i++) {
			int n  = Integer.parseInt(cpf.substring(i, i+1));
			int nm = n * multiplicador2;
			multiplicador2--;
			total2 = total2 + nm;
		}
		
		long segundoDigito = total2 * 10 % 11;
		
		if(segundoDigito == 10) {
			segundoDigito = 0;
		}
		
		if(segundoDigito != Integer.parseInt(cpf.substring(10, 11))) {
			throw new DadosPessoaisException("CPF inválido!");
		}
		
		return true;
		
	}
	
	public String gerarCodigo() {
		
		SecureRandom sr = new SecureRandom();
		
		String dataMilli = String.valueOf(System.currentTimeMillis());
		int tamanhoDtMili = dataMilli.length();
		int indexbegin = tamanhoDtMili - 9;
		
		String pt1 = String.valueOf(sr.nextInt(999));
		String pt2 = String.valueOf(sr.nextInt(999));
		String pt3 = String.valueOf(dataMilli.substring(indexbegin, tamanhoDtMili));
		
		String codigo = pt1.concat(pt2).concat(pt3);
		
		return codigo;
	}
	
}
