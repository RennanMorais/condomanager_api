package br.com.api.condomanager.condomanager.repository;

import br.com.api.condomanager.condomanager.sistema.dto.projection.VeiculoProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condomanager.condomanager.model.VeiculoEntity;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
	
	boolean existsByPlaca(String placa);

	List<VeiculoProjection> findAllProjectedBy();
	
	VeiculoProjection findProjectionById(Long id);
	
}
