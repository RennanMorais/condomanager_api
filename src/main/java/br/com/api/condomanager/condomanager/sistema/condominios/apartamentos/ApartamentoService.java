package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.repository.ApartamentoRepository;
import br.com.api.condomanager.condomanager.repository.PredioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartamentoService {

    @Autowired
    ApartamentoRepository apartamentoRepository;

    @Autowired
    PredioRepository predioRepository;

    public ApartamentoResponseDTO cadastrarApartamento(ApartamentoRequestDTO request) {
        ApartamentoEntity apto = new ApartamentoEntity();
        apto.setNumero(request.getNumero());
        apto.setPredio(this.buscarPredio(request.getIdPredio()));

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

    private PredioEntity buscarPredio(Long id) {
        Optional<PredioEntity> predio = predioRepository.findById(id);

        if(!predio.isPresent()) {
            throw new ErroFluxoException("Prédio não encontrado, verifique e tente novamente!");
        }

        return predio.get();
    }

}