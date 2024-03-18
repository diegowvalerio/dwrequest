package br.com.dw.request.generico.seven;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.dw.request.entidades.seven.CondPgto;
import br.com.dw.request.entidades.seven.FormaPag;
import br.com.dw.request.fabrica.EntityManagerProducerSeven.Corporativo;

@SuppressWarnings("unchecked")
public class DAOGenericoHibernateSeven<E> implements DAOGenericoSeven<E>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Corporativo
	protected EntityManager manager;
	
	@SuppressWarnings("rawtypes")
	private Class classeEntidade;
	
	@SuppressWarnings("rawtypes")
	public DAOGenericoHibernateSeven(Class classeEntidade){
		this.classeEntidade = classeEntidade;
	}
	
	
	public List<FormaPag> formapag(){
		List<FormaPag> list = new ArrayList<>();
		
		String sql= ""
				+ " select "
				+ " f.formacobrancaid , "
				+ " f.nome_formacob , "
				+ " f.desc_integracao "
				+ " from formacobranca f "
				+ " where f.bo_disponibilizar_web = 'SIM' "
				+ " and f.desc_integracao  is not null ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			FormaPag f = new FormaPag();
			
			f.setIdformapag(((BigDecimal) row[0]).intValue());
			f.setNome((String)row[1]);
			f.setDesc_integracao((String)row[2]);
			
			list.add(f);
			
		}
		return list;
	}
	
	public List<CondPgto> condPgto(String login){
		List<CondPgto> list = new ArrayList<>();
		
		String sql= ""
				+ " select "
				+ " F.formapagtoid , "
				+ " f.nome_formapagto , "
				+ " f.desc_integracao_formapagto , "
				+ " tv.tabelaprecoid "
				+ " from formapagto f "
				+ " inner join tabelapreco_prazopagto tp on tp.formapagtoid = f.formapagtoid "
				+ " inner join tabelapreco_vendedor tv on tv.tabelaprecoid = tp.tabelaprecoid "
				+ " where status_formapagto = 'ATIVO' "
				+ " and bo_disponibilizar_web = 'SIM' "
				+ " and desc_integracao_formapagto is not null "
				+ " and tv.cadcftvid = "+login;
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			CondPgto f = new CondPgto();
			
			f.setIdcondpgto(((BigDecimal) row[0]).intValue());
			f.setNome((String)row[1]);
			f.setDesc_integracao((String)row[2]);
			f.setTabelaprecoid(((BigDecimal)row[3]).intValue());
			
			list.add(f);
		}
		return list;
	}

}
