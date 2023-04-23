package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.condomanager.condomanager.model.VeiculoEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

	@Query("SELECT v FROM VeiculoEntity v WHERE v.idCondominio = :idCondominio")
	boolean checkVeiculoCadastrado(Long idCondominio);
	
	boolean existsByCodigo(String codigo);
	
}
