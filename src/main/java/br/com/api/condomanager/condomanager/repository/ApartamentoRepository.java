package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;

@Repository
public interface ApartamentoRepository extends JpaRepository<ApartamentoEntity, Long> {

    List<ApartamentoProjection> findAllProjectedByPredio(PredioEntity predio);

}
