package br.com.dw.request.integracao;

import java.io.Serializable;


public class SintegraWS_Saldo implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private String code;
	private String status;
	private String message;
	private String qtd_consultas_disponiveis;

	public SintegraWS_Saldo() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQtd_consultas_disponiveis() {
		return qtd_consultas_disponiveis;
	}

	public void setQtd_consultas_disponiveis(String qtd_consultas_disponiveis) {
		this.qtd_consultas_disponiveis = qtd_consultas_disponiveis;
	}

}