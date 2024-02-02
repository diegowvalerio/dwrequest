package br.com.dw.request.integracao;

import java.io.Serializable;


public class SintegraWS_CnpjsGrupo implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private String cnpj;
	private String uf;
	private String tipo;

	
	public SintegraWS_CnpjsGrupo() {
		super();
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}