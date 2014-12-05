package com.core.data.persistencia;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.persistencia.interfaces.locales.PersonasDesaparecidasDAO;


@Stateless

@Local(PersonasDesaparecidasDAO.class)
public class PersonasDesaparecidasDAOImpl extends AbstractService implements PersonasDesaparecidasDAO{
	
private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {		
		return em;
	}
	
	@EJB
	private DataService dataService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(PersonasDesaparecidas personadesaparecida)  throws Exception{
		try {
			dataService.create(personadesaparecida);
			Long id = personadesaparecida.getId();
			return id;
			} 	
		catch (Exception excep){			
			throw excep;
		}	
	}
	////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existePersona(String nombrePersona, String apePersona){
		boolean existe;
		Query consulta = this.em.createNamedQuery("PersonasDesaparecidas.BuscarPer.Nombre.Apellido");
		consulta.setParameter("nombre", nombrePersona);	
		consulta.setParameter("apellido", apePersona);
	  	if (consulta.getResultList().isEmpty()){
	  		existe = false;
	  	} else {
	  		existe = true;
	  	}
	  	return existe;
	}
	
	
	public List<PersonasDesaparecidas> buscarPersonaPorCat(int idCat)throws Exception {	
		try{
               
		TypedQuery<PersonasDesaparecidas> consulta = this.em.createNamedQuery("PersonasDesaparecidas.BuscarPorCat.Catastrofe",PersonasDesaparecidas.class);
        consulta.setParameter("idCatastrofe", idCat);	
        List<PersonasDesaparecidas> lista = consulta.getResultList();
        return lista;
		}  catch (Exception excep){			
			throw excep;
		}	 
}

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PersonasDesaparecidas buscarPersonaDesaparecida(Long idCatastrofe, String nomPer, String apePer) {
		PersonasDesaparecidas perDesap = null;
		Query consulta = this.em.createNamedQuery("PersonasDesaparecidas.BuscarPorNombreyApellido.Nombre.Apellido.Cat");
		consulta.setParameter("catastrofe", idCatastrofe);
	  	consulta.setParameter("nombre", nomPer);
	  	consulta.setParameter("apellido", apePer);
	   	perDesap = (PersonasDesaparecidas) consulta.getResultList().get(0);
		return perDesap;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<PersonasDesaparecidas> listarTodasLasPersonas() throws Exception{
		
		try {
			TypedQuery<PersonasDesaparecidas> consulta = this.em.createNamedQuery("PersonasDesaparecidas.findAll",PersonasDesaparecidas.class);								
			List<PersonasDesaparecidas> personas = consulta.getResultList();			
		  	return personas;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	/*public Collection<PersonasDesaparecidas> listarPersonasHalladas(Long idPersona) throws Exception{
		Collection<PersonasDesaparecidas> listaPersonasHalladas = null;
		try{
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id",idPersona);
		listaPersonasHalladas  = dataService.findWithNamedQuery(PersonasDesaparecidas.class,"EstadoPersona.findHalladas", parameters);
			parameters.clear();
			
		}catch (Exception e){}
		return listaPersonasHalladas;
		
	}*/
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PersonasDesaparecidas buscarPersonaPorId(Long id) throws Exception{
		
		PersonasDesaparecidas persona;
		try {
			Query consulta = this.em.createNamedQuery("PersonasDesaparecidas.BuscarPersona.Id",PersonasDesaparecidas.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		persona = new PersonasDesaparecidas();
		  		persona.setId(new Long(0));		  		
		  	} else {		  		
		  		persona = (PersonasDesaparecidas) consulta.getResultList().get(0);
		  	}		  	
		  	return persona;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	
}
