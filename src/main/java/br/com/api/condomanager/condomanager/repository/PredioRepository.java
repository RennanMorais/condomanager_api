package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.PredioEntity;

@Repository
public interface PredioRepository extends JpaRepository<PredioEntity, Long> {

}
