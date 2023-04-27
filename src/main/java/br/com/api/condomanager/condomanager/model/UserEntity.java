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
@Table(name="users")
@Getter
@Setter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo")
	private String codigo;
	
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
	
	@Column(name = "idCondominio")
	private Long idCondominio;
	
	@Column(name = "id_predio")
	private Long idPredio;
	
	@Column(name = "id_apto")
	private String idApto;
	
	@Column(name = "id_nivel_acesso")
	private Long idNivelAcesso;
	
	@Column(name = "id_avatar")
	private String idAvatar;
}
