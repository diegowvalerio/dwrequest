package br.com.dw.request.entidades;

import java.io.Serializable;

import javax.persistence.*;



@Entity
@Table(name="tbconfcadastro")
public class ConfCadastro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idconfcadastro;
	
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean integra_sintegraws;
	@Column(nullable=true,columnDefinition="varchar(250)")
	private String sintegraws_token;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String sintegraws_qtd_consultas_disponiveis;
	
	public ConfCadastro() {
		super();
	}

	public Integer getIdconfcadastro() {
		return idconfcadastro;
	}

	public void setIdconfcadastro(Integer idconfcadastro) {
		this.idconfcadastro = idconfcadastro;
	}

	public Boolean getIntegra_sintegraws() {
		return integra_sintegraws;
	}

	public void setIntegra_sintegraws(Boolean integra_sintegraws) {
		this.integra_sintegraws = integra_sintegraws;
	}

	public String getSintegraws_token() {
		return sintegraws_token;
	}

	public void setSintegraws_token(String sintegraws_token) {
		this.sintegraws_token = sintegraws_token;
	}

	public String getSintegraws_qtd_consultas_disponiveis() {
		return sintegraws_qtd_consultas_disponiveis;
	}

	public void setSintegraws_qtd_consultas_disponiveis(String sintegraws_qtd_consultas_disponiveis) {
		this.sintegraws_qtd_consultas_disponiveis = sintegraws_qtd_consultas_disponiveis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idconfcadastro == null) ? 0 : idconfcadastro.hashCode());
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
		ConfCadastro other = (ConfCadastro) obj;
		if (idconfcadastro == null) {
			if (other.idconfcadastro != null)
				return false;
		} else if (!idconfcadastro.equals(other.idconfcadastro))
			return false;
		return true;
	}
	
	
	
	

}