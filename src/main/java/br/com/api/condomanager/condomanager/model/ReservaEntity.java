package br.com.api.condomanager.condomanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name = "id_condominio")
	private Long idCondominio;
	
	@Column(name = "id_morador")
	private Long idMorador;
	
	@Column(name = "id_areacomum")
	private Long idAreaComum;
	
	@Column(name = "id_status_reserva")
	private Long idStatus;
	
	@Column(name = "resposta")
	private String resposta;
	
}
