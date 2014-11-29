
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
import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImagenCatastrofeDAOImpl extends AbstractService implements ImagenCatastrofeDAO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(ImagenCatastrofe imgCatastrofe)throws Exception {	
		Long id;
		String nombImg = imgCatastrofe.getPath();
		try {
			if (this.existePathImagCatastrofe(nombImg)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(imgCatastrofe);					
				TypedQuery<Long> consulta = this.em.createNamedQuery("ImagenCatastrofe.BuscarImgCatastrofeId.PathImg",Long.class);				
				consulta.setParameter("pathImg", nombImg);				
				List<Long> imgCatastrofesId = consulta.getResultList();
				if (imgCatastrofesId.isEmpty()) {
					id = (long) 0;	
			  	} else {
			  		id = imgCatastrofesId.get(0);
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existePathImagCatastrofe(String pathImg) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("ImagenCatastrofe.BuscarImgCatastrofeId.PathImg");
			consulta.setParameter("pathImg", pathImg);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ImagenCatastrofe buscarImgCatastrofePorPath(String path) throws Exception {
	  	
		ImagenCatastrofe imgCatastrofe;
		try {
			Query consulta = this.em.createNamedQuery("ImagenCatastrofe.BuscarImgCatastrofe.PathImg",ImagenCatastrofe.class);			
		  	consulta.setParameter("path", path);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		imgCatastrofe = new ImagenCatastrofe();
		  		imgCatastrofe.setId(new Long(0));		  		
		  	} else {		  		
		  		imgCatastrofe = (ImagenCatastrofe) consulta.getResultList().get(0);
		  	}		  	
		  	return imgCatastrofe;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ImagenCatastrofe buscarImgCatastrofePorId(Long id) throws Exception {
	  	
		ImagenCatastrofe imgCatastrofe;
		try {
			Query consulta = this.em.createNamedQuery("ImagenCatastrofe.BuscarImgCatastrofe.Id",ImagenCatastrofe.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		imgCatastrofe = new ImagenCatastrofe();
		  		imgCatastrofe.setId(new Long(0));		  		
		  	} else {		  		
		  		imgCatastrofe = (ImagenCatastrofe) consulta.getResultList().get(0);
		  	}		  	
		  	return imgCatastrofe;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ImagenCatastrofe> listarImgCatastrofes() throws Exception {
	  	
		try {
			TypedQuery<ImagenCatastrofe> consulta = this.em.createNamedQuery("ImagenCatastrofe.BuscarTodas",ImagenCatastrofe.class);								
			List<ImagenCatastrofe> imgCatastrofes = consulta.getResultList();			
		  	return imgCatastrofes;		
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
