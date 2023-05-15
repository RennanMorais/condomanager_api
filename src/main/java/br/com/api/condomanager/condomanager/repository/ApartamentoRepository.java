package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.model.ApartamentoEntity;
import br.com.api.condomanager.condomanager.model.PredioEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartamentoRepository extends JpaRepository<ApartamentoEntity, Long> {

    List<ApartamentoProjection> findAllProjectedByPredio(PredioEntity predio);

}
