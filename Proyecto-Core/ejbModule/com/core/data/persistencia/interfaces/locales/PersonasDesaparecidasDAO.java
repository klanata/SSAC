package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import java.util.List;

import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.JPAService;

import cross_cuting.enums.EstadoPersona;

public interface PersonasDesaparecidasDAO extends JPAService {
	
	public void insert(PersonasDesaparecidas personadesaparecida)  throws Exception;
	
	public boolean existePersona(String nombrePersona, String apePersona);
	
	public PersonasDesaparecidas buscarPersonaDesaparecida(String nomPer, String apePer);

	public List<PersonasDesaparecidas> listarTodasLasPersonas() throws Exception;
	
	public Collection<PersonasDesaparecidas> listarPersonasHalladas(Long idPersona) throws Exception;
	
	public Collection<PersonasDesaparecidas> listarPersonasDesaparecidas(Long idPersona) throws Exception;
}
