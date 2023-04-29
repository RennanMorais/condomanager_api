package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VieculosResponseDTO;
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
		
		Optional<CondominioEntity> condominio = condominioRepository.findById(request.getIdCondominio());
		Optional<UserEntity> usuario = usuarioRepository.findById(request.getIdMorador());
		
		if(!condominio.isPresent()) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		} else if(!usuario.isPresent()) {
			throw new ErroFluxoException("Usuário não encontrado!");
		}
		
		this.validarVeiculoPlaca(request.getPlaca());
		
		VeiculoEntity veiculo = new VeiculoEntity();
		veiculo.setIdCondominio(condominio.get().getId());
		veiculo.setIdMorador(usuario.get().getId());
		veiculo.setIdPredio(usuario.get().getIdPredio());
		veiculo.setMarca(request.getMarca());
		veiculo.setModelo(request.getModelo());
		veiculo.setPlaca(request.getPlaca());
		
		if(request.getTipoVeiculo().equals(TipoVeiculoEnum.CARRO.getTipo())) {
			veiculo.setIdTipo(TipoVeiculoEnum.CARRO.getTipo());
		} else if(request.getTipoVeiculo().equals(TipoVeiculoEnum.MOTO.getTipo())) {
			veiculo.setIdTipo(TipoVeiculoEnum.MOTO.getTipo());
		} else if(request.getTipoVeiculo().equals(TipoVeiculoEnum.VAN.getTipo())) {
			veiculo.setIdTipo(TipoVeiculoEnum.VAN.getTipo());
		} else {
			veiculo.setIdTipo(TipoVeiculoEnum.OUTROS.getTipo());
		}
		
		VeiculoResponseDTO response = new VeiculoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Veículo "+ veiculo.getModelo() +" cadastrado com sucesso!");
		
		return response;
	}
	
	public List<VieculosResponseDTO> listarVeiculos() {
		List<VieculosResponseDTO> response = new ArrayList<>();
		List<VeiculoEntity> veiculos = veiculoRepository.findAll();
		
		if(veiculos == null || veiculos.isEmpty()) {
			throw new ErroFluxoException("Nenhum veículo cadastrado!");
		}
		
		for(VeiculoEntity v : veiculos) {
			VieculosResponseDTO veiculoItem = new VieculosResponseDTO();
			veiculoItem.setMarca(v.getMarca());
			veiculoItem.setModelo(v.getModelo());
			veiculoItem.setPlaca(v.getPlaca());
			veiculoItem.setTipoVeiculo(v.getIdTipo());
			response.add(veiculoItem);
		}
		
		return response;
	}
	
	private void validarVeiculoPlaca(String placa) {
		if(veiculoRepository.existsByPlaca(placa)) {
			throw new ErroFluxoException("Veículo já cadastrado!");
		}
	}
	
}
