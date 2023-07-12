package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.EstadoEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.EstadoProjection;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

    List<EstadoProjection> findAllProjectedBy();
}
