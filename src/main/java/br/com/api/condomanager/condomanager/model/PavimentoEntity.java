package br.com.api.condomanager.condomanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pavimentos")
@Getter
@Setter
public class PavimentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pavimento")
	private String pavimento;
	
	@Column(name = "id_predio")
    private Long predio;
	
	@OneToMany
	@JoinColumn(name = "id_pavimento")
	private List<ApartamentoEntity> apartamentos;
	
}
