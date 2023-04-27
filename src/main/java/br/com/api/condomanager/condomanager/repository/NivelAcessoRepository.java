package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.NivelAcessoEntity;

@Repository
public interface NivelAcessoRepository extends JpaRepository<NivelAcessoEntity, Long> {
	
}
