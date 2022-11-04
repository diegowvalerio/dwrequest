package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.classe.Modulo;
import br.com.dw.request.dao.DAOModulo;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateModulo extends DAOGenericoHibernate<Modulo> implements DAOModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateModulo(){
		super(Modulo.class);
	}


}
