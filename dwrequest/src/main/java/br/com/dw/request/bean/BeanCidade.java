package br.com.dw.request.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.request.entidades.Cidade;
import br.com.dw.request.entidades.Estado;
import br.com.dw.request.servico.ServicoCidade;
import br.com.dw.request.servico.ServicoEstado;
import br.com.dw.request.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanCidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Cidade cidade = new Cidade();
	@Inject
	private ServicoCidade servico;
	@Inject
	private ServicoEstado servicoEst;
	private List<Cidade> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.cidade = this.getCidade();
	}	
	
	public String salvar(){
		try{
		servico.salvar(cidade);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-cidade";
	}
	
	public void excluir(){
		try{
		servico.excluir(cidade.getIdcidade());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public List<Estado> getEstados2(){
		return servicoEst.consultar();
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getLista() {
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
		
}
