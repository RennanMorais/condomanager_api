package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condomanager.condomanager.model.PetEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.PetProjection;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
	
	List<PetProjection> findAllProjectedBy();
	
	PetProjection findProjectionById(Long id);
	
}
