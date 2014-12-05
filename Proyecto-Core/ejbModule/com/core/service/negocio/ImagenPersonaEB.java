package com.core.service.negocio;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.service.negocio.remote.ImagenPersonaEBR;

import com.core.data.entites.ImagenPersonaDesaparecida;



import com.core.data.persistencia.interfaces.locales.ImagenPersonaDesaparecidaDAO;
import com.core.data.persistencia.DataService;


@Path("/imagenPersona")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//ImagenPersonaEB!com.core.service.negocio.remote.ImagenPersonaEBR")
public class ImagenPersonaEB  implements ImagenPersonaEBR{
	
	@EJB
	private DataService dataService;
	
	@EJB
	private ImagenPersonaDesaparecidaDAO imagenPersonaDAO;	
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ImagenPersonaDesaparecida buscarImgPersonaPorPath(String path) throws Exception {
		ImagenPersonaDesaparecida imgCat = new ImagenPersonaDesaparecida();
		imgCat = imagenPersonaDAO.buscarImgPersonaPorPath(path);
		return imgCat;
	}		
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ImagenPersonaDesaparecida buscarImgPersonaPorId(Long id) throws Exception {
		ImagenPersonaDesaparecida imgCat = new ImagenPersonaDesaparecida();
		imgCat = imagenPersonaDAO.buscarImgPersonaPorId(id);
		return imgCat;
	}
	
	public List<ImagenPersonaDesaparecida> listarImgPersona() throws Exception {
		List<ImagenPersonaDesaparecida> listImgCatastrofes = new ArrayList<ImagenPersonaDesaparecida>();
		listImgCatastrofes = imagenPersonaDAO.listarImgPersona();
		return listImgCatastrofes;		
	}
	
	
}
