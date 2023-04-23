package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.AreaComumEntity;

@Repository
public interface AreaComumRepository extends JpaRepository<AreaComumEntity, Long> {
	
	boolean existsByCodigo(String codigo);
	
}
