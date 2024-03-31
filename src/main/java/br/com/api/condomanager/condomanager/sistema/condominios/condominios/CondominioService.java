package br.com.api.condomanager.condomanager.sistema.condominios.condominios;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.CidadeEntity;
import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.model.EnderecoEntity;
import br.com.api.condomanager.condomanager.model.EstadoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.CidadeRepository;
import br.com.api.condomanager.condomanager.repository.CondominioRepository;
import br.com.api.condomanager.condomanager.repository.EnderecoRepository;
import br.com.api.condomanager.condomanager.repository.EstadoRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.CondominiosRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.CondominiosResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.CondominioProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CondominioService {
	
	@Autowired
	CondominioRepository condominioRepository;
	
	@Autowired
	PredioRepository predioRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public CondominiosResponseDTO cadastrarCondominio(CondominiosRequestDTO request) {
			
		this.checkCondominio(request.getCnpj());
		
		CondominioEntity cond = new CondominioEntity();
		cond.setNome(request.getNome());
		cond.setCnpj(request.getCnpj());
		cond.setEmail(request.getEmail());

		EnderecoEntity endereco = new EnderecoEntity();
		endereco.setEndereco(request.getEndereco().getEndereco());
		endereco.setComplemento(request.getEndereco().getComplemento());
		endereco.setNumero(request.getEndereco().getNumero());
		endereco.setBairro(request.getEndereco().getBairro());

		EstadoEntity estado = this.buscarEstado(request.getEndereco().getIdEstado());
		CidadeEntity cidade = this.buscarCidade(request.getEndereco().getIdCidade());

		endereco.setEstado(estado);
		endereco.setCidade(cidade);

		cond.setEndereco(endereco);
		
		List<CondominioEntity> listCond = this.condominioRepository.findAll();
		
		if(listCond.isEmpty()) {
			cond.setPrincipal(true);
		}

		enderecoRepository.save(endereco);
		condominioRepository.save(cond);
		
		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ request.getNome() +"' foi salvo com sucesso!");
		
		return response;
		
	}
	
	public List<CondominioProjection> buscarCondominios() {

		List<CondominioProjection> listCondominios = condominioRepository.findAllProjectedBy();
		
		if(!listCondominios.isEmpty()) {
			return listCondominios;
		}
		
		throw new ErroFluxoException("Nenhum condomínio cadastrado!");
	}
	
	private boolean checkCondominio(String cnpj) {
		
		CondominioEntity cond = condominioRepository.findByCnpj(cnpj);
		if(cond == null) {
			return true;
		}
		
		throw new ErroFluxoException("Condomínio já cadastrado!");
		
	}

	public CondominioProjection getCondominio(Long id) {
		CondominioProjection condominio = condominioRepository.findProjectionById(id);

		if(condominio == null) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}

		return condominio;
	}

	public CondominiosResponseDTO editarCondominio(Long id, CondominiosRequestDTO request) {
		Optional<CondominioEntity> cond = condominioRepository.findById(id);

		if(!cond.isPresent()) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}

		cond.get().setNome(request.getNome());
		cond.get().setCnpj(request.getCnpj());
		cond.get().setEmail(request.getEmail());
		cond.get().getEndereco().setEndereco(request.getEndereco().getEndereco());
		cond.get().getEndereco().setComplemento(request.getEndereco().getComplemento());
		cond.get().getEndereco().setNumero(request.getEndereco().getNumero());
		cond.get().getEndereco().setBairro(request.getEndereco().getBairro());

		EstadoEntity estado = this.buscarEstado(request.getEndereco().getIdEstado());
		CidadeEntity cidade = this.buscarCidade(request.getEndereco().getIdCidade());

		cond.get().getEndereco().setEstado(estado);
		cond.get().getEndereco().setCidade(cidade);

		condominioRepository.save(cond.get());

		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ cond.get().getNome() +"' foi editado com sucesso!");

		return response;
	}

	public CondominiosResponseDTO deletarCondominio(Long id) {

		Optional<CondominioEntity> cond = condominioRepository.findById(id);

		if(!cond.isPresent()) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}
		
		try {
			
			List<PredioEntity> predios = this.predioRepository.findAllByCondominio(cond.get());
			
			if(!predios.isEmpty()) {
				for(PredioEntity p : predios) {
					this.predioRepository.delete(p);
				}
			}
			
			this.condominioRepository.delete(cond.get());
		} catch(IllegalArgumentException | PersistenceException | DataIntegrityViolationException e) {
			log.error("Falha ao deletar condominio do banco de dados. ERRO: " + e.getMessage());
			throw new ErroFluxoException("Falha ao deletar o condominio no banco de dados.");
		}

		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ cond.get().getNome() +"' foi excluido com sucesso!");

		return response;
	}
	
	public CondominioProjection buscarCondominioPrincipal() {
		CondominioProjection cond = condominioRepository.findProjectionByPrincipal(true);

		if(cond == null) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}
		
		return cond;
	}

	private EstadoEntity buscarEstado(Long idEstado) {
		Optional<EstadoEntity> estado = estadoRepository.findById(idEstado);

		if(!estado.isPresent()) {
			throw new ErroFluxoException("Código do estado inválido.");
		}

		return estado.get();
	}
	
	public CondominiosResponseDTO alterarCondominioPrincipal(Long id) {
		Optional<CondominioEntity> cond = condominioRepository.findById(id);

		if(!cond.isPresent()) {
			throw new ErroFluxoException("Condomínio não encontrado!");
		}
		
		CondominioEntity condprincipal = condominioRepository.findByPrincipal(true);

		if(condprincipal == null) {
			throw new ErroFluxoException("Condomínio principal não foi encontrado");
		}
		
		condprincipal.setPrincipal(false);
		cond.get().setPrincipal(true);
		condominioRepository.save(condprincipal);
		condominioRepository.save(cond.get());

		CondominiosResponseDTO response = new CondominiosResponseDTO();
		response.setCodigo(HttpStatus.OK.value());
		response.setMensagem("O condominio '"+ cond.get().getNome() +"' agora é o condomínio principal");

		return response;
	}

	private CidadeEntity buscarCidade(Long idCidade) {
		Optional<CidadeEntity> cidade = cidadeRepository.findById(idCidade);

		if(!cidade.isPresent()) {
			throw new ErroFluxoException("Código da cidade inválido.");
		}

		return cidade.get();
	}
	
}
