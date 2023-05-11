package br.com.api.condomanager.condomanager.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "ddd")
	private String ddd;

	@OneToOne
	@JoinColumn(name = "id_condominio")
	private CondominioEntity condominio;

	@OneToOne
	@JoinColumn(name = "id_predio")
	private PredioEntity predio;

	@OneToOne
	@JoinColumn(name = "id_apto")
	private ApartamentoEntity apartamento;
	
	@Column(name = "nivel_acesso")
	private Long nivelAcesso;
	
	@Column(name = "avatar")
	private String avatar;
}
