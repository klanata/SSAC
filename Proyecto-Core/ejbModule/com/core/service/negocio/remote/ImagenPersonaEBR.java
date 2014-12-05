package com.core.service.negocio.remote;

import java.util.List;


import com.core.data.entites.ImagenPersonaDesaparecida;

public interface ImagenPersonaEBR {
	
	public ImagenPersonaDesaparecida buscarImgPersonaPorPath(String path) throws Exception;
	
	public ImagenPersonaDesaparecida buscarImgPersonaPorId(Long id) throws Exception;
	
	public List<ImagenPersonaDesaparecida> listarImgPersona() throws Exception;

}
