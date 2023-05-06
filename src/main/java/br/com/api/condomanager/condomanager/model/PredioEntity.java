package br.com.api.condomanager.condomanager.model;

import javax.persistence.*;

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
	
}
