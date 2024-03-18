package br.com.dw.request.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;


@Entity
@Table(name="tbcontato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcontato;
	//contato
	@Column(nullable=false,columnDefinition="varchar(150)")
	private String nomecontato;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String email;
	@Column(nullable=false,columnDefinition="varchar(15)")
	private String numerocontato;
	
	@Column(nullable=true,columnDefinition="varchar(150)")
	private String observacaocontato;
	
	@ManyToOne
	private CadastroGeral cadastrogeral;

	public Contato() {
		super();
	}

	public Integer getIdcontato() {
		return idcontato;
	}

	public void setIdcontato(Integer idcontato) {
		this.idcontato = idcontato;
	}

	public String getNomecontato() {
		return nomecontato;
	}

	public void setNomecontato(String nomecontato) {
		this.nomecontato = nomecontato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacaocontato() {
		return observacaocontato;
	}

	public void setObservacaocontato(String observacaocontato) {
		this.observacaocontato = observacaocontato;
	}

	public String getNumerocontato() {
		return numerocontato;
	}

	public void setNumerocontato(String numerocontato) {
		this.numerocontato = numerocontato;
	}

	public CadastroGeral getCadastrogeral() {
		return cadastrogeral;
	}

	public void setCadastrogeral(CadastroGeral cadastrogeral) {
		this.cadastrogeral = cadastrogeral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastrogeral == null) ? 0 : cadastrogeral.hashCode());
		result = prime * result + ((idcontato == null) ? 0 : idcontato.hashCode());
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
		Contato other = (Contato) obj;
		if (cadastrogeral == null) {
			if (other.cadastrogeral != null)
				return false;
		} else if (!cadastrogeral.equals(other.cadastrogeral))
			return false;
		if (idcontato == null) {
			if (other.idcontato != null)
				return false;
		} else if (!idcontato.equals(other.idcontato))
			return false;
		return true;
	} 
	
	

	
}
