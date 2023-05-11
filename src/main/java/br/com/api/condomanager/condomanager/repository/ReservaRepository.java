package br.com.api.condomanager.condomanager.repository;

import java.util.Date;
import java.util.List;

import br.com.api.condomanager.condomanager.sistema.dto.projection.ReservaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
	
	@Query("SELECT r FROM ReservaEntity r WHERE r.condominio = :idCondominio AND r.areaComum = :idArea AND r.data = :data")
	ReservaEntity findByDate(Long idCondominio, Long idArea, Date data);

	List<ReservaProjection> findAllProjectedBy();
	
}
