package br.com.api.condomanager.condomanager.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class Util {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AreaComumRepository areaComumRepository;
	
	@Autowired
	AssembleiaRepository assembleiaRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
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
	
	public String gerarCodigo(String tipo) {
		
		SecureRandom sr = new SecureRandom();
		
		String dataMilli = String.valueOf(System.currentTimeMillis());
		int tamanhoDtMili = dataMilli.length();
		int indexbegin = tamanhoDtMili - 9;
		
		String pt1 = String.valueOf(sr.nextInt(999));
		String pt2 = String.valueOf(sr.nextInt(999));
		String pt3 = String.valueOf(dataMilli.substring(indexbegin, tamanhoDtMili));
		
		String codigo = pt1.concat(pt2).concat(pt3);
		
		if(!this.validarCodigo(codigo, tipo)) {
			throw new ErroFluxoException("Erro no serviço de gerar código, verifique as informações e tente novamente.");
		}
		
		return codigo;
	}
	
	private boolean validarCodigo(String codigo, String tipo) {
		
		if("user".equals(tipo)) {
			while(usuarioRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("area".equals(tipo)) {
			while(areaComumRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("assembl".equals(tipo)) {
			while(assembleiaRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("cond".equals(tipo)) {
			while(condominioRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("ocorr".equals(tipo)) {
			while(ocorrenciaRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("pet".equals(tipo)) {
			while(petRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("pred".equals(tipo)) {
			while(predioRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("reserv".equals(tipo)) {
			while(reservaRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		if("veic".equals(tipo)) {
			while(veiculoRepository.existsByCodigo(codigo)) {
				this.gerarCodigo(tipo);
			}
			return true;
		}
		
		return false;
	}
	
}
