package br.com.knopsistemas.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.geo.Point;


@Entity
public class Registro {

	@Id
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	
	private Long inquilino_id;
	
	
	@JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne
	private Evento evento_id;
	
	@Size (max = 100)
    private String tipo;
	
	//@NotEmpty
	@Size (max = 255)
	private String descricao;
	
	private Timestamp  datahora;
	
	
    @Size (max = 50)
    private String latitude;
    
    @Size (max = 50)
    private String longitude;
	
	
	
	
	@Size (max = 100)
    private String situacao;
	
	@Size (max = 500)
    private String informacoes;
	
	
	
	 private Point localizacao;

	
	
	
	
	

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

	public Evento getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(Evento evento_id) {
		this.evento_id = evento_id;
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

	public Timestamp getDatahora() {
		return datahora;
	}

	public void setDatahora(Timestamp datahora) {
		this.datahora = datahora;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public Point getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Point localizacao) {
		this.localizacao = localizacao;
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
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}



