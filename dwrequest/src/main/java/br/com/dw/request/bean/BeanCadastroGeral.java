package br.com.dw.request.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.dw.request.classe.CadastroGeral;
import br.com.dw.request.classe.Cidade;
import br.com.dw.request.classe.ConfCadastro;
import br.com.dw.request.classe.Contato;
import br.com.dw.request.classe.Endereco;
import br.com.dw.request.classe.Estado;
import br.com.dw.request.integracao.SintegraWS_CPF;
import br.com.dw.request.integracao.SintegraWS_Cnpj;
import br.com.dw.request.integracao.SintegraWS_Ibge;
import br.com.dw.request.integracao.ViaCep;
import br.com.dw.request.servico.ServicoCadastroGeral;
import br.com.dw.request.servico.ServicoCidade;
import br.com.dw.request.servico.ServicoConfCadastro;
import br.com.dw.request.servico.ServicoEstado;
import br.com.dw.request.util.FacesMessageUtil;



@Named
@ViewScoped
public class BeanCadastroGeral implements Serializable{
	private static final long serialVersionUID = 1L;

	private CadastroGeral cadastrogeral = new CadastroGeral();
	private Endereco endereco = new Endereco();
	private Contato contato = new Contato();
	
	private List<Endereco> enderecos = new ArrayList<>();
	private List<Contato> contatos = new ArrayList<>();
	
	@Inject
	private ServicoConfCadastro servicoConfCadastro;
	private List<ConfCadastro> listaconfCadastro = new ArrayList<>();
	private ConfCadastro confCadastro = new ConfCadastro();
	
	@Inject
	private ServicoCadastroGeral servico;
	private List<CadastroGeral> lista;
	private List<CadastroGeral> listavendedores;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	@Inject
	private ServicoEstado servicoEstado;
	private List<Estado> listaestado;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	private Boolean renderizaSintegraWS_CPF = false;
	private Boolean renderizaSintegraWS_CNPJ = false;
	
	private Boolean renderizaVendedor = false;
	
	public BeanCadastroGeral() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		carregaconfiguracao();
		
		lista = servico.consultar();
		listaestado = servicoEstado.consultar();
		listavendedores = servico.buscavendedor(true);
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		CadastroGeral g = new CadastroGeral();
		g = (CadastroGeral) session.getAttribute("cadastrogeralAux");
		session.removeAttribute("cadastrogeralAux");
		if(g != null) {
			this.cadastrogeral = g;
			
			if (this.cadastrogeral.getTipojf().equals("J")) {
				isRederiza = true;
				isRederiza2 = false;
			}
			if (this.cadastrogeral.getTipojf().equals("F")) {
				isRederiza = false;
				isRederiza2 = true;
			}
			if(this.cadastrogeral.getBovendedor().booleanValue()==true) {
				renderizaVendedor = true;
			}

		}else {
			this.cadastrogeral = this.getCadastroGeral();
			this.cadastrogeral.setDtcadastro(data);
			this.cadastrogeral.setSituacao(true);
		}
		
		this.opcao = this.cadastrogeral.getTipojf();
		this.enderecos = this.cadastrogeral.getEnderecos();
		this.contatos = this.cadastrogeral.getContatos();
		
	}
	
	public void carregaconfiguracao() {
		listaconfCadastro = servicoConfCadastro.consultar();	
		if(listaconfCadastro.size()>0) {
			this.confCadastro = listaconfCadastro.get(0);
		}else {
			this.confCadastro = null;
		}
	}
	
	public String salvar(){
		if(cadastrogeral.getIdcadastrogeral() == null){
			cadastrogeral.setDtcadastro(data);
		}
		try{
		cadastrogeral.setEnderecos(enderecos);
		cadastrogeral.setContatos(contatos);
		servico.salvar(cadastrogeral);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-cadastrogeral";
	}


	public String excluir() {
		try{
		servico.excluir(cadastrogeral.getIdcadastrogeral());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-cadastrogeral";
	}
	
	public void sintegraws_cpf() throws IOException {
		DateFormat df2 = new SimpleDateFormat("ddMMyyyy");
		System.out.println(cadastrogeral.getCpf()+"--"+cadastrogeral.getDtnascimento());
		
		if (cadastrogeral.getCpf() != null && cadastrogeral.getDtnascimento() != null) {
			try {
				String enderecoURL = "https://www.sintegraws.com.br/api/v1/execute-api.php?token="+confCadastro.getSintegraws_token()+"&cpf="+cadastrogeral.getCpf().replaceAll("[.-]", "")+"&data-nascimento="+df2.format(cadastrogeral.getDtnascimento())+"&plugin=CPF";
				URL url = new URL(enderecoURL);
				HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
				conexao.setRequestMethod("GET");
				conexao.setDoInput(true);
				try {
					BufferedReader buff = new BufferedReader(
							new InputStreamReader((conexao.getInputStream()), "utf-8"));

					String convertJsonString = converteJsonEmString(buff);
					System.out.println(convertJsonString);
					
					if (convertJsonString.contains("erro")) {
						if (convertJsonString.contains("2")) {
							FacesMessageUtil.addMensagemWarn("CPF inválido.");
						}
						if (convertJsonString.contains("3")) {
							FacesMessageUtil.addMensagemWarn("Token inválido.");
						}
						if (convertJsonString.contains("4")) {
							FacesMessageUtil.addMensagemWarn("Usuário não contratou nenhum pacote de créditos.");
						}
						if (convertJsonString.contains("5")) {
							FacesMessageUtil.addMensagemWarn("Os créditos contratados acabaram.");
						}
						if (convertJsonString.contains("6")) {
							FacesMessageUtil.addMensagemWarn("Plugin não existe.");
						}
						if (convertJsonString.contains("7")) {
							FacesMessageUtil.addMensagemWarn("Site da Receita Federal CPF está com instabilidade.");
						}
						if (convertJsonString.contains("8")) {
							FacesMessageUtil.addMensagemWarn("Ocorreu um erro interno, por favor contatar o nosso suporte.");
						}
						
					}else if(convertJsonString.contains("Data de nascimento informada está divergente da base")) {
						FacesMessageUtil.addMensagemWarn("Data de nascimento informada está divergente da base de dados da Receita Federal do Brasil.");
					} else {
						Gson gson = new Gson();
						SintegraWS_CPF cpf = gson.fromJson(convertJsonString, SintegraWS_CPF.class);
						
						if (cpf.getCpf() != null && cpf.getCpf() != "") {
							cadastrogeral.setNome(cpf.getNome().toUpperCase());
							cadastrogeral.setObservacao("Situação CPF:"+cpf.getSituacao_cadastral());

						}
					}

				} catch (Exception msgErro) {
					// throw new Exception("Erro de conexão- status Code [" +
					// conexao.getResponseCode() + "]. " + msgErro.toString());
					FacesMessageUtil.addMensagemError(
							"Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			FacesMessageUtil.addMensagemInfo("CPF e Data de Nascimento são obrigatorios para consulta de CPF !");
		}

	}
	
	public void sintegraws_cnpj() throws IOException {
		if (cadastrogeral.getCnpj() != null) {
			try {
				String enderecoURL = "https://www.sintegraws.com.br/api/v1/execute-api.php?token="+confCadastro.getSintegraws_token()+"&cnpj="+cadastrogeral.getCnpj().replaceAll("[/.-]", "")+"&plugin=RF";
				URL url = new URL(enderecoURL);
				HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
				conexao.setRequestMethod("GET");
				conexao.setDoInput(true);
				try {
					BufferedReader buff = new BufferedReader(
							new InputStreamReader((conexao.getInputStream()), "utf-8"));

					String convertJsonString = converteJsonEmString(buff);
					//System.out.println(convertJsonString);
					
					if (convertJsonString.contains("erro")) {
						if (convertJsonString.contains("2")) {
							FacesMessageUtil.addMensagemWarn("CNPJ inválido.");
						}
						if (convertJsonString.contains("3")) {
							FacesMessageUtil.addMensagemWarn("Token inválido.");
						}
						if (convertJsonString.contains("4")) {
							FacesMessageUtil.addMensagemWarn("Usuário não contratou nenhum pacote de créditos.");
						}
						if (convertJsonString.contains("5")) {
							FacesMessageUtil.addMensagemWarn("Os créditos contratados acabaram.");
						}
						if (convertJsonString.contains("6")) {
							FacesMessageUtil.addMensagemWarn("Plugin não existe.");
						}
						if (convertJsonString.contains("7")) {
							FacesMessageUtil.addMensagemWarn("Site da Receita Federal está com instabilidade.");
						}
						if (convertJsonString.contains("8")) {
							FacesMessageUtil.addMensagemWarn("Ocorreu um erro interno, por favor contatar o nosso suporte.");
						}
						
					}else if(convertJsonString.contains("CNPJ não tem cadastro na Receita federal.")) {
						FacesMessageUtil.addMensagemWarn("CNPJ não tem cadastro na Receita federal.");
					} else {
						Gson gson = new Gson();
						SintegraWS_Cnpj cNPJ = gson.fromJson(convertJsonString, SintegraWS_Cnpj.class);
						//SintegraWS_Ibge ibge = gson.fromJson(convertJsonString, SintegraWS_Ibge.class);
						
						if (cNPJ.getCnpj() != null && cNPJ.getCnpj() != "") {
							cadastrogeral.setNome(cNPJ.getFantasia().toUpperCase());
							cadastrogeral.setRazao_social(cNPJ.getNome().toUpperCase());
							cadastrogeral.setObservacao("Situação CNPJ:"+cNPJ.getSituacao());
							
							
							Endereco end = new Endereco();
							end.setBairro(cNPJ.getBairro());
							end.setComplemento(cNPJ.getComplemento());
							end.setLogadouro(cNPJ.getLogradouro());
							end.setNumero(cNPJ.getNumero());
							end.setCep(cNPJ.getCep().replaceAll("[.-]", ""));
							end.setCadastrogeral(cadastrogeral);
							List<Cidade> cid = new ArrayList<>();
		            		cid.addAll(servicoCidade.buscacidadeibge(cNPJ.getIbge().getCodigo_municipio()));
		            		if(cid.size() >0) {
		            			endereco.setCidade(cid.get(0));
		            		}else {
		            			endereco.setCidade(null);
		            		}
							
							enderecos.add(end);
							
							Contato cont = new Contato();
							cont.setCadastrogeral(cadastrogeral);
							cont.setEmail(cNPJ.getEmail().toLowerCase());
							cont.setNomecontato("****");
							cont.setNumerocontato(cNPJ.getTelefone());
							cont.setObservacaocontato("Dados da consulta de CNPJ");
							
							contatos.add(cont);

						}
					}

				} catch (Exception msgErro) {
					// throw new Exception("Erro de conexão- status Code [" +
					// conexao.getResponseCode() + "]. " + msgErro.toString());
					FacesMessageUtil.addMensagemError(
							"Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			FacesMessageUtil.addMensagemInfo("CNPJ obrigatorio para consulta !");
		}
	}
	
	public void consultacep() throws Exception {	
		if(endereco.getCep()!= null) {
		try {						
			String enderecoURL = "https://viacep.com.br/ws/" +endereco.getCep().replaceAll("[.-]", "")+ "/json/";
	        URL url = new URL(enderecoURL);
	        HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
	        conexao.setRequestMethod("GET");
	        conexao.setDoInput(true);
	        try {
	            BufferedReader buff = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "utf-8"));

	            String convertJsonString = converteJsonEmString(buff);
	            if(convertJsonString.contains("erro")) {
	            	FacesMessageUtil.addMensagemWarn("CEP inválido");
	            }else {
	            	Gson gson = new Gson();
	            	ViaCep viacep = gson.fromJson(convertJsonString, ViaCep.class);
	            
	            	if(viacep.getIbge() != null && viacep.getIbge() != "") {
	            		endereco.setBairro(viacep.getBairro().toUpperCase());
	            		endereco.setLogadouro(viacep.getLogradouro().toUpperCase());
	            		endereco.setNumero("");
	            		endereco.setComplemento("");
	            		List<Cidade> cid = new ArrayList<>();
	            		cid.addAll(servicoCidade.buscacidadeibge(viacep.getIbge()));
	            		if(cid.size() >0) {
	            			endereco.setCidade(cid.get(0));
	            		}else {
	            			endereco.setCidade(null);
	            		}
	            		
	            	}
	            }
	            
	        } catch (Exception msgErro) {
	            //throw  new Exception("Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
	            FacesMessageUtil.addMensagemError("Erro de conexão- status Code [" + conexao.getResponseCode() + "]. " + msgErro.toString());
	        }			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	  }
	}
	
	public String converteJsonEmString(BufferedReader buffereReader) throws IOException {
	String resposta, jsonString = "";
    while ((resposta = buffereReader.readLine()) != null) {
        jsonString += resposta;
    }
    return jsonString;
	}
	/* editar cadastro */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("cadastrogeralAux", this.cadastrogeral);

		return "cadastro-geral";
	}
	
	public String cancelar(){
		return "lista-cadastrogeral";
	}
	
	public void renderizar() {
		if (this.cadastrogeral.getTipojf().equals("J")) {
			isRederiza = true;
			isRederiza2 = false;
			cadastrogeral.setCpf(null);
			cadastrogeral.setRg(null);
			if(verificaconf() == true) {
				renderizaSintegraWS_CNPJ = true;
				renderizaSintegraWS_CPF = false;
			}

		}
		if (this.cadastrogeral.getTipojf().equals("F")) {
			isRederiza = false;
			isRederiza2 = true;
			cadastrogeral.setCnpj(null);
			cadastrogeral.setInsc_estadual(null);
			cadastrogeral.setRazao_social(null);
			
			if(verificaconf() == true) {
				renderizaSintegraWS_CNPJ = false;
				renderizaSintegraWS_CPF = true;
			}
		}

	}
	
	public void renderizarVende() {
		if(this.cadastrogeral.getBocliente().equals(true)) {
			renderizaVendedor = true;
		}else {
			renderizaVendedor = false;
		}
		
	}
	
	public boolean verificaconf() {
		if(confCadastro != null) {
			if(confCadastro.getIntegra_sintegraws() == true) {
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public List<Cidade> completaCidade(String nome) {
		return servicoCidade.buscacidadenome(nome);
	}
	
	public void addNovoEndereco() {
		if (this.cadastrogeral == null) {
			throw new RuntimeException("O cliente não pode ser nulo");
		} else {
			this.endereco = new Endereco();
			// this.endereco.setPessoa(cliente);
		}
	}

	public void removerEndereco() {
		this.enderecos.remove(endereco);
	}
	
	public void editarEnd() {
		if (endereco == null) {
			throw new IllegalArgumentException("Cliente nao pode ser nulo");
		}
		if (endereco.getLogadouro().length() > 0 && endereco.getCep().length() > 0 && endereco.getNumero().length() > 0) {
			int index = enderecos.indexOf(endereco);
			if (index > -1) {
				enderecos.remove(index);
				endereco.setCadastrogeral(cadastrogeral);
				enderecos.add(index, endereco);
			} else {
				endereco.setCadastrogeral(cadastrogeral);
				enderecos.add(endereco);
			}

			endereco = new Endereco();
		} else {
			FacesMessageUtil.addMensagemWarn("Preencha todos os dados");
		}
	}
	
	public void addcontato() {
		if (contato == null) {
			throw new IllegalArgumentException("Cliente nao pode ser nulo");
		}
		if (contato.getNomecontato().length() > 0 && contato.getEmail().length() > 0 && contato.getNumerocontato().length() > 0) {
			int index = contatos.indexOf(contato);
			if (index > -1) {
				contatos.remove(index);
				contato.setCadastrogeral(cadastrogeral);
				contatos.add(index, contato);
			} else {
				contato.setCadastrogeral(cadastrogeral);
				contatos.add(contato);
			}

			contato = new Contato();
		} else {
			FacesMessageUtil.addMensagemWarn("Preencha todos os dados");
		}
	}

	public void novocontato() {
		this.contato = new Contato();

	}
	public void excluirContato() {
		// servico.excluir(this.contato.getIdcontato());
		this.contatos.remove(contato);
	}
	
	
	
	
	
	public Boolean getRenderizaSintegraWS_CPF() {
		return renderizaSintegraWS_CPF;
	}

	public void setRenderizaSintegraWS_CPF(Boolean renderizaSintegraWS_CPF) {
		this.renderizaSintegraWS_CPF = renderizaSintegraWS_CPF;
	}

	public Boolean getRenderizaSintegraWS_CNPJ() {
		return renderizaSintegraWS_CNPJ;
	}

	public void setRenderizaSintegraWS_CNPJ(Boolean renderizaSintegraWS_CNPJ) {
		this.renderizaSintegraWS_CNPJ = renderizaSintegraWS_CNPJ;
	}

	public List<ConfCadastro> getListaconfCadastro() {
		return listaconfCadastro;
	}

	public void setListaconfCadastro(List<ConfCadastro> listaconfCadastro) {
		this.listaconfCadastro = listaconfCadastro;
	}

	public ConfCadastro getConfCadastro() {
		return confCadastro;
	}

	public void setConfCadastro(ConfCadastro confCadastro) {
		this.confCadastro = confCadastro;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Boolean getIsRederiza() {
		return isRederiza;
	}

	public void setIsRederiza(Boolean isRederiza) {
		this.isRederiza = isRederiza;
	}

	public Boolean getIsRederiza2() {
		return isRederiza2;
	}

	public void setIsRederiza2(Boolean isRederiza2) {
		this.isRederiza2 = isRederiza2;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public CadastroGeral getCadastroGeral() {
		return cadastrogeral;
	}

	public void setCadastroGeral(CadastroGeral cadastrogeral) {
		this.cadastrogeral = cadastrogeral;
	}

	public List<CadastroGeral> getLista() {
		return lista;
	}

	public void setLista(List<CadastroGeral> lista) {
		this.lista = lista;
	}

	public CadastroGeral getCadastrogeral() {
		return cadastrogeral;
	}

	public void setCadastrogeral(CadastroGeral cadastrogeral) {
		this.cadastrogeral = cadastrogeral;
	}

	public List<Estado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<Estado> listaestado) {
		this.listaestado = listaestado;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<CadastroGeral> getListavendedores() {
		return listavendedores;
	}

	public void setListavendedores(List<CadastroGeral> listavendedores) {
		this.listavendedores = listavendedores;
	}

	public Boolean getRenderizaVendedor() {
		return renderizaVendedor;
	}

	public void setRenderizaVendedor(Boolean renderizaVendedor) {
		this.renderizaVendedor = renderizaVendedor;
	}

}
