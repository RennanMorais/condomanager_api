package br.com.api.condomanager.condomanager.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.AcessoEnum;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class Util {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void validarAdmin(String emailoggedUser) {
		UserEntity user = usuarioRepository.findByEmail(emailoggedUser);

		if(user != null) {
			if(!user.getNivelAcesso().equals(AcessoEnum.ADMINISTRADOR.getNivel())) {
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
	
	public static String gerarCodigo() {
		SecureRandom sr = new SecureRandom();
		String p1 = String.valueOf(sr.nextInt(9));
		String p2 = String.valueOf(sr.nextInt(9));
		String p3 = String.valueOf(sr.nextInt(9));
		String p4 = String.valueOf(sr.nextInt(9));
		String p5 = String.valueOf(sr.nextInt(9));
		String p6 = String.valueOf(sr.nextInt(9));
		return p1.concat(p2).concat(p3).concat(p4).concat(p5).concat(p6);
	}
	
}
