package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condomanager.condomanager.model.VeiculoEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
	
	boolean existsByPlaca(String placa);
	
}
