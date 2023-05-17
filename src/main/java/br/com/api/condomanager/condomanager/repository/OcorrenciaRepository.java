package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.sistema.dto.projection.OcorrenciaProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condomanager.condomanager.model.OcorrenciaEntity;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<OcorrenciaEntity, Long> {

    OcorrenciaProjection findProjectedById(Long id);

    List<OcorrenciaProjection> findAllProjectedBy();

}
