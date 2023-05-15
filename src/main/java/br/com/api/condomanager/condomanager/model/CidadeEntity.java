package br.com.api.condomanager.condomanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cidade")
@Getter
@Setter
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "uf")
    private EstadoEntity estado;

    @Column(name="ibge")
    private Long ibge;
}
