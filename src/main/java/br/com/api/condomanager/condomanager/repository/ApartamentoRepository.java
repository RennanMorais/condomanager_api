package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentoRepository extends JpaRepository<ApartamentoEntity, Long> {



}
