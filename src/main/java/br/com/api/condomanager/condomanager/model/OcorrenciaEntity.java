package br.com.api.condomanager.condomanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ocorrencias")
@Getter
@Setter
public class OcorrenciaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo")
    private String codigo;
	
	@Column(name = "data")
    private Date data;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "idCondominio")
    private Long idCondominio;
	
	@Column(name = "idMorador")
	private Long idMorador;
	
	@Column(name = "id_status_ocorrencia")
    private Long idStatus;
	
	@Column(name = "resposta")
    private String resposta;
	
}
