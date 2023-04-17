package br.com.api.condomanager.condomanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="assembleias")
@Getter
@Setter
public class AssembleiaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "idCondominio")
    private Long idCondominio;

    @Column(name = "local_condominio")
    private String condominio;

    @Column(name = "idAreaComum")
    private Long idArea;

    @Column(name = "local")
    private String localAreaComum;
}
