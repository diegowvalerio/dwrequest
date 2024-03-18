package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOUsuarioModulo;
import br.com.dw.request.entidades.UsuarioModulo;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUsuarioModulo extends DAOGenericoHibernate<UsuarioModulo> implements DAOUsuarioModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuarioModulo(){
		super(UsuarioModulo.class);
	}


}
