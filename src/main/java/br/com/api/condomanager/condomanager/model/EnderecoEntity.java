package br.com.api.condomanager.condomanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="enderecos")
@Getter
@Setter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="endereco")
    private String endereco;

    @Column(name="numero")
    private String numero;

    @Column(name="complemento")
    private String complemento;

    @Column(name="bairro")
    private String bairro;

    @OneToOne
    @JoinColumn(name = "id_estado")
    private EstadoEntity estado;

    @OneToOne
    @JoinColumn(name = "id_cidade")
    private CidadeEntity cidade;
}
