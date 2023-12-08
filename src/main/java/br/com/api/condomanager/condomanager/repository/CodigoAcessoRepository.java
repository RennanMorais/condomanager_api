package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.CodigoAcessoEntity;

@Repository
public interface CodigoAcessoRepository extends JpaRepository<CodigoAcessoEntity, Long> {
	
	//@Query("SELECT e FROM CodigoAcessoEntity e WHERE e.email = :email ORDER BY e.criado DESC")
	CodigoAcessoEntity findFirstByEmailOrderByCriadoDesc(String email);
	
	CodigoAcessoEntity findByCodigo(String codigo);
	
}
