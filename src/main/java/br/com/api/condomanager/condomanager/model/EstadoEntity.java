package br.com.api.condomanager.condomanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="estado")
@Getter
@Setter
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name="uf")
    private String uf;

    @Column(name="ibge")
    private Long ibge;

    @Column(name="pais")
    private Long pais;

    @Column(name="ddd")
    private String ddd;
}
