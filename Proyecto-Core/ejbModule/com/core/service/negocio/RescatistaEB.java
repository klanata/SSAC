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
		
		//
		Rescatista r = rescatistaService.buscarUsuarioNick(nick);
		
		
		Collection<PlanesPendientesRescatistaDTO> listaDTO =new  ArrayList<PlanesPendientesRescatistaDTO>(0);
		
		try{
				Long idRescatista = r.getId();
		       Collection<EstadoRescatista> listaEstado = rescatistaService.listarPendientesRescatistaPorID(idRescatista);
		       
		        
		       if(listaEstado != null){
		    	   
		    
		       
		       
		       Iterator<EstadoRescatista> it = listaEstado.iterator();
		       
				       while(it.hasNext())
				       {
				    	   EstadoRescatista e = it.next();
				    	   Long idpedidoAyuda = e.getIdPedidoAyuda();
				    	   PedidoDeAyuda pedido = dataService.find(PedidoDeAyuda.class,idpedidoAyuda);
				    	   //obtengo la catastrofeç
				    	   PlanDeRiesgo p = pedido.getCatastrofe().getPlanDeRiesgo();
				    	   
				    	   //obtengo planDeRiesgo
				    	   		    	   
				    	   PlanesPendientesRescatistaDTO planesDTO = new PlanesPendientesRescatistaDTO();
				    	   planesDTO.setUrlArchivo(p.getRutaArchivo());
				    	   planesDTO.setNombreTarea(e.getNombreTarea());
				    	   planesDTO.setIdPedidoDeAyuda(idpedidoAyuda);
				    	   planesDTO.setCoordenadaX(pedido.getCoordenadasX());
				    	   planesDTO.setCoordenaday(pedido.getCoordenadasY());
				    	   planesDTO.setDescripcion(pedido.getDescripcion());
				    	   planesDTO.setEstadoTarea(e.getPendiente());
				    	   planesDTO.setIdEstadoRescatista(e.getId());
				    	   
				    	   listaDTO.add(planesDTO);
				    	   
				    	   
				       }
		       }
		       
		}catch (Exception e )
		{
			e.printStackTrace();
		}
		
		return listaDTO;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	@Override
	//FIXME: Vale tenes que usar esto para pedido de ayuda cuando lo crees
	public void asignarRescatistaCatastrofe(long pedido) {
		
		Rescatista rescatista = null;
		try{
			
			
			 rescatista = rescatistaService.obtenerRescatistaConMenosPendientes();
		}catch(Exception e){
			
			System.out.println("Excepcion al obtener rescatista con menos pendientes: " + e.getMessage());      
		};
		
		try{
		
			
			//obtengo objeto catastrofe
			PedidoDeAyuda pedidoGuardar = dataService.find(PedidoDeAyuda.class, pedido);
			
			EstadoRescatista estadoRescatista = new EstadoRescatista();
			estadoRescatista.setPendiente(true);
			estadoRescatista.setIdPedidoAyuda(pedido);
			
			estadoRescatista.setNombreTarea(pedidoGuardar.getDescripcion());
			//busco el rescatista para asignar
			
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
			String email, String password, Date fechaNac, String sexo, String celular,String imagen) throws Exception {
			
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
		r.setImagen(imagen);
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
	public void modificarRescatista(String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, String celular,String imagen) {
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
			u.setImagen(imagen);
			
			dataService.update(u);
			
		}	
		
	}


	/****************************************************************************/
	public Boolean eliminarRescatista(String nick) {
		
		Rescatista r = obtenerRescatistaNik(nick);
		r.setBajaLogica(true);
		
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


	@Override
	public void asignarRescatistaPedidoDeAyuda(Long idRescatista,
			Long idPedidoAyuda) {
		
		Rescatista r = dataService.find(Rescatista.class, idRescatista);
		PedidoDeAyuda p = dataService.find(PedidoDeAyuda.class, idPedidoAyuda);
		
		EstadoRescatista estadoRescatista = new EstadoRescatista();
		estadoRescatista.setPendiente(true);
		estadoRescatista.setIdPedidoAyuda(idPedidoAyuda);
	
		estadoRescatista.setNombreTarea(p.getDescripcion());
		estadoRescatista.setRescatista(r);
		try {
			dataService.create(estadoRescatista);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//obtenemos la lista de pendientes del rescatista 
		Collection<EstadoRescatista> listaEstado= r.getEstadoRescatista();
		listaEstado.add(estadoRescatista);
		dataService.update(r);
		
		
	}

	
	

}
