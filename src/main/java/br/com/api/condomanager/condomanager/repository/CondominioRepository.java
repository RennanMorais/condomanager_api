package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.CondominioEntity;

@Repository
public interface CondominioRepository extends JpaRepository<CondominioEntity, Long> {
	
	CondominioEntity findByCnpj(String cnpj);
	
	boolean existsByCodigo(String codigo);
	
}
