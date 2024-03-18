package br.com.dw.request.api;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.dw.request.entidades.seven.CondPgto;
import br.com.dw.request.servico.seven.ServicoCondPgto;


@Path("/condpgto")
@RequestScoped
public class API_CondPgto {
	
	@Inject
	private ServicoCondPgto servico;
	private List<CondPgto> lista;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/login")
	public String usuario(@QueryParam("login") String login){
		lista = servico.condPgto(login);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	    String user = gson.toJson(lista);
		return user;
	}
	

}
