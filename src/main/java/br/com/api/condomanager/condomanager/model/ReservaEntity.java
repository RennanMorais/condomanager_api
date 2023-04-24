package br.com.api.condomanager.condomanager.model;

import java.time.LocalDateTime;
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
	
	@Column(name = "codigo")
    private String codigo;
	
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
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name = "inicio")
	private LocalDateTime inicio;
	
	@Column(name = "termino")
	private LocalDateTime termino;
	
	@Column(name = "status")
	private String status;
	
}
