package br.com.dw.request.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.dao.DAOCadastroGeral;
import br.com.dw.request.entidades.CadastroGeral;
import br.com.dw.request.generico.Transacao;

@Dependent
public class ServicoCadastroGeral implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCadastroGeral dao;
	
	@Transacao
	public void salvar(CadastroGeral cidade){
		try {
			if(cidade.getIdcadastrogeral() == null){
				dao.salvar(cidade);
			}else{
				dao.alterar(cidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<CadastroGeral> consultar(){
		return dao.consultar();
	}
	
	public List<CadastroGeral> buscavendedor(boolean bovendedor){
		return dao.buscavendedor(bovendedor);
	}
	
	public List<CadastroGeral> buscacliente(boolean bocliente){
		return dao.buscacliente(bocliente);
	}
	
}
