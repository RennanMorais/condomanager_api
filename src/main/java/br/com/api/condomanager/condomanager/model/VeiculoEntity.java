package br.com.api.condomanager.condomanager.model;

import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "id_morador")
	private UserEntity morador;
	
}
