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
    private Date data;

    @Column(name = "idCondominio")
    private String idCondominio;

    @Column(name = "local_condominio")
    private String condominio;

    @Column(name = "idAreaComum")
    private String idArea;

    @Column(name = "local")
    private String localAreaComum;
}
