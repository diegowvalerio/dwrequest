package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOEmpresa;
import br.com.dw.request.entidades.Empresa;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEmpresa extends DAOGenericoHibernate<Empresa> implements DAOEmpresa,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEmpresa(){
		super(Empresa.class);
	}


}
