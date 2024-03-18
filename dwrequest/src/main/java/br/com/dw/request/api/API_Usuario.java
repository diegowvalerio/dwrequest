package br.com.dw.request.api;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.dw.request.entidades.Usuario;
import br.com.dw.request.servico.ServicoUsuario;


@Path("/usuario")
@RequestScoped
public class API_Usuario {
	
	@Inject
	private ServicoUsuario servico;
	private List<Usuario> lista;
	
	/* mostra todos os usuarios do sistema
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String todosusuarios(){
		lista = servico.consultar();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	    String cond = gson.toJson(lista);
		return cond;
	}
	*/
	
	/*mostra o usuario especifico pelo id
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String usuario(@PathParam("id") Integer id){
		Usuario usuario = servico.consultar(id);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	    String user = gson.toJson(usuario);
		return user;
	}
	*/
	
	//valida login e senha 
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/login")
	public String usuario(@QueryParam("login") String login,@QueryParam("senha") String senha){
		lista = servico.consultarlogin(login, senha);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	    String user = gson.toJson(lista);
		return user;
	}
	

}
