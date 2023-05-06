package br.com.api.condomanager.condomanager.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="condominios")
@Getter
@Setter
public class CondominioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cnpj")
	private String cnpj;
	
	@Column(name="email")
	private String email;

	@OneToOne
	@JoinColumn(name = "id_endereco")
	private EnderecoEntity endereco;
	
}
