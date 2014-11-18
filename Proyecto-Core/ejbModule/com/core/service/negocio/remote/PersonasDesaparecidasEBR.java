package com.core.service.negocio.remote;
import java.util.Date;
import javax.ejb.Local;



import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;

import cross_cuting.enums.EstadoPersona;
@Local

public interface PersonasDesaparecidasEBR {
	
	public void crearReportePersonasDesaparecidas(String nombre, String apellido,
			String numeroContacto, EstadoPersona descripcion, Date fechNac,
			ImagenPersonaDesaparecida imagenPersonaDesaparecida);
	//public List<PersonasDesaparecidas> findAllPerson();
	//public List<PersonasDesaparecidas> findPersonasHalladas();
	//public List<PersonasDesaparecidas> findPersonasNoHalladas();
	public PersonasDesaparecidas buscarPersonaDesaparecida(String nomPersona, String apePersona) throws Exception;

}
