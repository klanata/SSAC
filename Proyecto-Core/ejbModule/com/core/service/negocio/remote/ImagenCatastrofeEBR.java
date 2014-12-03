package com.core.service.negocio.remote;

import java.util.List;

import javax.ejb.Remote;
import com.core.data.entites.ImagenCatastrofe;

@Remote
public interface ImagenCatastrofeEBR {
	
	public ImagenCatastrofe buscarImgCatastrofePorPath(String path) throws Exception;
	
	public ImagenCatastrofe buscarImgCatastrofePorId(Long id) throws Exception;
	
	public List<ImagenCatastrofe> listarImgCatastrofes() throws Exception;
	
	
}
