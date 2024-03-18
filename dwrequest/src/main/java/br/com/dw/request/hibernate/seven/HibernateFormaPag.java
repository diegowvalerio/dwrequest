package br.com.dw.request.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.request.dao.seven.DAOFormaPag;
import br.com.dw.request.entidades.seven.FormaPag;
import br.com.dw.request.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateFormaPag extends DAOGenericoHibernateSeven<FormaPag> implements DAOFormaPag,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFormaPag(){
		super(FormaPag.class);
	}


}
