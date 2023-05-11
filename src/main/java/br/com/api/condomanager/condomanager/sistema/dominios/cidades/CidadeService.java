package br.com.api.condomanager.condomanager.sistema.dominios.cidades;

import br.com.api.condomanager.condomanager.repository.CidadeRepository;
import br.com.api.condomanager.condomanager.sistema.dto.projection.CidadeProjection;
import br.com.api.condomanager.condomanager.sistema.exceptions.ErroFluxoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

    public List<CidadeProjection> listarCidades() {

        List<CidadeProjection> cidades = cidadeRepository.findAllProjectedBy();

        if(cidades == null || cidades.isEmpty()) {
            throw new ErroFluxoException("Erro no serviço consultar cidades");
        }

        return cidades;
    }

}
