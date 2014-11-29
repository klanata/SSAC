package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.persistencia.JPAService;


@Local
public interface ImagenPersonaDesaparecidaDAO extends JPAService {
	
	public Long insert(ImagenPersonaDesaparecida imgPersona)throws Exception;
	
	public boolean existePathImagPersona(String pathImg);
	
	public ImagenPersonaDesaparecida buscarImgPersonaPorPath(String path) throws Exception;
	
	public ImagenPersonaDesaparecida buscarImgPersonaPorId(Long id) throws Exception;

	public List<ImagenPersonaDesaparecida> listarImgPersona() throws Exception;

}
