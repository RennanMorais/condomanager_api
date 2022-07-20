package br.com.api.condomanager.condomanager.model;

import java.math.BigInteger;

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
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "idCondominio")
	private Long idCondominio;
	
	@Column(name = "condominio")
	private String condominio;
	
	@Column(name = "id_predio")
	private Long idPredio;
	
	@Column(name = "predio")
	private String predio;
	
	@Column(name = "apto")
	private String apto;
	
	@Column(name = "id_access")
	private BigInteger idAccess;
	
	@Column(name = "nome_access")
	private String nomeAccess;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "avatar")
	private String avatar;
}
