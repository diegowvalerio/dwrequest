package br.com.dw.request.integracao;

import java.io.Serializable;


public class SintegraWS_Ibge implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private String codigo_municipio;
	private String codigo_uf;

	
	public SintegraWS_Ibge() {
		super();
	}


	public String getCodigo_municipio() {
		return codigo_municipio;
	}


	public void setCodigo_municipio(String codigo_municipio) {
		this.codigo_municipio = codigo_municipio;
	}


	public String getCodigo_uf() {
		return codigo_uf;
	}


	public void setCodigo_uf(String codigo_uf) {
		this.codigo_uf = codigo_uf;
	}

}