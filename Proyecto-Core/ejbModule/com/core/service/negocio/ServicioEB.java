package com.core.service.negocio;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.ServicioEBR;
import com.core.data.persistencia.DataService;


@Path("/servicios")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//ServicioEB!com.core.service.negocio.remote.ServicioEBR")
public class ServicioEB implements ServicioEBR{
		
	
	@EJB
	private DataService dataService;
	
	@EJB
	private ServicioDAO servicioDAO;
	
	
	public Long ingresarServicio(String nombreServicio)throws Exception{
		
		Servicio servicio = new Servicio();		
		String url = "www.youtube.com";				
		servicio.setFuente(nombreServicio);
		servicio.setUrl(url);		
		Long id = servicioDAO.insert(servicio);
						
		//System.out.println("probando agregar idServicio: " + id );
		
		return id;
	}
					
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Servicio> listaServicios() throws Exception {
		List<Servicio> listServicios = new ArrayList<Servicio>();
		listServicios = servicioDAO.listarServicios();		
		return listServicios;
	}	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean ExisteServicio(String nombreServicio)throws Exception {
		if (servicioDAO.existeServicio(nombreServicio)){			
			return true;
		}
		else{
			return false;
		}		
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Servicio buscarServicioPorFuente(String nombreServicio) throws Exception {
		Servicio servicio = new Servicio();
		servicio = servicioDAO.buscarServicioPorFuente(nombreServicio);
		return servicio;
	}
	
	public Servicio buscarServicioPorId(Long idServicio) throws Exception {
		Servicio servicio = new Servicio();
		servicio = servicioDAO.buscarServicioPorId(idServicio);
		return servicio;		
	}
	
}
