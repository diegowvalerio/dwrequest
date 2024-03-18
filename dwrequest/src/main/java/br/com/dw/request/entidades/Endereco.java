package br.com.dw.request.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbendereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String logadouro;
	@Column(nullable=false,columnDefinition="varchar(10)")
	private String numero;
	@Column(nullable=false,columnDefinition="varchar(10)")
	private String cep;
	@Column(nullable=false,columnDefinition="varchar(40)")
	private String bairro;
	@Column(nullable=true,columnDefinition="varchar(150)")
	private String complemento;
	@ManyToOne
	private Cidade cidade;	
	@ManyToOne
	private CadastroGeral cadastrogeral;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogadouro() {
		return logadouro;
	}
	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
		Endereco other = (Endereco) obj;
		if (cadastrogeral == null) {
			if (other.cadastrogeral != null)
				return false;
		} else if (!cadastrogeral.equals(other.cadastrogeral))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
   
}
