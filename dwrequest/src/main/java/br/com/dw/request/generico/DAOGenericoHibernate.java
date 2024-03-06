package br.com.dw.request.generico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.dw.request.classe.CadastroGeral;
import br.com.dw.request.classe.Cidade;


public class DAOGenericoHibernate<E> implements DAOGenerico<E>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected EntityManager manager;
	@SuppressWarnings("rawtypes")
	private Class classeEntidade;
	
	@SuppressWarnings("rawtypes")
	public DAOGenericoHibernate(Class classeEntidade){
		this.classeEntidade = classeEntidade;
	}

	@Override
	public E salvar(E e) {
		manager.persist(e);
		return e;
	}
	
	
	@Override
	public E alterar(E e) {
		return manager.merge(e);
	}

	@Override
	public boolean excluir(Integer id) {
		E e = consultar(id);
		manager.remove(e);
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public E consultar(Integer id) {
		return (E) manager.find(classeEntidade, id);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultar() {
		return manager.createQuery("from "+classeEntidade.getSimpleName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultar_ativos() {		
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where situacao = true").getResultList();
	}
		
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<E> buscacidadenome(String e){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		criteria.add(Restrictions.ilike("nome", e.toUpperCase(),MatchMode.START));
		
		return criteria.list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<E> buscavendedor(boolean bovendedor){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CadastroGeral.class);
		
		criteria.add(Restrictions.eq("bovendedor", bovendedor));
		
		return criteria.list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<E> buscacliente(boolean bocliente){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CadastroGeral.class);
		
		criteria.add(Restrictions.eq("bocliente", bocliente));
		
		return criteria.list();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<E> buscacidadeibge(String e){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		criteria.add(Restrictions.eq("codigoibge", e.toUpperCase()));
		
		return criteria.list();
	}
	
	//painel de resumo
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<E> clientesnovos(Date data, Date data2){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classeEntidade);
		
		criteria.add(Restrictions.eq("situacao", Boolean.TRUE));
		criteria.add(Restrictions.eq("op_cliente", Boolean.TRUE));
		criteria.add(Restrictions.between("dtcadastro", data, data2));
		
		return criteria.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultarlogin(String login, String senha){
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where login = '"+login+"' and senha = '"+senha+"' and situacao = true and vendedor_idcadastrogeral is not null ").getResultList();
	}
	
}
