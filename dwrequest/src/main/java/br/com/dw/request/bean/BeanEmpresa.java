package br.com.dw.request.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dw.request.entidades.Cidade;
import br.com.dw.request.entidades.Empresa;
import br.com.dw.request.servico.ServicoCidade;
import br.com.dw.request.servico.ServicoEmpresa;
import br.com.dw.request.util.FacesMessageUtil;


@Named
@ViewScoped
public class BeanEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Empresa empresa = new Empresa();
	@Inject
	private ServicoEmpresa servico;
	private List<Empresa> lista;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	private String opcao;
	private Date data;
	
	
	
	public BeanEmpresa() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.empresa = (Empresa) session.getAttribute("empresaAux");
			
		session.removeAttribute("empresaAux");
			
		lista = servico.consultar();
		
		if(this.empresa == null) {
			this.empresa = this.getEmpresa();
		}
		
	}
	
	public String salvar(){
		if(empresa.getIdempresa() == null){
			empresa.setDtcadastro(data);
		}
		try{
		servico.salvar(empresa);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-empresa";
	}

	public String excluir() {
		try{
		servico.excluir(empresa.getIdempresa());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-empresa";
	}
	
	/* editar cadastro */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("empresaAux", this.empresa);

		return "edita-empresa";
	}
		
	
	public List<Cidade> completaCidade(String nome) {
		return servicoCidade.buscacidadenome(nome);
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}


}
