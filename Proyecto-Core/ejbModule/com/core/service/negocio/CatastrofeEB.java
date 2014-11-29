package com.core.service.negocio;

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
import com.core.data.entites.Servicio;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.ImagenCatastrofeDAO;
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
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX, 
		double coordenadasY, Boolean activa, Boolean prioridad, Set<ImagenCatastrofe> imagenes, 
		Set<Servicio> servicios, Set<Ong> ongs,	PlanDeRiesgo planDeRiesgo)throws Exception {
				
		Catastrofe c = new Catastrofe();
		Long id;	
		
		c.setNombreEvento(nombreEvento);
		c.setDescripcion(descripcion);
		c.setLogo(logo);
		c.setCoordenadasX(coordenadasX);
		c.setCoordenadasY(coordenadasY);
		c.setActiva(activa);
		c.setPrioridad(prioridad);
		c.setImagenes(imagenes);
		c.setServicios(servicios);
		c.setOngs(ongs);
		c.setPlanDeRiesgo(planDeRiesgo);
				
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
			
		System.out.println("La imagen de la catastrofe: " + nombImagen.toString());
		
		
		
	}

}
