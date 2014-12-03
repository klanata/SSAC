package com.core.service.negocio;

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
			String email, String telefono, String citioWeb)
			throws Exception {
		
		Ong o= new Ong();
		o.setCitioWeb(citioWeb);
		o.setDescripcion(descripcion);
		o.setDireccion(direccion);
		o.setEmail(email);
		o.setTelefono(telefono);
		o.setNombre(nombre);
		o.setBajaLogica(false);
		Long id = ongService.insert(o);
		return id;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public List<Ong> listarTodasLasOng() {
		List<Ong> lista = null;
		
		lista = dataService.findAll(Ong.class);
		
		return lista;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	public boolean existeOng_EB(String nombre) {
		
		boolean existe= false;
		
		if(!nombre.isEmpty())
		{
			existe = ongService.existeOng(nombre);
			
		}
		
		return existe;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	public Ong buscarOngPorID_EB(Long id) {
		
		Ong  o = null;
		
		o = ongService.buscarOngPorID(id);
		
		
		return o;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	public Ong buscarOngPorNick_EB(String nombreOng) {
		
		Ong o = null;
		
		o= ongService.buscarOngPorNick(nombreOng);
		
		return o;
	}

	@Override
	public void modificarOng(String nombre, String direccion,
			String descripcion, String email, String telefono, String citioWeb) {
		
		//obtengo la ong
		Ong o =ongService.buscarOngPorNick(nombre);
		o.setCitioWeb(citioWeb);
		o.setDescripcion(descripcion);
		o.setDireccion(direccion);
		o.setEmail(email);
		o.setTelefono(telefono);
		//o.setNombre(nombre); politicas de la empresa el nombre no se modifica
		dataService.update(o);
		
		
		
		
		
	}

	@Override
	public void EliminarONG(Long id) {
		
		//buscamos ong para darle baja logica
		Ong o =dataService.find(Ong.class, id);
		o.setBajaLogica(true);
		dataService.update(o);
		
		
	}

	
	
}
