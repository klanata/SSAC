package com.core.service.negocio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.AbstractEntity;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.PersonasDesaparecidasDAO;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;

import cross_cuting.enums.EstadoPersona;
@Path("/personasDesaparecidas") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//PersonasDesaparecidasEB!com.core.service.negocio.remote.PersonasDesaparecidasEBR")

public class PersonasDesaparecidasEB  extends AbstractEntity implements PersonasDesaparecidasEBR{
	
	@EJB
	private DataService dataService;
	
	@EJB 
	private PersonasDesaparecidasDAO personadesaparecidaDAO;

	
	public void crearReportePersonasDesaparecidas(String nombre, String apellido, String numeroContacto, EstadoPersona descripcion, Date fechNac,
			ImagenPersonaDesaparecida imagenPersonaDesaparecida){ 
		
		PersonasDesaparecidas perdes = new PersonasDesaparecidas();
		
		perdes.setNombre(nombre);
		perdes.setApellido(apellido);
		perdes.setNumeroContacto(numeroContacto);
		perdes.setDescripcion(descripcion);
		perdes.setFechNac(fechNac);
		perdes.setImagenPersonaDesaparecida(imagenPersonaDesaparecida);
		
		try {
			personadesaparecidaDAO.insert(perdes);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	/*public List<PersonasDesaparecidas> findAllPerson(){ 
		List<PersonasDesaparecidas> listaPer = new ArrayList<PersonasDesaparecidas>();
	//	listaPer = personadesaparecidaDAO.listarTodasLasPersonas();
		return listaPer;
		
	}*/
	//public List<PersonasDesaparecidas> findPersonasHalladas(){return null;}
	
//	public List<PersonasDesaparecidas> findPersonasNoHalladas(){return null;}
	
	public PersonasDesaparecidas buscarPersonaDesaparecida(String nomPersona, String apePersona) throws Exception{
			PersonasDesaparecidas perDesap = new PersonasDesaparecidas();
			
			perDesap = personadesaparecidaDAO.buscarPersonaDesaparecida(nomPersona, apePersona);
			return perDesap;
		}

		
}

	
	

