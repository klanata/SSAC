package com.core.service.negocio;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.data.entites.Servicio;
import com.core.data.entites.Filtro;
import com.core.data.persistencia.interfaces.locales.FiltroDAO;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.data.persistencia.DataService;


@Path("/filtros")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//FiltroEB!com.core.service.negocio.remote.FiltroEBR")
public class FiltroEB implements FiltroEBR{
	
	@EJB
	private FiltroDAO filtroDAO;
	
	@EJB
	private DataService dataService;
	
	@EJB
	private ServicioDAO servicioDAO;
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarFiltro(String descripcion)throws Exception {
				
		Filtro f = new Filtro();
		Long id;	
				
		f.setDescripcion(descripcion);			
		f.setBajaLogica(false);
				
		id = filtroDAO.insert(f);
		return id;											
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Filtro buscaFiltroPorDescripcion(String descripcion) throws Exception {
		Filtro f = new Filtro();
		f = filtroDAO.buscarFiltroPorDescripcion(descripcion);		
		return f;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Filtro buscaFiltroPorId(Long id) throws Exception {
		Filtro f = new Filtro();
		f = filtroDAO.buscarFiltroPorId(id);		
		return f;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Filtro> listaFiltros() throws Exception {
		List<Filtro> listFiltros = new ArrayList<Filtro>();
		listFiltros = filtroDAO.listarFiltros();		
		return listFiltros;
	}	
	
	
	public List<Filtro> listaFiltrosYoutube() throws Exception{
		List<Filtro> listaFiltrosYoutube = new ArrayList<Filtro>();
		listaFiltrosYoutube = filtroDAO.listarFiltrosYoutube();
		return listaFiltrosYoutube;
	}
	
	
	/*
	public void asignarServicioFiltro(String descripcion,String fuente) throws Exception{		
		try{
			Servicio servicio = new Servicio();
			if (servicioDAO.existeServicio(fuente)){				
				servicio = servicioDAO.buscarServicioPorFuente(fuente);
			}
			else
			{							
				String url = "www.youtube.com";				
				servicio.setFuente(fuente);
				servicio.setUrl(url);
				
				Long idServicio = servicioDAO.insert(servicio);
								
				System.out.println("probando agregar idServicio: " + idServicio );
			}				
			
			Long id = ingesarFiltro(descripcion);				
			Filtro filtro = filtroDAO.buscarFiltroPorId(id);
					
			Set<Filtro> filtros = servicio.getFiltros();
			filtros.add(filtro);						
			dataService.update(servicio);	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
			
		
	}
	*/
	
	public void asignarFiltroServicio(Long idFiltro,String fuente) throws Exception{
		
		try{
			Servicio servicio = new Servicio();
			if (servicioDAO.existeServicio(fuente)){				
				servicio = servicioDAO.buscarServicioPorFuente(fuente);
			}
			else
			{							
				String url = "www.youtube.com";				
				servicio.setFuente(fuente);
				servicio.setUrl(url);
				
				Long idServicio = servicioDAO.insert(servicio);
								
				System.out.println("probando agregar idServicio: " + idServicio );
			}				
										
			Filtro filtro = filtroDAO.buscarFiltroPorId(idFiltro);
					
			Set<Filtro> filtros = servicio.getFiltros();
			filtros.add(filtro);						
			dataService.update(servicio);	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
		
	}
	
	
	public List<Filtro> listaFiltrosNoAsignadosAYoutube() throws Exception{
		
		List<Filtro> listaFiltrosNoAsigYoutube = new ArrayList<Filtro>();			
		List<Filtro> listaAllFiltros = filtroDAO.listarFiltros();
		List<Filtro> listaYoutubeFiltros = filtroDAO.listarFiltrosYoutube();
		List<Long> listaId = new ArrayList<Long>();
		Filtro filtro;
		Filtro filtro2;
		Long id;
		Long id2;
		boolean esta;
		boolean baja;
		
		for (int i=0; i<=listaAllFiltros.size()-1; i++){
			filtro = listaAllFiltros.get(i);
			id = filtro.getId();
			esta = false;
			baja = false;
			for (int j=0; j<=listaYoutubeFiltros.size()-1; j++){
				filtro2 = listaYoutubeFiltros.get(j);
				baja = filtro2.isBajaLogica();
				id2 = filtro2.getId();
				if (id == id2){
					esta = true;					
				}
			}			
			if ((esta == false) && (baja == false)){
				listaId.add(id);				
			}			
		}
		
		Filtro filtroRes;
		Long idRes;
		for (int k=0; k<=listaId.size()-1; k++){
			idRes = listaId.get(k);
			filtroRes = filtroDAO.buscarFiltroPorId(idRes);
			listaFiltrosNoAsigYoutube.add(filtroRes);
		}		
		return listaFiltrosNoAsigYoutube;
	}
	
}
