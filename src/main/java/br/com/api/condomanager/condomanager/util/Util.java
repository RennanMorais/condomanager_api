package br.com.api.condomanager.condomanager.util;

import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

public class Util {
	
@SuppressWarnings("unused")
private boolean validarCpf(String cpf) {
		
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
	
}
