package com.core.service.negocio;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.service.negocio.remote.CatastrofeEBR;


@Path("/catastrofes")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR")
public class CatastrofeEB implements CatastrofeEBR{
	
	@EJB
	private CatastrofeDAO catastrofeDAO;
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarCatastrofe(String nombreEvento, String descripcion, String logo, BigDecimal coordenadasX, 
		BigDecimal coordenadasY, Boolean activa, Boolean prioridad, Collection<Servicio> servicios, Collection<Ong> ongs,
		PlanDeRiesgo planDeRiesgo)throws Exception {
				
		Catastrofe c = new Catastrofe();
		Long id;	
		
		c.setNombreEvento(nombreEvento);
		c.setDescripcion(descripcion);
		c.setLogo(logo);
		c.setCoordenadasX(coordenadasX);
		c.setCoordenadasY(coordenadasY);
		c.setActiva(activa);
		c.setPrioridad(prioridad);
		c.setServicios(servicios);
		c.setOngs(ongs);
		c.setPlanDeRiesgo(planDeRiesgo);
				
		id = catastrofeDAO.insert(c);
		return id;										
	
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Catastrofe buscaCatastrofe(String nombreEvento) throws Exception {
		Catastrofe c = new Catastrofe();
		c = catastrofeDAO.buscarCatastrofe(nombreEvento);
		return c;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Catastrofe> listaCatastrofes() throws Exception {
		List<Catastrofe> listCatastrofes = new ArrayList<Catastrofe>();
		listCatastrofes = catastrofeDAO.listarCatastrofes();
		return listCatastrofes;
	}
	

}
