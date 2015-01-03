package com.core.service.negocio;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Filtro;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Servicio;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.ImagenCatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;
import com.core.data.persistencia.interfaces.locales.FiltroDAO;
import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.data.persistencia.DataService;


@Path("/catastrofes")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR")
public class CatastrofeEB implements CatastrofeEBR{
	
	@EJB
	private CatastrofeDAO catastrofeDAO;
	
	@EJB
	private DataService dataService;
	
	@EJB
	private ImagenCatastrofeDAO imagenCatastrofeDAO;
	
	@EJB
	private PlanDeRiesgoDAO planDeRiesgoDAO;
	
	@EJB
	private FiltroDAO filtroDAO;
	
	@EJB
	private ServicioDAO servicioDAO;

	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX, 
		double coordenadasY, Boolean activa, Boolean prioridad, String css, Set<ImagenCatastrofe> imagenes, 
		Set<Filtro> filtros, Set<Ong> ongs,	PlanDeRiesgo planDeRiesgo,String poligono)throws Exception {
				
		Catastrofe c = new Catastrofe();
		Long id;	
		
		c.setNombreEvento(nombreEvento);
		c.setDescripcion(descripcion);
		c.setLogo(logo);
		c.setCoordenadasX(coordenadasX);
		c.setCoordenadasY(coordenadasY);
		c.setActiva(activa);
		c.setPrioridad(prioridad);
		c.setCss(css);
		c.setImagenes(imagenes);		
		c.setFiltrosCatastrofes(filtros);
		c.setOngs(ongs);
		c.setPlanDeRiesgo(planDeRiesgo);
		c.setBajaLogica(false);
		c.setPoligono(poligono);
				
		id = catastrofeDAO.insert(c);
		return id;											
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Catastrofe buscaCatastrofePorNombre(String nombreEvento) throws Exception {
		Catastrofe c = new Catastrofe();
		c = catastrofeDAO.buscarCatastrofePorNombre(nombreEvento);
		return c;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Catastrofe buscaCatastrofePorId(Long id) throws Exception {
		Catastrofe c = new Catastrofe();
		c = catastrofeDAO.buscarCatastrofePorId(id);
		return c;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Catastrofe> listaCatastrofes() throws Exception {
		List<Catastrofe> listCatastrofes = new ArrayList<Catastrofe>();
		listCatastrofes = catastrofeDAO.listarCatastrofes();
		return listCatastrofes;
	}	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void agregarOngALaCatastrofe(Long idCatastrofe, Long idOng) throws Exception{			
		Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);		
		Ong ong = dataService.find(Ong.class, idOng) ;		
		Set<Ong> ongs = c.getOngs();		
		boolean esta = false;		
		Long idO;
		for (Ong o : ongs){
			idO = o.getId();			
			if(idOng == idO)
				esta = true;
		}				
		if(!esta){						
			ongs.add(ong);					
			dataService.update(c);						
			System.out.println("probando agregar ONG de nombre: " + ong.getNombre());			
		}
		else
		{
			System.out.println("La ONG ya estaba en la catastrofe.");
		}							
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void agregarImagenALaCatastrofe(Long idCatastrofe, String nombImagen) throws Exception{
		
		Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);	
		
		ImagenCatastrofe imgCatastrofe = new ImagenCatastrofe();
		imgCatastrofe.setPath(nombImagen);
		imgCatastrofe.setCatastrofe(c);		
		imagenCatastrofeDAO.insert(imgCatastrofe);		
		
		ImagenCatastrofe imgCat = imagenCatastrofeDAO.buscarImgCatastrofePorPath(nombImagen);
		Set<ImagenCatastrofe> imagenesCat = c.getImagenes();
		boolean esta = false;
		Long idImgCat;
		for (ImagenCatastrofe i : imagenesCat){
			idImgCat = i.getId();			
			if(idImgCat == imgCat.getId())
				esta = true;
		}
		if(!esta){		
			imagenesCat.add(imgCat);						
			dataService.update(c);						
			//System.out.println("probando agregar imagen de nombre: " + nombImagen);			
		}
		else
		{
			//System.out.println("La imagen ya estaba en la catastrofe.");
		}	
		
		
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Collection<Ong> listaOngDeCatastrofe(Long id) {		
		Collection<Ong> lista = null;		
		try {
			Catastrofe c = this.buscaCatastrofePorId(id);			
			lista = c.getOngs();
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		return lista;
	}


	@Override
	public void eliminarOngDeCatastrofe(Long idCatastrofe, Long idOng) {
				
		Catastrofe c= dataService.find(Catastrofe.class, idCatastrofe);
		Ong o = dataService.find(Ong.class, idOng);
		
		Set<Ong> lista = c.getOngs();
		lista.remove(o);
		
		c.setOngs(lista);
		dataService.update(c);
						
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Collection<ImagenCatastrofe> listaImagenesDeCatastrofe(Long id) throws Exception  {		
		Collection<ImagenCatastrofe> lista = null;
		try {
			Catastrofe c = this.buscaCatastrofePorId(id);			
			lista = c.getImagenes();
		} catch (Exception e) {			
			e.printStackTrace();
		}			
		return lista;		
	}
	
	
	public void eliminarImgDeCatastrofe(Long idCatastrofe, Long idImg) throws Exception {
		Catastrofe c= dataService.find(Catastrofe.class, idCatastrofe);
		ImagenCatastrofe imgCat = imagenCatastrofeDAO.buscarImgCatastrofePorId(idImg);
		
		Set<ImagenCatastrofe> lista = c.getImagenes();
		lista.remove(imgCat);
		
		c.setImagenes(lista);
		dataService.update(c);
		
		dataService.delete(imgCat);				
		
	}
	
	public void agregarPlanDeRiesgoALaCatastrofe(Long idCatastrofe, String nombArchivo) throws Exception {
		
		try {
			Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);	
			
			PlanDeRiesgo planRiesgo = new PlanDeRiesgo();
			planRiesgo.setRutaArchivo(nombArchivo);
			planRiesgo.setCatastrofe(c);
						
			Long idPlan = planDeRiesgoDAO.crearPlanDeRiesgo(planRiesgo);
			Long id;
						
			PlanDeRiesgo planCatastrofe = c.getPlanDeRiesgo();
			if (planCatastrofe != null){
				id = planCatastrofe.getId();
				if (idPlan == id){
					System.out.println("Nunca deberia llegar aca. ");			
				}
				else {			
					c.setPlanDeRiesgo(planRiesgo);						
					dataService.update(c);			
				}	
			}
			else{
				c.setPlanDeRiesgo(planRiesgo);						
				dataService.update(c);					
			}																									
		}catch (Exception e) {			
			e.printStackTrace();
		}									
	}
	
	public void eliminarPlanDeRiesgoCatastrofe(Long idCatastrofe, Long idPlan) throws Exception{
		Catastrofe c= dataService.find(Catastrofe.class, idCatastrofe);
		PlanDeRiesgo plan= planDeRiesgoDAO.find(PlanDeRiesgo.class, idPlan);
		
		c.setPlanDeRiesgo(null);
		dataService.update(c);
		
		dataService.delete(plan);	
		
	}	
	
	public void agregarCSSALaCatastrofe(Long idCatastrofe, String css) throws Exception{
		try {
			Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);							
			c.setCss(css);
			dataService.update(c);																										
		}catch (Exception e) {			
			e.printStackTrace();
		}				
	}
	
	public void eliminarCSSDeLaCatastrofe(Long idCatastrofe) throws Exception{				
		try {
			Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);							
			String css = c.getCss();
			if ((css != null) && (css != "")){
				c.setCss(null);
				dataService.update(c);				
			}																														
		}catch (Exception e) {			
			e.printStackTrace();
		}																																
	}
	
	public void asignarFiltroYoutubeALaCatastrofe(Long idCatastrofe, Long idFiltro) throws Exception{
		try {
			
			Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);
			Filtro filtro = dataService.find(Filtro.class, idFiltro);
			
			Set<Filtro> filtros = c.getFiltrosCatastrofes();
			filtros.add(filtro);
			dataService.update(filtro);
			
			//System.out.println("llega a asignar filtro youtube en catastrofe." + idFiltro);
			
		}catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public List<Filtro> filtrosAsingadosACatastrofe(Long idCatastrofe, String fuente) throws Exception{
		
		List<Filtro> res = new ArrayList<Filtro>();
		Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);	
		Set<Filtro> filtros = c.getFiltrosCatastrofes();
		Servicio servicio = servicioDAO.buscarServicioPorFuente(fuente);
		Long idServcio = servicio.getId();
		Set<Servicio> servicosF = new HashSet<Servicio>();
		Long idS;
		int i = 0;
		
		for (Filtro f : filtros){
			servicosF = f.getServicios();
			for (Servicio s : servicosF){
				idS = s.getId();			
				if(idServcio == idS){
					res.add(i, f);
					i = i + 1;
				}					
			}			 
		}								
		return res;		
		
	}
	
	public List<String> BusquedaAsingadasACatastrofe(Long idCatastrofe, String fuente) throws Exception{
		
		List<String> res = new ArrayList<String>();
		Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);	
		Set<Filtro> filtros = c.getFiltrosCatastrofes();
		Servicio servicio = servicioDAO.buscarServicioPorFuente(fuente);
		Long idServcio = servicio.getId();
		Set<Servicio> servicosF = new HashSet<Servicio>();
		Long idS;
		String descripcionF;
		
		for (Filtro f : filtros){
			servicosF = f.getServicios();
			descripcionF = f.getDescripcion();
			for (Servicio s : servicosF){
				idS = s.getId();			
				if(idServcio == idS){
					res.add(descripcionF);						
				}					
			}			
		}								
		return res;
		
	}
	
	
}
