package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.model.CidadeEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.CidadeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    List<CidadeProjection> findAllProjectedBy();

}
