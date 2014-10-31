package com.serviciorest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.core.data.entites.Usuario;
import com.core.service.negocio.locales.ServiciosSeguridadImpl;
import com.serviciorest.modelo.Persona;
import com.serviciorest.repositorio.PersonasStub;

//personas
@Path("personas") 
public class ServicioPersonas {
	 
	private PersonasStub personaStub = new PersonasStub();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Persona> getPersonas()
	{
		return personaStub.getPersonas();
	}
		
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{personaId}")
	public Persona getPersona(@PathParam("personaId") String id)
	{
		
		ServiciosSeguridadImpl servicioSeguridad = new ServiciosSeguridadImpl();
		Usuario usuario = servicioSeguridad.buscarUsuario(id);
		Persona p = new Persona();
		p.setId(1);
		p.setNombre("brian");
		p.setApellido("apellido");
		
		return p;
		
		//return personaStub.getPersona(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("funcionPrueba")
	public String funciona(@QueryParam("num") String numero)
	{
		return personaStub.funciona(numero);
	}
	/*@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPersonasHTML()
	{
		return "<p>Personas</p>";
	}*/

}
