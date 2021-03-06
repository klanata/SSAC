package com.core.service.negocio;

import java.util.Collection;
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
import com.core.data.entites.FiltroServicio;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Servicio;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.ImagenCatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;
import com.core.data.persistencia.interfaces.locales.FiltroDAO;
import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.data.persistencia.interfaces.locales.OngDAO;
import com.core.data.persistencia.interfaces.locales.FiltroServicioDAO;
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
	
	@EJB
	private OngDAO ongDAO;
	
	@EJB
	private FiltroServicioDAO filtroServicioDAO;

	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingresarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX, 
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
		//c.setFiltrosCatastrofes(filtros);
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
			//System.out.println("probando agregar ONG de nombre: " + ong.getNombre());			
		}
		else
		{
			//System.out.println("La ONG ya estaba en la catastrofe.");
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
	
	/*
	public void asignarFiltroALaCatastrofe(Long idCatastrofe, Long idFiltro) throws Exception{
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
	*/
	
	public List<Double> ListarCoordenasCatastrofe(Long idCatastrofe) throws Exception{
		
		List<Double> list = new ArrayList<Double>();
		Catastrofe catastrofe = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);	
		String poligono;
		poligono = catastrofe.getPoligono();
		int index = 0;
		char fin = ']';
		boolean esNegativo = false; 
		char digChar;
		double coordenada = 0;			
		
		do{
			//Recorro hasta ":":
			coordenada = 0;	
			while(poligono.charAt(index) != ':'){
				index ++;														
			}
			index ++;
			if (poligono.charAt(index) == '-'){
				esNegativo = true;	
				index ++;
			}
			else {
				esNegativo = false;
			}			
			while(poligono.charAt(index) != '.'){
				digChar = poligono.charAt(index);	
				int dig = digChar - '0';
				coordenada = coordenada*10 + dig;
				index ++;																	
			}	
			index ++;
			int cont = 1;
			double digDespDeLaComa = 0;						
			while((poligono.charAt(index) >= '0') && (poligono.charAt(index) <= '9')){
				digChar = poligono.charAt(index);	
				int dig = digChar - '0';
				double acum;
				double multi = 1;
				for(int x = 1; x <= cont; x++) {
					multi = 0.1 * multi;								
				}
				acum = dig * multi;
				cont  ++;
				index ++;
				digDespDeLaComa = digDespDeLaComa + acum;							
			}	
			coordenada = coordenada + digDespDeLaComa;
			if (esNegativo) {
				coordenada = coordenada * (-1);
			}		
			
			index ++;
			list.add(coordenada);																
			//System.out.println("valor de coordenada: " + coordenada);								
			
		}while( poligono.charAt(index) != fin );		
		
		return list;
	}
	
	
	public List<Ong> listaOngsNoAsignadosALaCatastrofe(Long idCatastrofe) throws Exception{
		
		List<Ong> listaOngNoAsigCatastrofe = new ArrayList<Ong>();			
		List<Ong> listaAllOngs = ongDAO.listarONGS();
		Catastrofe catastrofe = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);
		Set<Ong> listaOngsCatastrofe = catastrofe.getOngs();
		List<Long> listaId = new ArrayList<Long>();
		Ong ong;		
		Long id;
		Long id2;
		boolean esta;		
		
		for (int i=0; i<=listaAllOngs.size()-1; i++){
			ong = listaAllOngs.get(i);
			id = ong.getId();
			esta = false;			
			for (Ong ongCatastrofe: listaOngsCatastrofe){
				id2 = ongCatastrofe.getId();
				if (id == id2){
					esta = true;					
				}
			}			
			if (esta == false){
				listaId.add(id);				
			}																						
		}
		
		Ong ongRes;
		Long idRes;
		for (int k=0; k<=listaId.size()-1; k++){
			idRes = listaId.get(k);			
			ongRes = ongDAO.buscarOngPorID(idRes);
			listaOngNoAsigCatastrofe.add(ongRes);			
		}
				
		return listaOngNoAsigCatastrofe;
	}
	
	
	public void asignarFiltroServicioALaCatastrofe(Long idCatastrofe, Long idFiltroServicio) throws Exception{		
		try {			
			Catastrofe c = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);
			FiltroServicio filtroServicio = filtroServicioDAO.buscarFiltroServicioPorId(idFiltroServicio);
			
			filtroServicio.setCatastrofe(c);
			Set<FiltroServicio> filtroServicios = c.getFiltroServicio();
	
			filtroServicios.add(filtroServicio);
			dataService.update(filtroServicio);															
		}catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	
	public void eliminarFiltroServicioDeCatastrofe(Long idFiltroServicio) throws Exception{		
		try {						
			FiltroServicio filtroServicio = filtroServicioDAO.buscarFiltroServicioPorId(idFiltroServicio);	
			Catastrofe c= filtroServicio.getCatastrofe();
												
			Set<FiltroServicio> lista = c.getFiltroServicio();
			lista.remove(filtroServicio);			
			c.setFiltroServicio(lista);
			dataService.update(c);
			
			filtroServicio.setCatastrofe(null);							
			dataService.update(filtroServicio);
			
		}catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	public List<String> listarFiltrosDeCatastrofe(long idCatastrofe, String fuente) throws Exception{	
		
		List<String> listaFiltros = new ArrayList<String>();
		List<FiltroServicio> allFiltroServicio = filtroServicioDAO.listarFiltroServicios();
		Servicio servicioFuente = servicioDAO.buscarServicioPorFuente(fuente) ;
		
		FiltroServicio filtroServicio;
		Catastrofe catastrofe;
		Long id;
		Boolean bajaLogica;
		String filtroDescripcion;
		Long idFiltro;
		Filtro filtro;
		Servicio servicio;
		Long idServicio;
						
		for (int i=0; i<=allFiltroServicio.size()-1; i++){
			filtroServicio = allFiltroServicio.get(i);			
			bajaLogica = filtroServicio.isBajaLogica();
			catastrofe = filtroServicio.getCatastrofe();
			idServicio = filtroServicio.getIdServicio();
			servicio = servicioDAO.buscarServicioPorId(idServicio);					
			
			if ((catastrofe != null) && (bajaLogica == false) && (servicio == servicioFuente)) {					
				id = catastrofe.getId();
				//System.out.println("catastrofe Filtros servicios id: " + id);
				if (id == idCatastrofe){	
					idFiltro = filtroServicio.getIdFiltro();
					filtro = filtroDAO.buscarFiltroPorId(idFiltro);
					filtroDescripcion = filtro.getDescripcion();
					listaFiltros.add(filtroDescripcion);
				}											
											
			}						
		}					
		return listaFiltros;		
	}
	
	
	public void EliminarCatastrofe(Long idCatastrofe) throws Exception{
		Catastrofe catastrofe = catastrofeDAO.buscarCatastrofePorId(idCatastrofe);
		catastrofe.setBajaLogica(true);		
		dataService.update(catastrofe);
	}
	
	public void bajaFiltroServicioDeCatastrofe(Long idFiltroServicio) throws Exception{
		try {						
			FiltroServicio filtroServicio = filtroServicioDAO.buscarFiltroServicioPorId(idFiltroServicio);	
			filtroServicio.setBajaLogica(true);		
		}catch (Exception e) {			
			e.printStackTrace();
		}			
	}
	
	
	
}
