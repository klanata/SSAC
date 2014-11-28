package com.core.service.negocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.AbstractEntity;
import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.ImagenPersonaDesaparecidaDAO;
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
	
	@EJB 
	private ImagenPersonaDesaparecidaDAO imagenpersonaDAO;

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
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PersonasDesaparecidas> listarPersonas() throws Exception {
		List<PersonasDesaparecidas> listPersonas = new ArrayList<PersonasDesaparecidas>();
		listPersonas = personadesaparecidaDAO.listarTodasLasPersonas();
		return listPersonas;
	}	
	
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void agregarImagenAPersonaDesaparecida(String nomPer, String apePer, String nombImagen) throws Exception{
		
		PersonasDesaparecidas c = personadesaparecidaDAO.buscarPersonaDesaparecida(nomPer, apePer);
		
		ImagenPersonaDesaparecida imgPerDesap = new ImagenPersonaDesaparecida();
		imgPerDesap.setPath(nombImagen);
		imgPerDesap.setPersonasDesaparecidas(c);		
		imagenpersonaDAO.insert(imgPerDesap);
		ImagenPersonaDesaparecida imgPer = imagenpersonaDAO.buscarImgPersonaPorPath(nombImagen);
		Set<ImagenPersonaDesaparecida> imagenesPer = c.getImagenes();
		boolean esta = false;
		Long idImgPer;
		for (ImagenPersonaDesaparecida i : imagenesPer){
			idImgPer = i.getId();			
			if(idImgPer == imgPer.getId())
				esta = true;
		}
		if(!esta){		
			imagenesPer.add(imgPer);						
			dataService.update(c);						
			System.out.println("probando agregar imagen de nombre: " + nombImagen);			
		}
		else
		{
			System.out.println("La imagen ya estaba en la catastrofe.");
		}	
		
		
	}
		
}

	
	

