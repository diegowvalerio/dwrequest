package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOCidade;
import br.com.dw.request.entidades.Cidade;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCidade extends DAOGenericoHibernate<Cidade> implements DAOCidade,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCidade(){
		super(Cidade.class);
	}


}
