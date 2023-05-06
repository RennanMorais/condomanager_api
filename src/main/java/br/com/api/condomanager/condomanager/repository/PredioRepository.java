package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.PredioProjection;

@Repository
public interface PredioRepository extends JpaRepository<PredioEntity, Long> {
	
	List<PredioProjection> findAllProjectedBy();
	
}
