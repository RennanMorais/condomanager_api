package br.com.api.condomanager.condomanager.sistema.dominios.estados;

import br.com.api.condomanager.condomanager.repository.EstadoRepository;
import br.com.api.condomanager.condomanager.sistema.dto.projection.EstadoProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public List<EstadoProjection> listarEstados() {

        List<EstadoProjection> estados = estadoRepository.findAllProjectedBy();

        if(estados == null || estados.isEmpty()) {
            throw new ErroFluxoException("Erro no serviço consultar estados");
        }

        return estados;
    }

}
