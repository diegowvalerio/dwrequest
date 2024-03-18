package br.com.dw.request.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import br.com.dw.request.entidades.ConfCadastro;
import br.com.dw.request.integracao.SintegraWS_Saldo;
import br.com.dw.request.servico.ServicoConfCadastro;
import br.com.dw.request.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanConfCadastro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ConfCadastro confcadastro = new ConfCadastro();
	@Inject
	private ServicoConfCadastro servico;
	private List<ConfCadastro> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		if (lista.size() > 0) {
			this.confcadastro = lista.get(0);
		}else {		
			this.confcadastro = this.getConfcadastro();
		}
	}
		
	public String salvar(){
		servico.salvar(confcadastro);
		lista = servico.consultar();
		return "confcadastro";
	}
	
	public void excluir(){
		try{
		servico.excluir(confcadastro.getIdconfcadastro());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public String converteJsonEmString(BufferedReader buffereReader) throws IOException {
		String resposta, jsonString = "";
	    while ((resposta = buffereReader.readLine()) != null) {
	        jsonString += resposta;
	    }
	    return jsonString;
	}
	
	
	public void sintegraws_verificasaldo() throws Exception {	
		if(confcadastro.getSintegraws_token() != null) {
		try {						
			String enderecoURL = "https://www.sintegraws.com.br/api/v1/consulta-saldo.php?token="+confcadastro.getSintegraws_token();
	        URL url = new URL(enderecoURL);
	        HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
	        conexao.setRequestMethod("GET");
	        conexao.setDoInput(true);
	        try {
	            BufferedReader buff = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "utf-8"));

	            String convertJsonString = converteJsonEmString(buff);
	            if(convertJsonString.contains("ERROR")) {
	            	FacesMessageUtil.addMensagemWarn("Token inválido.");
	            }else {
	            	@SuppressWarnings("unused")
					Gson gson = new Gson();
	            	SintegraWS_Saldo saldo = gson.fromJson(convertJsonString, SintegraWS_Saldo.class);	     
	            	confcadastro.setSintegraws_qtd_consultas_disponiveis(saldo.getQtd_consultas_disponiveis());	            		            		
	            }
	            
	        } catch (Exception msgErro) {
	            //throw  new Exception("Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
	            FacesMessageUtil.addMensagemError("Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
	        }			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	  }else {
		  FacesMessageUtil.addMensagemInfo("Informe o Token !");
	  }
	}

	public ConfCadastro getConfcadastro() {
		return confcadastro;
	}

	public void setConfcadastro(ConfCadastro confcadastro) {
		this.confcadastro = confcadastro;
	}

	public List<ConfCadastro> getLista() {
		return lista;
	}

	public void setLista(List<ConfCadastro> lista) {
		this.lista = lista;
	}

	

}
