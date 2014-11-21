package com.serviciorest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import clienteutility.ClienteUtility;

import com.core.data.entites.Rescatista;
import com.core.service.negocio.remote.RescatistaEBR;
import com.serviciorest.modelo.Persona;

@Path("rescatista") 
@Stateless
@LocalBean
public class ServicioRescatistas {

private RescatistaEBR manager;
	
	private Context context;
	
	private String conexion = "ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR";
	
		
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("login")
	public Persona login(@QueryParam("nick") String nick, @QueryParam("pass") String pass) throws Exception
	{
		manager = null;
		context = null;
		Rescatista rescatistaAux = new Rescatista();
		Persona rescatista = new Persona();
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup(conexion);
            rescatistaAux = manager.buscarUsuario(nick, pass);
            
            rescatista.setNick(rescatistaAux.getNick());
            rescatista.setEmail(rescatistaAux.getEmail());
            rescatista.setId(rescatistaAux.getId());
            rescatista.setNombre(rescatistaAux.getNombre());
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return rescatista;
	}
}
