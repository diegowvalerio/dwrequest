package br.com.dw.request.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.dao.seven.DAOCondPgto;
import br.com.dw.request.entidades.seven.CondPgto;
import br.com.dw.request.generico.seven.TransacaoSeven;

@Dependent
public class ServicoCondPgto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCondPgto dao;
	
	@TransacaoSeven
	public List<CondPgto> condPgto(String login){
		return dao.condPgto(login);
	}
	
}
