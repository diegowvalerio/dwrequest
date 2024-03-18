package br.com.dw.request.fabrica;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import br.com.dw.request.generico.seven.TransacaoSeven;


@ApplicationScoped
public class EntityManagerProducerSeven implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
	@Qualifier
	public @interface Corporativo {
	}
	
	
	@PersistenceUnit(unitName = "seven")
	private EntityManagerFactory factoryseven;
	
	
	@Produces 
	@RequestScoped
	@Corporativo
	public EntityManager get(){
		return factoryseven.createEntityManager();
	}
	

}
