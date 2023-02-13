package br.com.api.condomanager.condomanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="veiculos")
public class VeiculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_condominio")
	private Long idCondominio;
	
	@Column(name = "condominio")
	private String condominio;
	
	@Column(name = "id_predio")
	private Long idPredio;
	
	@Column(name = "predio")
	private String predio;
	
	@Column(name = "id_morador")
	private Long idMorador;
	
	@Column(name = "morador")
	private String morador;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "placa")
	private String placa;
	
}
