package com.core.service.negocio;
/*Autor
 * Stephy*/
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.RescatistaDAO;
import com.core.service.negocio.remote.RescatistaEBR;

@Path("/rescatista") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR")

public class RescatistaEB implements RescatistaEBR {
	
	@EJB
	private DataService dataService;
	
	@EJB 
	private RescatistaDAO rescatistaService;
	//////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////
	@Override
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe) {
		
		try{
		
			Long id = new Long (catastrofe.getId());
			//obtengo objeto catastrofe
			Catastrofe catastrofeGuardar = dataService.find(Catastrofe.class, id);
			EstadoRescatista estadoRescatista = new EstadoRescatista();
			estadoRescatista.setPendiente(true);
			estadoRescatista.setCatastrofe(catastrofeGuardar);
			//busco el rescatista para asignar
			Rescatista rescatista = rescatistaService.obtenerRescatistaConMenosPendientes();
			estadoRescatista.setRescatista(rescatista);
			dataService.create(estadoRescatista);
			
			//obtenemos la lista de pendientes del rescatista 
			Collection<EstadoRescatista> listaEstado= rescatista.getEstadoRescatista();
			listaEstado.add(estadoRescatista);
			dataService.update(rescatista);
			
		
			
		}catch (Exception e )
		{
			e.printStackTrace();
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////
	@Override
	public Long crearRescatista(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular) throws Exception {
			
		Rescatista r = new Rescatista();
		r.setNombre(nombre);
		r.setApellido(apellido);
		r.setCelular(celular);
		r.setEmail(email);
		r.setFechaNac(fechaNac);
		r.setPassword(password);
		r.setSexo(sexo);
		Long id = rescatistaService.insert(r);
		return id;
	}

	
	

}
