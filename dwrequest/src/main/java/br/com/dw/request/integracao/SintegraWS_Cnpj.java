package br.com.dw.request.integracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SintegraWS_Cnpj implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private String code;
	private String status;
	private String message;
	private String cnpj;
	private String tipo;
	private String porte;
	private String situacao;
	private String abertura;
	private String nome;
	private String fantasia;
	private String natureza_juridica;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String municipio;
	private String uf;
	private String email;
	private String telefone;
	private String efr;
	private String data_situacao;
	private String motivo_situacao;
	private String situacao_especial;
	private String data_situacao_especial;
	private String capital_social;
	
	private List<SintegraWS_Atividade> atividade_principal = new ArrayList<>(); 
	private List<SintegraWS_Atividade> atividades_secundarias = new ArrayList<>();
	private List<SintegraWS_Qsa> qsa = new ArrayList<>();
	private SintegraWS_Ibge ibge;
	private List<SintegraWS_CnpjsGrupo> cnpjs_do_grupo = new ArrayList<>();


	public SintegraWS_Cnpj() {
		super();
	}


	public SintegraWS_Ibge getIbge() {
		return ibge;
	}


	public void setIbge(SintegraWS_Ibge ibge) {
		this.ibge = ibge;
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


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getPorte() {
		return porte;
	}


	public void setPorte(String porte) {
		this.porte = porte;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getAbertura() {
		return abertura;
	}


	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getFantasia() {
		return fantasia;
	}


	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}


	public String getNatureza_juridica() {
		return natureza_juridica;
	}


	public void setNatureza_juridica(String natureza_juridica) {
		this.natureza_juridica = natureza_juridica;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
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


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEfr() {
		return efr;
	}


	public void setEfr(String efr) {
		this.efr = efr;
	}


	public String getData_situacao() {
		return data_situacao;
	}


	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}


	public String getMotivo_situacao() {
		return motivo_situacao;
	}


	public void setMotivo_situacao(String motivo_situacao) {
		this.motivo_situacao = motivo_situacao;
	}


	public String getSituacao_especial() {
		return situacao_especial;
	}


	public void setSituacao_especial(String situacao_especial) {
		this.situacao_especial = situacao_especial;
	}


	public String getData_situacao_especial() {
		return data_situacao_especial;
	}


	public void setData_situacao_especial(String data_situacao_especial) {
		this.data_situacao_especial = data_situacao_especial;
	}


	public String getCapital_social() {
		return capital_social;
	}


	public void setCapital_social(String capital_social) {
		this.capital_social = capital_social;
	}


	public List<SintegraWS_Atividade> getAtividade_principal() {
		return atividade_principal;
	}


	public void setAtividade_principal(List<SintegraWS_Atividade> atividade_principal) {
		this.atividade_principal = atividade_principal;
	}


	public List<SintegraWS_Atividade> getAtividades_secundarias() {
		return atividades_secundarias;
	}


	public void setAtividades_secundarias(List<SintegraWS_Atividade> atividades_secundarias) {
		this.atividades_secundarias = atividades_secundarias;
	}


	public List<SintegraWS_Qsa> getQsa() {
		return qsa;
	}


	public void setQsa(List<SintegraWS_Qsa> qsa) {
		this.qsa = qsa;
	}

	public List<SintegraWS_CnpjsGrupo> getCnpjs_do_grupo() {
		return cnpjs_do_grupo;
	}


	public void setCnpjs_do_grupo(List<SintegraWS_CnpjsGrupo> cnpjs_do_grupo) {
		this.cnpjs_do_grupo = cnpjs_do_grupo;
	}

	

}