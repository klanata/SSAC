package com.serviciorest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.serviciorest.modelo.Persona;
import com.serviciorest.repositorio.PersonasStub;
import com.core.service.negocio.ServiciosSeguridad;

//personas
@Path("personas") 
public class ServicioPersonas {
	 
	private PersonasStub personaStub = new PersonasStub();
	
	@EJB
	private ServiciosSeguridad servicioSeguridad;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Persona> getPersonas()
	{
	  return personaStub.getPersonas();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
	@Path("hola")
	public String getHola()
	{
		
		String h = servicioSeguridad.getHola();
		return h;
	}
		
	//2
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{personaId}")
	public Persona getPersona(@PathParam("personaId") String personaId)
	{
		
		return personaStub.getPersona(personaId);
	}
	
	/*@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPersonasHTML()
	{
		return "<p>Personas</p>";
	}*/

}
