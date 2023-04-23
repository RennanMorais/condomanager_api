package br.com.api.condomanager.condomanager.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.condomanager.condomanager.model.AssembleiaEntity;

public interface AssembleiaRepository extends JpaRepository<AssembleiaEntity, Long> {

	@Query("SELECT a FROM AssembleiaEntity a WHERE a.data = :data")
	AssembleiaEntity findByDate(Date data);
	
	boolean existsByCodigo(String codigo);
	
}
