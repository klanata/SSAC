package com.core.service.negocio;
/*Autor
 * Stephy*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;
import com.core.data.persistencia.interfaces.locales.RescatistaDAO;
import com.core.service.negocio.remote.RescatistaEBR;

import cross_cuting.enums.PlanesPendientesRescatistaDTO;

@Path("/rescatista") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR")

public class RescatistaEB implements RescatistaEBR {
	
	@EJB
	private DataService dataService;
	
	@EJB 
	private RescatistaDAO rescatistaService;
	
	@EJB 
	private PlanDeRiesgoDAO planDeRiesgoService;
	

	@EJB 
	private CatastrofeDAO catastrofeService;
	
	//////////////////////////////////////////////////////////////////////////////
	public Collection<PlanesPendientesRescatistaDTO> listarPendientesRescatistaPorCatastrofe(String nick)
	{
		
		Collection<PlanesPendientesRescatistaDTO> listaDTO =new  ArrayList<PlanesPendientesRescatistaDTO>(0);
		
		try{
		       Collection<EstadoRescatista> listaEstado = rescatistaService.listarPendientesRescatista(nick);
		       
		       Iterator<EstadoRescatista> it = listaEstado.iterator();
		       
		       while(it.hasNext())
		       {
		    	   EstadoRescatista e = it.next();
		    	   Long idCatastrofe = e.getCatastrofe().getId();
		    	   Catastrofe c = dataService.find(Catastrofe.class, idCatastrofe);
		    	   Integer idPlanRiesgo = c.getPlanDeRiesgo().getId();
		    	   //obtengo planDeRiesgo
		    	   PlanDeRiesgo p = planDeRiesgoService.obtenerPlanDeRiesgo(idPlanRiesgo);
		    	   
		    	   PlanesPendientesRescatistaDTO planesDTO = new PlanesPendientesRescatistaDTO();
		    	   planesDTO.setUrlArchivo(p.getRutaArchivo());
		    	   planesDTO.setNombreTarea(e.getNombreTarea());
		    	   planesDTO.setIdCatastrofe(idCatastrofe);
		    	   planesDTO.setEstadoTarea(e.getPendiente());
		    	   planesDTO.setIdEstadoRescatista(e.getRescatista().getId());
		    	   
		    	   listaDTO.add(planesDTO);
		       }
		       
		       
		}catch (Exception e )
		{
			e.printStackTrace();
		}
		
		return listaDTO;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	@Override
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe) {
		
		try{
		
			Long id = new Long (catastrofe.getId());
			//obtengo objeto catastrofe
			Catastrofe catastrofeGuardar = dataService.find(Catastrofe.class, id);
			EstadoRescatista estadoRescatista = new EstadoRescatista();
			estadoRescatista.setPendiente(true);
			estadoRescatista.setCatastrofe(catastrofeGuardar);
			//FIXME: Stephy: victoria aca deje setado el nombre de la catastrofe ya que la tea no estaba pensado en todo caso 
			//me la tienen que pasar desde la catastrofe.
			estadoRescatista.setNombreTarea(catastrofeGuardar.getNombreEvento());
			//busco el rescatista para asignar
			Rescatista rescatista = rescatistaService.obtenerRescatistaConMenosPendientes();
			estadoRescatista.setRescatista(rescatista);
			dataService.create(estadoRescatista);
			
			//obtenemos la lista de pendientes del rescatista 
			Collection<EstadoRescatista> listaEstado= rescatista.getEstadoRescatista();
			listaEstado.add(estadoRescatista);
			dataService.update(rescatista);
			
		
			
		}catch (Exception e )
		{
			e.printStackTrace();
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////
	@Override
	public Long crearRescatista(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular) throws Exception {
			
		Rescatista r = new Rescatista();
		r.setNombre(nombre);
		r.setApellido(apellido);
		r.setCelular(celular);
		r.setEmail(email);
		r.setFechaNac(fechaNac);
		r.setPassword(password);
		r.setSexo(sexo);
		Long id = rescatistaService.insert(r);
		return id;
	}
	
	///////////////////////////////////////////////////////////////
	@Override
	public Boolean buscarUsuario(String nick, String password) {
		
		
		Boolean existe = false;
		
		try{
			Rescatista r = rescatistaService.buscarUsuario(nick, password);
		
			if(r!=null){existe = true;}
		}	catch (Exception excep){			
			throw excep;
		}
		return existe;
	}


	
	
	

	
	

}
