package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.CidadeEntity;
import br.com.api.condomanager.condomanager.sistema.dto.projection.CidadeProjection;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    List<CidadeProjection> findAllProjectedBy();
    
    List<CidadeProjection> findAllProjectedByEstado(Long uf);

}
