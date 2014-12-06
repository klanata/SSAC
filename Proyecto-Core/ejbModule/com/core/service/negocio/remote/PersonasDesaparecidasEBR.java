package com.core.service.negocio.remote;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;


@Remote

public interface PersonasDesaparecidasEBR {
	
	public Long crearReportePersonasDesaparecidas(Long idCatastrofe, String nombre, String apellido, String numeroContacto, String descripcion, Date fechNac,
			Set<ImagenPersonaDesaparecida> imagenes, boolean hallada)throws Exception;

	public void agregarImagenAPersonaDesaparecida(Long idPersona, String nombImagen) throws Exception;
	public Collection<ImagenPersonaDesaparecida> listaImagenesDePersona(Long id) throws Exception;
	public PersonasDesaparecidas buscaPersonaPorId(Long id) throws Exception;
	public PersonasDesaparecidas buscarPersonaDesaparecida(Long idCatastrofe, String nomPersona, String apePersona) throws Exception;
	public List<PersonasDesaparecidas> listarPersonas() throws Exception;
}
