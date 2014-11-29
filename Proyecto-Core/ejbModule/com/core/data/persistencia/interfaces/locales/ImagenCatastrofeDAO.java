package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.ImagenCatastrofe;
import com.core.data.persistencia.JPAService;


@Local
public interface ImagenCatastrofeDAO extends JPAService{
	
	public Long insert(ImagenCatastrofe imgCatastrofe)  throws Exception;
	
	public boolean existePathImagCatastrofe(String pathImg);		
	
	public ImagenCatastrofe buscarImgCatastrofePorId(Long id) throws Exception;

	public List<ImagenCatastrofe> listarImgCatastrofes() throws Exception;

}
