package br.com.dw.request.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.request.entidades.Modulo;
import br.com.dw.request.servico.ServicoModulo;
import br.com.dw.request.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanModulo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Modulo modulo = new Modulo();
	@Inject
	private ServicoModulo servico;
	
	private List<Modulo> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.modulo = this.getModulo();
	}
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Modulo> getLista() {
		return lista;
	}

	public void setLista(List<Modulo> lista) {
		this.lista = lista;
	}

	public String salvar(){
		try{
		servico.salvar(modulo);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		
		return "lista-modulo";
	}

	public String excluir() {
		try{
		servico.excluir(modulo.getIdmodulo());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-modulo";
	}

}
