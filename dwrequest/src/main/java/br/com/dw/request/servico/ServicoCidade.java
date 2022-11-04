package br.com.dw.request.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.classe.Cidade;
import br.com.dw.request.dao.DAOCidade;
import br.com.dw.request.generico.Transacao;

@Dependent
public class ServicoCidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCidade dao;
	
	@Transacao
	public void salvar(Cidade cidade){
		try {
			if(cidade.getIdcidade() == null){
				dao.salvar(cidade);
			}else{
				dao.alterar(cidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Cidade> buscacidadenome(String nome){
		List<Cidade> v = null;
		if(!nome.equals("")){
			v = dao.buscacidadenome(nome);
		}
		return v;
	}
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Cidade> consultar(){
		return dao.consultar();
	}
	
}
