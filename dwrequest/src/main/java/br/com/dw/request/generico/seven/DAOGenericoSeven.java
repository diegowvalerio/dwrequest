package br.com.dw.request.generico.seven;

import java.util.List;

import br.com.dw.request.entidades.seven.CondPgto;
import br.com.dw.request.entidades.seven.FormaPag;


public interface DAOGenericoSeven<E> {

	public List<CondPgto> condPgto(String login);
	public List<FormaPag> formapag();
	
}
