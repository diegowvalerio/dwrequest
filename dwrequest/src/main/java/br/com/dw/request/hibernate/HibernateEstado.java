package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOEstado;
import br.com.dw.request.entidades.Estado;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEstado extends DAOGenericoHibernate<Estado> implements DAOEstado,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEstado(){
		super(Estado.class);
	}

}
