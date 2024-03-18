package br.com.dw.request.api;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.dw.request.entidades.seven.FormaPag;
import br.com.dw.request.servico.seven.ServicoFormaPag;


@Path("/formapag")
@RequestScoped
public class API_FormPag {
	
	@Inject
	private ServicoFormaPag servico;
	private List<FormaPag> lista;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String usuario(){
		lista = servico.formapag();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	    String user = gson.toJson(lista);
		return user;
	}
	

}
