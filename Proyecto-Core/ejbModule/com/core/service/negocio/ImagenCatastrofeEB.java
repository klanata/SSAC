package com.core.service.negocio;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.service.negocio.remote.ImagenCatastrofeEBR;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.persistencia.interfaces.locales.ImagenCatastrofeDAO;


import com.core.data.persistencia.DataService;


@Path("/imagenCatastrofe")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//ImagenCatastrofeEB!com.core.service.negocio.remote.imagenCatastrofeEBR")
public class ImagenCatastrofeEB implements ImagenCatastrofeEBR{
	
	@EJB
	private DataService dataService;
	
	@EJB
	private ImagenCatastrofeDAO imagenCatastrofeDAO;	
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ImagenCatastrofe buscarImgCatastrofePorPath(String path) throws Exception {
		ImagenCatastrofe imgCat = new ImagenCatastrofe();
		imgCat = imagenCatastrofeDAO.buscarImgCatastrofePorPath(path);
		return imgCat;
	}		
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ImagenCatastrofe buscarImgCatastrofePorId(Long id) throws Exception {
		ImagenCatastrofe imgCat = new ImagenCatastrofe();
		imgCat = imagenCatastrofeDAO.buscarImgCatastrofePorId(id);
		return imgCat;
	}
	
	public List<ImagenCatastrofe> listarImgCatastrofes() throws Exception {
		List<ImagenCatastrofe> listImgCatastrofes = new ArrayList<ImagenCatastrofe>();
		listImgCatastrofes = imagenCatastrofeDAO.listarImgCatastrofes();
		return listImgCatastrofes;		
	}
	
	
}
