package br.com.api.condomanager.condomanager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.condomanager.condomanager.model.AssembleiaEntity;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AssembleiaProjection;

public interface AssembleiaRepository extends JpaRepository<AssembleiaEntity, Long> {

	@Query("SELECT a FROM AssembleiaEntity a WHERE a.data = :data")
	AssembleiaEntity findByDate(Date data);
	
	List<AssembleiaProjection> findAllProjectedBy();

	AssembleiaProjection findProjectedById(Long id);
	
}
