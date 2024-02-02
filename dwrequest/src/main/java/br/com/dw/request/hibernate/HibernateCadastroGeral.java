package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.classe.CadastroGeral;
import br.com.dw.request.dao.DAOCadastroGeral;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCadastroGeral extends DAOGenericoHibernate<CadastroGeral> implements DAOCadastroGeral,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCadastroGeral(){
		super(CadastroGeral.class);
	}


}
