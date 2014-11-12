package com.serviciorest;

import java.math.BigDecimal;
import java.util.Date;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import clienteutility.ClienteUtility;

import com.core.data.entites.PedidoDeAyuda;
import com.core.service.negocio.remote.PedidoDeAyudaEBR;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Path("ayuda") 
@Stateless
@LocalBean
public class ServicioPedidoDeAyuda {
	
private PedidoDeAyudaEBR manager;
	
	private Context context;
	
	private String conexion = "ejb:Proyecto-EAR/Proyecto-Core//PedidoDeAyudaEB!com.core.service.negocio.remote.PedidoDeAyudaEBR";
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String pedirAyudaPrueba()
	{
		return "La desc es:";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("pedirAyuda")
	public void pedirAyuda(@QueryParam("catId") String catastrofeId, @QueryParam("des") String descripcion) throws Exception
	{
		
		manager = null;
		context = null;
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (PedidoDeAyudaEBR) context.lookup(conexion);
 
            
            //com.serviciorest.modelo.PedidoDeAyudaModelo pedidoAyudaRest = null;
    		com.core.data.entites.PedidoDeAyuda pedidoAyuda = new PedidoDeAyuda();
    		
    		pedidoAyuda.setId(1);
    		pedidoAyuda.setDescripcion(descripcion);
    		
    		Date fechaPublicacion = new Date();
    			
    		//pedidoAyuda.setFechaPublicacion(df.format(fechaPublicacion));
    		pedidoAyuda.setFechaPublicacion(fechaPublicacion);
    		BigDecimal coordX = null;
    		coordX.valueOf(0.58);
    		BigDecimal coordY = null;
    		coordY.valueOf(0.38);
    		pedidoAyuda.setCoordenadasX(coordX);
    		pedidoAyuda.setCoordenadasY(coordY);
    			
    		manager.crearPedido(pedidoAyuda);
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
	}
}
