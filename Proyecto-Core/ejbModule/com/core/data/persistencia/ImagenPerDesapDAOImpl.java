package com.core.data.persistencia;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import com.core.data.persistencia.interfaces.locales.ImagenCatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.ImagenPersonaDesaparecidaDAO;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.entites.ImagenPersonaDesaparecida;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImagenPerDesapDAOImpl extends AbstractService implements ImagenPersonaDesaparecidaDAO{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(ImagenPersonaDesaparecida imgPersona)throws Exception {	
		Long id;
		String nombImg = imgPersona.getPath();
		try {
			if (this.existePathImagPersona(nombImg)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(imgPersona);					
				TypedQuery<Long> consulta = this.em.createNamedQuery("ImagenPersonaDesaparecida.BuscarImgPersonaId.PathImg",Long.class);				
				consulta.setParameter("pathImg", nombImg);				
				List<Long> imgPersonaId = consulta.getResultList();
				if (imgPersonaId.isEmpty()) {
					id = (long) 0;	
			  	} else {
			  		id = imgPersonaId.get(0);
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existePathImagPersona(String pathImg) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("ImagenPersonaDesaparecida.BuscarImgPersonaId.PathImg");
			consulta.setParameter("pathImg", pathImg);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ImagenPersonaDesaparecida buscarImgPersonaPorPath(String path) throws Exception {
	  	
		ImagenPersonaDesaparecida imgPersona;
		try {
			Query consulta = this.em.createNamedQuery("ImagenPersonaDesaparecida.BuscarImgPersona.PathImg",ImagenPersonaDesaparecida.class);			
		  	consulta.setParameter("path", path);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		imgPersona = new ImagenPersonaDesaparecida();
		  		imgPersona.setId(new Long(0));		  		
		  	} else {		  		
		  		imgPersona = (ImagenPersonaDesaparecida) consulta.getResultList().get(0);
		  	}		  	
		  	return imgPersona;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ImagenPersonaDesaparecida buscarImgPersonaPorId(Long id) throws Exception {
	  	
		ImagenPersonaDesaparecida imgCatastrofe;
		try {
			Query consulta = this.em.createNamedQuery("ImagenPersonaDesaparecida.BuscarImgPersona.Id",ImagenPersonaDesaparecida.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		imgCatastrofe = new ImagenPersonaDesaparecida();
		  		imgCatastrofe.setId(new Long(0));		  		
		  	} else {		  		
		  		imgCatastrofe = (ImagenPersonaDesaparecida) consulta.getResultList().get(0);
		  	}		  	
		  	return imgCatastrofe;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ImagenPersonaDesaparecida> listarImgPersona() throws Exception {
	  	
		try {
			TypedQuery<ImagenPersonaDesaparecida> consulta = this.em.createNamedQuery("ImagenPersonaDesaparecida.BuscarTodas",ImagenPersonaDesaparecida.class);								
			List<ImagenPersonaDesaparecida> imgPersona = consulta.getResultList();			
		  	return imgPersona;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}