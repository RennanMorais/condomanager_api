package br.com.api.condomanager.condomanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="predios")
@Getter
@Setter
public class PredioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private CondominioEntity condominio;
	
	@OneToMany
	@JoinColumn(name = "id_predio")
	private List<PavimentoEntity> pavimentos;
	
}
