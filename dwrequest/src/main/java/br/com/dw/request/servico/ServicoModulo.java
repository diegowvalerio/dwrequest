package br.com.dw.request.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.dao.DAOModulo;
import br.com.dw.request.entidades.Modulo;
import br.com.dw.request.generico.Transacao;

@Dependent
public class ServicoModulo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOModulo dao;
	
	@Transacao
	public void salvar(Modulo modulo){
		try {
			if(modulo.getIdmodulo() == null){
				dao.salvar(modulo);
			}else{
				dao.alterar(modulo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Modulo> consultar(){
		return dao.consultar();
	}

}
