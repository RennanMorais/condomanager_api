package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AreaComumProjection;

@Repository
public interface AreaComumRepository extends JpaRepository<AreaComumEntity, Long> {
	
	AreaComumEntity findByCodigo(String codigo);
	
	List<AreaComumProjection> findAllProjectedBy();
	
	boolean existsByCodigo(String codigo);
	
}
