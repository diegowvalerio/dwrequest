package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOConfCadastro;
import br.com.dw.request.entidades.ConfCadastro;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateConfCadastro extends DAOGenericoHibernate<ConfCadastro> implements DAOConfCadastro,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateConfCadastro(){
		super(ConfCadastro.class);
	}


}
