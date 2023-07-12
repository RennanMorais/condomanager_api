package br.com.api.condomanager.condomanager.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Setter;

@Entity
@Table(name="assembleias")
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
    
    @Column(name = "hora")
    private LocalDateTime hora;

    @ManyToOne
    @JoinColumn(name = "id_condominio")
    private CondominioEntity condominio;

    @ManyToOne
    @JoinColumn(name = "id_areacomum")
    private AreaComumEntity areaComum;
}
