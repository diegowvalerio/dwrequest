package br.com.dw.request.entidades.seven;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import com.google.gson.annotations.Expose;


public class FormaPag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Integer idformapag;
	@Expose
	private String nome;
	@Expose
	private String desc_integracao;
	

	public FormaPag() {
		super();
	}


	public Integer getIdformapag() {
		return idformapag;
	}


	public void setIdformapag(Integer idformapag) {
		this.idformapag = idformapag;
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
	
	
}
