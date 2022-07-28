package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
	
}
