package br.com.api.condomanager.condomanager.model;

import java.math.BigInteger;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String rg;
	
	private String cpf;
	
	private String phone;
	
	private Long idCondominio;
	
	private String predio;
	
	private String apto;
	
	private BigInteger nivelAcesso;
	
	private String token;
}
