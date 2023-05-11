package br.com.api.condomanager.condomanager.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class ReservaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "evento")
	private String evento;
	
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private CondominioEntity condominio;

	@ManyToOne
	@JoinColumn(name = "id_morador")
	private UserEntity morador;

	@ManyToOne
	@JoinColumn(name = "id_areacomum")
	private AreaComumEntity areaComum;

	@Column(name = "status")
	private Long status;
	
	@Column(name = "resposta")
	private String resposta;
	
}
