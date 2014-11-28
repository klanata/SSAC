package com.core.service.negocio;
import java.util.Date;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.AbstractEntity;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.PersonasDesaparecidasDAO;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;
import com.core.data.entites.ImagenPersonaDesaparecida;

import cross_cuting.enums.EstadoPersona;
@Path("/personasDesaparecidas") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//PersonasDesaparecidasEB!com.core.service.negocio.remote.PersonasDesaparecidasEBR")

public class PersonasDesaparecidasEB  implements PersonasDesaparecidasEBR{
	
	@EJB
	private DataService dataService;
	
	@EJB 
	private PersonasDesaparecidasDAO personadesaparecidaDAO;

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long crearReportePersonasDesaparecidas(String nombre, String apellido, String numeroContacto, EstadoPersona descripcion, Date fechNac, String foto,
			Set<ImagenPersonaDesaparecida>  imagen)throws Exception{ 
		
		PersonasDesaparecidas perdes = new PersonasDesaparecidas();
		Long id;
		perdes.setNombre(nombre);
		perdes.setApellido(apellido);
		perdes.setNumeroContacto(numeroContacto);
		perdes.setDescripcion(descripcion);
		perdes.setFechNac(fechNac);
		perdes.setFoto(foto);
		perdes.setImagenes(imagen);
		
		id = personadesaparecidaDAO.insert(perdes);
		return id;
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

	
	

