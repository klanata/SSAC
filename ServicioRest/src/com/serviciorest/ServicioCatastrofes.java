package com.serviciorest;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.CatastrofeEBR;
import com.serviciorest.modelo.Catastrofe;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Path("catastrofes") 
@Stateless
@LocalBean
public class ServicioCatastrofes {

private CatastrofeEBR manager;
	
	private Context context;
	
	private String conexion = "ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR";
	
		
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Catastrofe> getCatastrofes() throws Exception
	{
		manager = null;
		context = null;
		
		List<com.serviciorest.modelo.Catastrofe> catastrofesRest = null;
        List<com.core.data.entites.Catastrofe> catastrofesCore;
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (CatastrofeEBR) context.lookup(conexion);
            catastrofesRest = new ArrayList<com.serviciorest.modelo.Catastrofe>();
            catastrofesCore = new ArrayList<com.core.data.entites.Catastrofe>();
            catastrofesCore = manager.listaCatastrofes();
           for (int i = 0; i < catastrofesCore.size(); i++) {
        	   com.serviciorest.modelo.Catastrofe catastrofeAux = new Catastrofe();
        	   catastrofeAux.setId((long)catastrofesCore.get(i).getId());
        	   catastrofeAux.setNombreEvento((catastrofesCore.get(i).getNombreEvento()));
        	   catastrofesRest.add(catastrofeAux);
		}
            
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		return catastrofesRest;
	}
	
}