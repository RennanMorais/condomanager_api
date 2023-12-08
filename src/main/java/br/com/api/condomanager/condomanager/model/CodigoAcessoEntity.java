package br.com.api.condomanager.condomanager.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="codacesso")
public class CodigoAcessoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="codigo")
	private String codigo;
	
	@Column(name="criado")
	private LocalDateTime criado;
	
	@Column(name="expiracao")
	private LocalDateTime expiracao;
	
	@Column(name="verificado")
	private LocalDateTime verificado;
	
	@ManyToOne
	@JoinColumn(name = "id_morador")
	private UserEntity morador;
	
	@Column(name="email")
	private String email;
	
	@Column(name="ativo")
	private Boolean ativo;
	
}
