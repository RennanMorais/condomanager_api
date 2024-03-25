package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import br.com.api.condomanager.condomanager.model.PavimentoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.ApartamentoRepository;
import br.com.api.condomanager.condomanager.repository.PavimentoRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;

@Service
public class ApartamentoService {

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private PredioRepository predioRepository;
    
    @Autowired
    private PavimentoRepository pavimentoRepository;

    public ApartamentoResponseDTO cadastrarApartamento(ApartamentoRequestDTO request) {
    	
    	PredioEntity predio = this.buscarPredio(request.getIdPredio());
    	List<PavimentoEntity> pavimentos = this.pavimentoRepository.listAllPredios(predio.getId());
    	Long idPavimento = 0L;
    	
    	for(PavimentoEntity p : pavimentos) {
    		if(p.getPavimento().equals(request.getPavimento())) {
    			idPavimento = p.getId();
    			break;
    		}
    	}
    	
    	if(idPavimento.equals(0L)) {
    		throw new ErroFluxoException("Pavimento inválido para esse prédio.");
    	}
    	
        ApartamentoEntity apto = new ApartamentoEntity();
        apto.setNumero(request.getNumero());
        apto.setPredio(request.getIdPredio());
        apto.setDispAluguel(request.getDispAluguel());
        apto.setIdPavimento(idPavimento);

        apartamentoRepository.save(apto);

        ApartamentoResponseDTO response = new ApartamentoResponseDTO();
        response.setCodigo("200");
        response.setMensagem("Apartamento cadastrado com sucesso!");

        return response;
    }

    public List<ApartamentoProjection> listarApartamentos(Long idPredio) {

        List<ApartamentoProjection> aptos = apartamentoRepository.findAllProjectedByPredio(this.buscarPredio(idPredio));

        if(aptos.isEmpty()) {
            throw new ErroFluxoException("Nenhum apartamento encontrado neste prédio");
        }

        return aptos;

    }
    
    public ApartamentoResponseDTO editarApartamento(Long id, ApartamentoRequestDTO request) {
    	
    	Optional<ApartamentoEntity> apto = apartamentoRepository.findById(id);
    	
    	if(!apto.isPresent()) {
    		throw new ErroFluxoException("Apartamento não encontrado!");
    	}
    	
    	apto.get().setNumero(request.getNumero());
    	apto.get().setDispAluguel(request.getDispAluguel());
    	apartamentoRepository.save(apto.get());
    	
    	ApartamentoResponseDTO response = new ApartamentoResponseDTO();
        response.setCodigo("200");
        response.setMensagem("Apartamento editado com sucesso!");

        return response;
    }

    public ApartamentoProjection buscarAptoPorId(Long id) {
        ApartamentoProjection apto = apartamentoRepository.findProjectedById(id);

        if(apto == null) {
            throw new ErroFluxoException("Apartamento não encontrado, verifique e tente novamente!");
        }

        return apto;
    }

    public ApartamentoResponseDTO deletarApartamento(Long id) {
        Optional<ApartamentoEntity> apto = apartamentoRepository.findById(id);

        if(!apto.isPresent()) {
            throw new ErroFluxoException("Apartamento não encontrado, verifique e tente novamente!");
        }

        this.apartamentoRepository.delete(apto.get());

        ApartamentoResponseDTO response = new ApartamentoResponseDTO();
        response.setCodigo("200");
        response.setMensagem("Apartamento excluído com sucesso!");

        return response;
    }

    private PredioEntity buscarPredio(Long id) {
        Optional<PredioEntity> predio = predioRepository.findById(id);

        if(!predio.isPresent()) {
            throw new ErroFluxoException("Prédio não encontrado, verifique e tente novamente!");
        }

        return predio.get();
    }

}
