package br.com.dw.request.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.request.dao.seven.DAOFormaPag;
import br.com.dw.request.entidades.seven.FormaPag;
import br.com.dw.request.generico.seven.TransacaoSeven;

@Dependent
public class ServicoFormaPag implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOFormaPag dao;
	
	@TransacaoSeven
	public List<FormaPag> formapag(){
		return dao.formapag();
	}
	
}
