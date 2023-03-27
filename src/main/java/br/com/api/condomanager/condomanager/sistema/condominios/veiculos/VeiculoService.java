package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.VeiculoEntity;
import br.com.api.condomanager.condomanager.repository.VeiculoRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.CondomanagerException;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;
	
	public VeiculoResponseDTO cadastrar(VeiculoRequestDTO request) {
		
		this.verificaVeiculoCadastrado(request.getIdCondominio());
		
		VeiculoEntity veiculo = new VeiculoEntity();
		
		return null;
	}
	
	private void verificaVeiculoCadastrado(BigInteger idCondominio) {
		
		boolean response = false;
		
		try {
			response = this.veiculoRepository.checkVeiculoCadastrado(Long.valueOf(idCondominio.toString()));
		} catch (Exception e) {
			throw new CondomanagerException("Falha ao consultar veículo cadastrado");
		}
		
		if(response) {
			throw new CondomanagerException("Veículo já cadastrado neste condomínio");
		}
		
	}
	
}
