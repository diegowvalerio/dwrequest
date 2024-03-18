package br.com.dw.request.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/reset")
public class IniciaReset extends Application {
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<Class<?>>();
		addReset(resources);
		return resources;
	}
	
	public void addReset(Set<Class<?>> resources){
		resources.add(API_Usuario.class);
		resources.add(API_CondPgto.class);
		resources.add(API_FormPag.class);
	}

}
