package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.TipoVeiculoEnum;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.model.VeiculoEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.repository.VeiculoRepository;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	PredioRepository predioRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	Util utils;
	
	public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO request) {
		
		CondominioEntity condominio = condominioRepository.findByCodigo(String.valueOf(request.getCodigoCondominio()));
		UserEntity usuario = usuarioRepository.findByCodigo(String.valueOf(request.getCodigoMorador()));
		
		if(condominio == null) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		} else if(usuario == null) {
			throw new ErroFluxoException("Usuário não encontrado!");
		}
		
		this.validarVeiculoPlaca(request.getPlaca());
		
		VeiculoEntity veiculo = new VeiculoEntity();
		veiculo.setCodigo(utils.gerarCodigo("veic"));
		veiculo.setIdCondominio(condominio.getId());
		veiculo.setCondominio(condominio.getNome());
		veiculo.setIdMorador(usuario.getId());
		veiculo.setMorador(usuario.getName());
		veiculo.setIdPredio(usuario.getIdPredio());
		veiculo.setPredio(usuario.getPredio());
		veiculo.setMarca(request.getMarca());
		veiculo.setModelo(request.getModelo());
		veiculo.setPlaca(request.getPlaca());
		
		if(request.getTipoVeiculo().equals(TipoVeiculoEnum.CARRO.getTipo())) {
			veiculo.setTipo(TipoVeiculoEnum.CARRO.getDescricao());
		} else if(request.getTipoVeiculo().equals(TipoVeiculoEnum.MOTO.getTipo())) {
			veiculo.setTipo(TipoVeiculoEnum.MOTO.getDescricao());
		} else if(request.getTipoVeiculo().equals(TipoVeiculoEnum.VAN.getTipo())) {
			veiculo.setTipo(TipoVeiculoEnum.VAN.getDescricao());
		} else {
			veiculo.setTipo(TipoVeiculoEnum.OUTROS.getDescricao());
		}
		
		VeiculoResponseDTO response = new VeiculoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Veículo "+ veiculo.getModelo() +" cadastrado com sucesso!");
		
		return response;
	}
	
	private void validarVeiculoPlaca(String placa) {
		if(veiculoRepository.existsByPlaca(placa)) {
			throw new ErroFluxoException("Veículo já cadastrado!");
		}
	}
	
}
