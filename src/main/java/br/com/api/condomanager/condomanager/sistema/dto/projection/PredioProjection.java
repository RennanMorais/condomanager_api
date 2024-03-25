package br.com.api.condomanager.condomanager.sistema.dto.projection;

import java.util.List;

import br.com.api.condomanager.condomanager.model.PavimentoEntity;

public interface PredioProjection {

	Long getId();
	
	String getNome();
	
	CondominioProjection getCondominio();
	
	List<PavimentoEntity> getPavimentos();
	
}
