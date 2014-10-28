package com.serviciorest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persona")
public class ServicioRestCatastrofe {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ping()
	{
		return "<p>Ping satisfactorio - GestionCN</p>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String imprimir(@PathParam(value = "nose") String persona)
	{
		return "Hola persona";
	}
}
