package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.TipoVeiculoEnum;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.model.VeiculoEntity;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.repository.VeiculoRepository;
import br.com.api.condomanager.condomanager.sistema.dto.VeiculoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.VeiculoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.VeiculoProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

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
	
	public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO request) {
		Optional<UserEntity> usuario = usuarioRepository.findById(request.getIdMorador());
		
		if(!usuario.isPresent()) {
			throw new ErroFluxoException("Usuário não encontrado!");
		}
		
		this.validarVeiculoPlaca(request.getPlaca());
		
		VeiculoEntity veiculo = new VeiculoEntity();
		veiculo.setMarca(request.getMarca());
		veiculo.setModelo(request.getModelo());
		veiculo.setPlaca(request.getPlaca());
		veiculo.setMorador(usuario.get());

		for(TipoVeiculoEnum tipoEnum : TipoVeiculoEnum.values()) {
			if(tipoEnum.getTipo().equals(request.getTipoVeiculo())) {
				veiculo.setIdTipo(request.getTipoVeiculo());
			}
		}
		
		if(veiculo.getIdTipo() == null) {
			veiculo.setIdTipo(TipoVeiculoEnum.OUTROS.getTipo());
		}

		veiculoRepository.save(veiculo);
		
		VeiculoResponseDTO response = new VeiculoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Veículo "+ veiculo.getModelo() +" cadastrado com sucesso!");
		
		return response;
	}
	
	public List<VeiculoProjection> listarVeiculos() {
		List<VeiculoProjection> veiculos = veiculoRepository.findAllProjectedBy();
		
		if(veiculos == null || veiculos.isEmpty()) {
			throw new ErroFluxoException("Nenhum veículo cadastrado!");
		}
		
		return veiculos;
	}
	
	public VeiculoProjection buscarVeiculo(Long id) {
		VeiculoProjection veiculo = veiculoRepository.findProjectionById(id);
		
		if(veiculo == null) {
			throw new ErroFluxoException("Nenhum veículo cadastrado!");
		}
		
		return veiculo;
	}
	
	public VeiculoResponseDTO editarVeiculo(Long id, VeiculoRequestDTO request) {
		
		Optional<UserEntity> usuario = usuarioRepository.findById(request.getIdMorador());
		
		if(!usuario.isPresent()) {
			throw new ErroFluxoException("Usuário não encontrado!");
		}
		
		Optional<VeiculoEntity> veiculo = veiculoRepository.findById(id);
		
		if(!veiculo.isPresent()) {
			throw new ErroFluxoException("Veículo não encontrado!");
		}
		
		veiculo.get().setMarca(request.getMarca());
		veiculo.get().setModelo(request.getModelo());
		veiculo.get().setPlaca(request.getPlaca());
		veiculo.get().setMorador(usuario.get());
		
		for(TipoVeiculoEnum tipoEnum : TipoVeiculoEnum.values()) {
			if(tipoEnum.getTipo().equals(request.getTipoVeiculo())) {
				veiculo.get().setIdTipo(request.getTipoVeiculo());
			}
		}
		
		if(veiculo.get().getIdTipo() == null) {
			veiculo.get().setIdTipo(TipoVeiculoEnum.OUTROS.getTipo());
		}
		
		veiculoRepository.save(veiculo.get());
		
		VeiculoResponseDTO response = new VeiculoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Veículo "+ veiculo.get().getModelo() +" editado com sucesso!");
		
		return response;
	}
	
	public VeiculoResponseDTO deletarVeiculo(Long id) {

		Optional<VeiculoEntity> veiculo = veiculoRepository.findById(id);
		
		if(!veiculo.isPresent()) {
			throw new ErroFluxoException("Veículo não encontrado!");
		}
		
		veiculoRepository.delete(veiculo.get());
		
		VeiculoResponseDTO response = new VeiculoResponseDTO();
		response.setCodigo(String.valueOf(HttpStatus.OK.value()));
		response.setMensagem("Veículo "+ veiculo.get().getModelo() +" excluído com sucesso!");
		
		return response;
	}
	
	private void validarVeiculoPlaca(String placa) {
		if(veiculoRepository.existsByPlaca(placa)) {
			throw new ErroFluxoException("Veículo já cadastrado!");
		}
	}
	
}
