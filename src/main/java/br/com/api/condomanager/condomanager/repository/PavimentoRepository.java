package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.condomanager.condomanager.model.PavimentoEntity;

public interface PavimentoRepository extends JpaRepository<PavimentoEntity, Long> {
	
	@Query("SELECT p FROM PavimentoEntity p WHERE p.predio = :idPredio")
	List<PavimentoEntity> listAllPredios(Long idPredio);
	
}
