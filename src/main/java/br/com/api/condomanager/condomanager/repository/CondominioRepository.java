package br.com.api.condomanager.condomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.CondominioEntity;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.CondominioProjection;

@Repository
public interface CondominioRepository extends JpaRepository<CondominioEntity, Long> {
	
	CondominioEntity findByCnpj(String cnpj);
	
	List<CondominioProjection> findAllProjectedBy();

	CondominioProjection findProjectedById(Long id);
	
}
