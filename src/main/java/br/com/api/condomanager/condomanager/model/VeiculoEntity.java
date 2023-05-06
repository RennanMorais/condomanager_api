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
	
	@Column(name = "id_tipo_veiculo")
	private Long idTipo;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "id_condominio")
	private Long idCondominio;
	
	@Column(name = "id_predio")
	private Long idPredio;
	
	@Column(name = "id_morador")
	private Long idMorador;
	
}
