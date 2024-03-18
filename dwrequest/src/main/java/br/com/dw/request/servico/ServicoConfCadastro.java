package br.com.dw.request.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.dao.DAOConfCadastro;
import br.com.dw.request.entidades.ConfCadastro;
import br.com.dw.request.generico.Transacao;

@Dependent
public class ServicoConfCadastro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOConfCadastro dao;
	
	@Transacao
	public void salvar(ConfCadastro estado){
		try {
			if(estado.getIdconfcadastro() == null){
				dao.salvar(estado);
			}else{
				dao.alterar(estado);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<ConfCadastro> consultar(){
		return dao.consultar();
	}
}
