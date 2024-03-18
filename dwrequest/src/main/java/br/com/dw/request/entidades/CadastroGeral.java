package br.com.dw.request.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.gson.annotations.Expose;


@Entity
@Table(name="tbcadastrogeral")
public class CadastroGeral implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idcadastrogeral;
	@Column(nullable=false,columnDefinition="varchar(250)")
	@Expose
	private String nome;
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	@Expose
	private Boolean situacao;
	@Column(nullable=true, columnDefinition="varchar(250)")
	private String observacao;
	@Column(nullable=false)
	private String tipojf;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean bocliente;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	@Expose
	private Boolean bovendedor;
	
	//fiscal
	@Column(nullable=true, columnDefinition="varchar(10)")
	private String insc_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_icms_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_cofins_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_pis_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_ipi_suframa;
	@Column(nullable=true, columnDefinition="char(1)")
	private String tipo_tributacao;
	
	//pessoa fisica
	@Column(nullable=true, columnDefinition="varchar(14)", unique=true)
	private String cpf;
	@Column(nullable=true, columnDefinition="varchar(14)")
	private String rg;
	@Temporal(TemporalType.DATE)
	private Date dtnascimento;
	
	//pessoa juridica
	@Column(nullable=true, columnDefinition="varchar(18)", unique=true)
	private String cnpj;
	@Column(nullable=true, columnDefinition="varchar(14)")
	private String insc_estadual;
	@Column(nullable=true, columnDefinition="varchar(250)")
	private String razao_social;
	
	@OneToMany(mappedBy="cadastrogeral", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<Contato> contatos = new ArrayList<>();
	
	@OneToMany(mappedBy="cadastrogeral", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<Endereco> enderecos = new ArrayList<>();
	
	@ManyToOne
	private CadastroGeral vendedor;

	
	public CadastroGeral() {
		super();
	}


	public CadastroGeral getVendedor() {
		return vendedor;
	}


	public void setVendedor(CadastroGeral vendedor) {
		this.vendedor = vendedor;
	}


	public Boolean getBocliente() {
		return bocliente;
	}


	public void setBocliente(Boolean bocliente) {
		this.bocliente = bocliente;
	}


	public Boolean getBovendedor() {
		return bovendedor;
	}


	public void setBovendedor(Boolean bovendedor) {
		this.bovendedor = bovendedor;
	}


	public Date getDtnascimento() {
		return dtnascimento;
	}


	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}


	public Integer getIdcadastrogeral() {
		return idcadastrogeral;
	}


	public void setIdcadastrogeral(Integer idcadastrogeral) {
		this.idcadastrogeral = idcadastrogeral;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDtcadastro() {
		return dtcadastro;
	}


	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}


	public Boolean getSituacao() {
		return situacao;
	}


	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getTipojf() {
		return tipojf;
	}


	public void setTipojf(String tipojf) {
		this.tipojf = tipojf;
	}


	public String getInsc_suframa() {
		return insc_suframa;
	}


	public void setInsc_suframa(String insc_suframa) {
		this.insc_suframa = insc_suframa;
	}


	public Boolean getDesc_icms_suframa() {
		return desc_icms_suframa;
	}


	public void setDesc_icms_suframa(Boolean desc_icms_suframa) {
		this.desc_icms_suframa = desc_icms_suframa;
	}


	public Boolean getDesc_cofins_suframa() {
		return desc_cofins_suframa;
	}


	public void setDesc_cofins_suframa(Boolean desc_cofins_suframa) {
		this.desc_cofins_suframa = desc_cofins_suframa;
	}


	public Boolean getDesc_pis_suframa() {
		return desc_pis_suframa;
	}


	public void setDesc_pis_suframa(Boolean desc_pis_suframa) {
		this.desc_pis_suframa = desc_pis_suframa;
	}


	public Boolean getDesc_ipi_suframa() {
		return desc_ipi_suframa;
	}


	public void setDesc_ipi_suframa(Boolean desc_ipi_suframa) {
		this.desc_ipi_suframa = desc_ipi_suframa;
	}


	public String getTipo_tributacao() {
		return tipo_tributacao;
	}


	public void setTipo_tributacao(String tipo_tributacao) {
		this.tipo_tributacao = tipo_tributacao;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getInsc_estadual() {
		return insc_estadual;
	}


	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}


	public String getRazao_social() {
		return razao_social;
	}


	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}


	public List<Contato> getContatos() {
		return contatos;
	}


	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcadastrogeral == null) ? 0 : idcadastrogeral.hashCode());
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
		CadastroGeral other = (CadastroGeral) obj;
		if (idcadastrogeral == null) {
			if (other.idcadastrogeral != null)
				return false;
		} else if (!idcadastrogeral.equals(other.idcadastrogeral))
			return false;
		return true;
	}  
	
	

}