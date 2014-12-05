package com.core.service.negocio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.ImagenPersonaDesaparecidaDAO;
import com.core.data.persistencia.interfaces.locales.PersonasDesaparecidasDAO;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;

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
	public Long crearReportePersonasDesaparecidas(Long idCatastrofe, String nombre, String apellido, String numeroContacto, String descripcion, Date fechNac,
			Set<ImagenPersonaDesaparecida> imagenes, boolean hallada)throws Exception{ 
	System.out.println("aca estoyyyyyy");
		PersonasDesaparecidas perdes = new PersonasDesaparecidas();
		Long id;
		Catastrofe catastrofe = dataService.find(Catastrofe.class, idCatastrofe);
		perdes.setCatastrofe(catastrofe);
		perdes.setNombre(nombre);
		perdes.setApellido(apellido);
		perdes.setNumeroContacto(numeroContacto);
		perdes.setDescripcion(descripcion);
		perdes.setFechNac(fechNac);
		perdes.setImagenes(imagenes);
		perdes.setHallada(hallada);
		
		id = personadesaparecidaDAO.insert(perdes);
		return id;
	}
	
	
	public PersonasDesaparecidas buscarPersonaDesaparecida(Long idCatastrofe, String nomPersona, String apePersona) throws Exception{
			PersonasDesaparecidas perDesap = new PersonasDesaparecidas();
			perDesap = personadesaparecidaDAO.buscarPersonaDesaparecida(idCatastrofe, nomPersona, apePersona);
			return perDesap;
		}
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PersonasDesaparecidas> listarPersonas() throws Exception {
		List<PersonasDesaparecidas> listPersonas = new ArrayList<PersonasDesaparecidas>();
		listPersonas = personadesaparecidaDAO.listarTodasLasPersonas();
		return listPersonas;
	}	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public PersonasDesaparecidas buscaPersonaPorId(Long id) throws Exception{
		
		PersonasDesaparecidas c = new PersonasDesaparecidas();
		c = personadesaparecidaDAO.buscarPersonaPorId(id);
		return c;
	}
	////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Collection<ImagenPersonaDesaparecida> listaImagenesDePersona(Long id) throws Exception{
		Collection<ImagenPersonaDesaparecida> lista = null;
		try {
			PersonasDesaparecidas c = this.buscaPersonaPorId(id);	
			lista = c.getImagenes();
		} catch (Exception e) {			
			e.printStackTrace();
		}			
		return lista;		
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void agregarImagenAPersonaDesaparecida(Long idPersona, String nombImagen) throws Exception{
		
		PersonasDesaparecidas c = personadesaparecidaDAO.buscarPersonaPorId(idPersona);
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
			System.out.println("La imagen.");
		}	
		
		
	}
		
}

	
	

