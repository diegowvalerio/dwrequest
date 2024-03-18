package br.com.dw.request.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.seven.DAOCondPgto;
import br.com.dw.request.entidades.seven.CondPgto;
import br.com.dw.request.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateCondicaoPagto extends DAOGenericoHibernateSeven<CondPgto> implements DAOCondPgto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCondicaoPagto(){
		super(CondPgto.class);
	}


}
