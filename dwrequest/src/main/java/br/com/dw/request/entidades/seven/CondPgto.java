package br.com.dw.request.entidades.seven;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import com.google.gson.annotations.Expose;


public class CondPgto implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Expose
	private Integer idcondpgto;
	@Expose
	private String nome;
	@Expose
	private String desc_integracao;
	@Expose
	private Integer tabelaprecoid;
	
	public CondPgto() {
		super();
	}

	public Integer getIdcondpgto() {
		return idcondpgto;
	}

	public void setIdcondpgto(Integer idcondpgto) {
		this.idcondpgto = idcondpgto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc_integracao() {
		return desc_integracao;
	}

	public void setDesc_integracao(String desc_integracao) {
		this.desc_integracao = desc_integracao;
	}

	public Integer getTabelaprecoid() {
		return tabelaprecoid;
	}

	public void setTabelaprecoid(Integer tabelaprecoid) {
		this.tabelaprecoid = tabelaprecoid;
	}   

	
   
}
