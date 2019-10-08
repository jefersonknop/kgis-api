package br.com.knopsistemas.entities;

import java.sql.Timestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Evento {

	@Id
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	
	private Long inquilino_id;
	
	
	@JoinColumn(name = "equipamento_id", referencedColumnName = "id")
    @ManyToOne
	private Equipamento equipamento_id;
	
	@Size (max = 100)
    private String tipo;
	
	//@NotEmpty
	@Size (max = 255)
	private String descricao;
	
	
	private Timestamp data_inicio;
	
	private Timestamp  data_fim;
	
	@Size (max = 100)
    private String situacao;
	
	@Size (max = 500)
    private String informacoes;

	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInquilino_id() {
		return inquilino_id;
	}

	public void setInquilino_id(Long inquilino_id) {
		this.inquilino_id = inquilino_id;
	}

	public Equipamento getEquipamento_id() {
		return equipamento_id;
	}

	public void setEquipamento_id(Equipamento equipamento_id) {
		this.equipamento_id = equipamento_id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Timestamp data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Timestamp getData_fim() {
		return data_fim;
	}

	public void setData_fim(Timestamp data_fim) {
		this.data_fim = data_fim;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}



