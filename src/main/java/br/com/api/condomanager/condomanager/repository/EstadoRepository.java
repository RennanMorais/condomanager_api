package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.model.EstadoEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.EstadoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

    List<EstadoProjection> findAllProjectedBy();
}
