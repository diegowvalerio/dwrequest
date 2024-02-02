package br.com.dw.request.integracao;

import java.io.Serializable;


public class SintegraWS_Atividade implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private String text;
	private String code;


	public SintegraWS_Atividade() {
		super();
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


}