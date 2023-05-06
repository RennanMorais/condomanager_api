package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condomanager.condomanager.model.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
	
}
