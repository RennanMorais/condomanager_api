package br.com.api.condomanager.condomanager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
	
	@Query("SELECT r FROM ReservaEntity r WHERE id_condominio = idCondominio AND id_area = idArea AND r.data = data")
	List<ReservaEntity> findByDate(Long idCondominio, Long idArea, Date data);
	
}
