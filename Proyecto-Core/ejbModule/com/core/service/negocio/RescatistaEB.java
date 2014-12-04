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
import com.core.data.entites.PedidoDeAyuda;
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
		    	   Long idCatastrofe = e.getPedidoAyuda().getId();
		    	   Catastrofe c = dataService.find(Catastrofe.class, idCatastrofe);
		    	   Long idPlanRiesgo = c.getPlanDeRiesgo().getId();
		    	   //obtengo planDeRiesgo
		    	   PlanDeRiesgo p = planDeRiesgoService.obtenerPlanDeRiesgoPorID(idPlanRiesgo);
		    	   
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
	public void asignarRescatistaCatastrofe(PedidoDeAyuda pedido) {
		
		try{
		
			Long id = new Long (pedido.getId());
			//obtengo objeto catastrofe
			PedidoDeAyuda pedidoGuardar = dataService.find(PedidoDeAyuda.class, id);
			EstadoRescatista estadoRescatista = new EstadoRescatista();
			estadoRescatista.setPendiente(true);
			estadoRescatista.setPedidoAyuda(pedidoGuardar);
			//FIXME: Stephy: victoria aca deje setado el nombre de la catastrofe ya que la tea no estaba pensado en todo caso 
			//me la tienen que pasar desde la catastrofe.
			estadoRescatista.setNombreTarea(pedidoGuardar.getDescripcion());
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
			String email, String password, Date fechaNac, String sexo, String celular) throws Exception {
			
		Rescatista r = new Rescatista();
		r.setNombre(nombre);
		r.setApellido(apellido);
		r.setCelular(celular);
		r.setEmail(email);
		r.setFechaNac(fechaNac);
		r.setPassword(password);
		r.setSexo(sexo);
		r.setBajaLogica(false);
		r.setNick(nick);
		r.setEstadoRescatista(null);
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


///////////////////////////////////
	@Override
	public void RealizadoPendiente(EstadoRescatista estadorescatista){
		
		
		 rescatistaService.pendienteRealizado(estadorescatista);
		
		
	}


	/****************************************************************************/
	public void modificarRescatista(String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, String celular) {
		if(!nick.isEmpty())
		{
			Rescatista u = rescatistaService.buscarUsuarioNick(nick);
			if(!password.isEmpty()) { u.setPassword(password);}
			if(!email.isEmpty()){ u.setEmail(email);}
			if(!nombre.isEmpty()){u.setNombre(nombre);}
			if(fechaNac != null){ u.setFechaNac(fechaNac);}
			if(!apellido.isEmpty()){u.setApellido(apellido);}
			if(!sexo.isEmpty()){u.setSexo(sexo);}
			u.setCelular(celular);
			
			dataService.update(u);
			
		}	
		
	}


	/****************************************************************************/
	public Boolean eliminarRescatista(String nick) {
		
		Rescatista r = obtenerRescatistaNik(nick);
		r.setBajaLogica(true);
		dataService.update(r);
		
		
		/** problemas al borrar
		Boolean eliminar =  false;
		Rescatista r = obtenerRescatistaNik(nick);
		
		r.getEstadoRescatista();
		//pregunto si tiene pendientes
		Collection<PlanesPendientesRescatistaDTO> pendientes = listarPendientesRescatistaPorCatastrofe(nick);
		if(pendientes == null)
		{
			//obtengo rescartista
			
			dataService.delete(r);
			eliminar = true;
		}
		else{
			
				//obtengo lista de sus pendientes
			Collection<EstadoRescatista> listaPendientesUsuarioBorrar =  r.getEstadoRescatista();
			Rescatista rescatistaAsignar = rescatistaService.obtenerRescatistaConMenosPendientes();
			//obtengo su colleccion de pendientes
			
			Collection<EstadoRescatista> lista = rescatistaAsignar.getEstadoRescatista();
			
			Iterator<EstadoRescatista> it = listaPendientesUsuarioBorrar.iterator();
			
			while(it.hasNext())
			{
				
				EstadoRescatista e = it.next();
				lista.add(e);
				
			}
			
			dataService.update(rescatistaAsignar);
			r.setBajaLogica(true);
			dataService.update(r);
			
			
		}		
		
		*/
		return true;
	}


	/****************************************************************************/
	public Collection<Rescatista> listarTodosLosRescatistasActivos() {
		
		
		Collection<Rescatista> listar = rescatistaService.listarRescatistas();
		
		return listar;
	}


	/****************************************************************************/
	public Rescatista obtenerRescatistaNik(String nick) {
		
		Rescatista r = rescatistaService.buscarUsuarioNick(nick);
		
		return r;
	}


	@Override
	public Rescatista obtenerRescatistaID(Long id) {
		
		Rescatista r =  dataService.find(Rescatista.class, id);
		
		return r;
	}

	
	

}
