package br.com.dw.request.generico;

import java.util.List;


public interface DAOGenerico<E> {
	public E salvar(E e);
	public E alterar(E e);
	public boolean excluir(Integer id);
	public E consultar(Integer id);
	public List<E> consultar();	
	public List<E> consultar_ativos();	
	
	public List<E> buscacidadenome(String e);
	public List<E> buscacidadeibge(String e);
	public List<E> buscavendedor(boolean bovendedor);
	public List<E> buscacliente(boolean bocliente);
	
	
	//ws
	public List<E> consultarlogin(String login,String senha);
	
	
}
