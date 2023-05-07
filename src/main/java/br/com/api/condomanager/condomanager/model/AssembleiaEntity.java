package br.com.api.condomanager.condomanager.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import br.com.api.condomanager.condomanager.util.DateUtil;
import br.com.api.condomanager.condomanager.util.validators.DateFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
