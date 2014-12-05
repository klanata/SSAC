package com.core.data.persistencia.interfaces.locales;
import java.util.List;
import javax.ejb.Local;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.JPAService;

@Local
public interface PersonasDesaparecidasDAO extends JPAService {
	
	public Long insert(PersonasDesaparecidas personadesaparecida)  throws Exception;
	
	public boolean existePersona(String nombrePersona, String apePersona);
	
	public List<PersonasDesaparecidas> buscarPersonaPorCat(int idCat)throws Exception;
	
	public PersonasDesaparecidas buscarPersonaDesaparecida(Long idCatastrofe, String nomPer, String apePer);
	
	public PersonasDesaparecidas buscarPersonaPorId(Long id) throws Exception;

	public List<PersonasDesaparecidas> listarTodasLasPersonas() throws Exception;
	
	
}
