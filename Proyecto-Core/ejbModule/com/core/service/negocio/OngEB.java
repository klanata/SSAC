package com.core.service.negocio;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.Ong;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.OngDAO;
import com.core.service.negocio.remote.OngEBR;


@Path("/ong")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR")

public class OngEB implements OngEBR{
	
	@EJB
	private OngDAO ongService;
	@EJB 
	private DataService dataService;
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarOng(String nombre, String direccion, String descripcion,
			String email, BigDecimal telefono, String citioWeb)
			throws Exception {
		
		Ong o= new Ong();
		o.setCitioWeb(citioWeb);
		o.setDescripcion(descripcion);
		o.setDireccion(direccion);
		o.setEmail(email);
		o.setTelefono(telefono);
		o.setNombre(nombre);
		
		Long id = ongService.insert(o);
		return id;
	}

	@Override
	public List<Ong> listarTodasLasOng() {
		List<Ong> lista = null;
		
		lista = dataService.findAll(Ong.class);
		
		return lista;
	}

	
	
}
