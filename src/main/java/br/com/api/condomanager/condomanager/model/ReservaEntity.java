package br.com.api.condomanager.condomanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "id_condominio")
	private Long idCondominio;
	
	@Column(name = "condominio")
	private String condominio;
	
	@Column(name = "id_morador")
	private Long idMorador;
	
	@Column(name = "morador")
	private String morador;
	
	@Column(name = "id_area")
	private Long idArea;
	
	@Column(name = "area_comum")
	private String area;
	
	@Column(name = "evento")
	private String evento;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "inicio")
	private Date inicio;
	
	@Column(name = "termino")
	private Date termino;
	
	@Column(name = "status")
	private String status;
	
}
