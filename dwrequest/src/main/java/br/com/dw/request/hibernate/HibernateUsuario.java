package br.com.dw.request.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.DAOUsuario;
import br.com.dw.request.entidades.Usuario;
import br.com.dw.request.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUsuario extends DAOGenericoHibernate<Usuario> implements DAOUsuario,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuario(){
		super(Usuario.class);
	}

}
