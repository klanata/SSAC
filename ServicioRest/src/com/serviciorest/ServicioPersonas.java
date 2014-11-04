package com.serviciorest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import clienteutility.ClienteUtility;

import com.core.data.entites.Usuario;
import com.core.service.negocio.ServiciosSeguridad;
import com.serviciorest.modelo.Persona;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Path("personas") 
@Stateless
@LocalBean
public class ServicioPersonas {
	 
	
	private ServiciosSeguridad manager;
	
	private Context context;
	
		
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Persona> getPersonas() throws Exception
	{
		manager = null;
		context = null;
		Persona p = new Persona();
		long idUsuario = 1;
		p.setId(idUsuario);
		p.setNombre("nombre");
		p.setEmail("email");
		Date fechaNac = new Date();
		p.setFechaNac(fechaNac);
		p.setNick("nick");
		List<Persona> listaPersona = new  ArrayList<Persona>(0);
		listaPersona.add(p);
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (ServiciosSeguridad) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServiciosSeguridadImpl!com.core.service.negocio.ServiciosSeguridad");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		return listaPersona;
	}
		
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{personaId}")
	public Persona getPersona(@PathParam("personaId") String id) throws Exception
	{
		manager = null;
		
		 context = null;
		
		//por defecto dejamos a la persona sin nada
		Persona p = new Persona();
		long idUsuario = 1;
		p.setId(idUsuario);
		p.setNombre("nombre");
		p.setEmail("email");
		Date fechaNac = new Date();
		p.setFechaNac(fechaNac);
		p.setNick("nick");
		
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (ServiciosSeguridad) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServiciosSeguridadImpl!com.core.service.negocio.ServiciosSeguridad");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		try {
				Usuario usuario = manager.buscarUsuario(id);
				
				p.setId(usuario.getId());
				p.setNombre(usuario.getNombre());
				p.setEmail(usuario.getEmail());
				p.setNick(usuario.getNick());
				p.setFechaNac(usuario.getFechaNac());
				
		} catch (NamingException e) {
            e.printStackTrace();
        }
		
		
				
		return p;
		
	}
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("crearUsuario")
	public void getCrearUsuario(@QueryParam("num") String nick) throws Exception
	{
		manager = null;
		context = null;
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (ServiciosSeguridad) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServiciosSeguridadImpl!com.core.service.negocio.ServiciosSeguridad");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
			
			String login = nick;
			String password ="1120212";
			String email = "email@rest.com";
			String nombre = "nombreResst"; 
			Date fechaNac = new Date();
			manager.ingesarUsuraio(login, password, email, nombre, fechaNac);
			
			
		
		
	}
	///////////////////////////////////////////////////////////////////////////
}