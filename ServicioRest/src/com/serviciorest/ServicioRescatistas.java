package com.serviciorest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import clienteutility.ClienteUtility;

import com.core.data.entites.EstadoRescatista;
import com.core.service.negocio.remote.RescatistaEBR;
import com.serviciorest.modelo.MiBoolean;
import com.serviciorest.modelo.PlanesPendientesRescatistaRest;

import cross_cuting.enums.PlanesPendientesRescatistaDTO;

@Path("rescatista")
@Stateless
@LocalBean
public class ServicioRescatistas {

	private RescatistaEBR manager;

	private Context context;

	private String conexion = "ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR";

	// /////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("login")
	public MiBoolean login(@QueryParam("nick") String nick,
			@QueryParam("pass") String pass) throws Exception {
		manager = null;
		context = null;

		MiBoolean existeUsuario = new MiBoolean();

		try {
			// 1. Obtaining Context
			context = ClienteUtility.getInitialContext();
			// 2. Generate JNDI Lookup name
			// String lookupName = getLookupName();
			// 3. Lookup and cast
			manager = (RescatistaEBR) context.lookup(conexion);
			// Descomentar lo siguiente cuando esté implementado el método en
			// core:
			boolean b = manager.buscarUsuario(nick, pass);
			existeUsuario.setBooleanValue(b);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return existeUsuario;
	}

	// /////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("verPendientes")
	public List<PlanesPendientesRescatistaDTO> getPlanesPendientes(
			@QueryParam("nick") String nick) throws Exception {
		manager = null;
		context = null;

		List<PlanesPendientesRescatistaDTO> planesPendientes = new ArrayList<PlanesPendientesRescatistaDTO>();

		try {
			// 1. Obtaining Context
			context = ClienteUtility.getInitialContext();
			// 2. Generate JNDI Lookup name
			// String lookupName = getLookupName();
			// 3. Lookup and cast
			manager = (RescatistaEBR) context.lookup(conexion);
			// Descomentar lo siguiente cuando esté implementado el método en
			// core:
			// path = manager.getRutaPlan(nombreArchivo);

			Long idCatastrofe = new Long(1);// Borrar cuando se modifique el
											// método.
			planesPendientes= (List<PlanesPendientesRescatistaDTO>)
			manager.listarPendientesRescatistaPorCatastrofe(nick);//cambiar
			// cuando se modifique el método.
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Éstos son datos de prueba */
		List<PlanesPendientesRescatistaRest> listaPlanesAux = new ArrayList<PlanesPendientesRescatistaRest>();

		PlanesPendientesRescatistaRest a = new PlanesPendientesRescatistaRest();
		a.setIdEstadoRescatista(new Long(1));
		a.setDescripcion("Ayuda!!");
		a.setCoordPedidoAyudaX(-34.58400);
		a.setCoordPedidoAyudaY(-55.2541);
		a.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(a);

		PlanesPendientesRescatistaRest b = new PlanesPendientesRescatistaRest();
		b.setIdEstadoRescatista(new Long(2));
		b.setDescripcion("Ayuda por favor!!");
		b.setCoordPedidoAyudaX(-33.58400);
		b.setCoordPedidoAyudaY(-55.2541);
		b.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(b);

		PlanesPendientesRescatistaRest c = new PlanesPendientesRescatistaRest();
		c.setIdEstadoRescatista(new Long(3));
		c.setDescripcion("Estoy en Av Italia y Propios!!");
		c.setCoordPedidoAyudaX(-33.58400);
		c.setCoordPedidoAyudaY(-55.2541);
		c.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(c);

		//return listaPlanesAux;

		return planesPendientes;
	}

	// /////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("plan")
	public String getPlan(@QueryParam("nombreArchivoPlan") String nombreArchivo)
			throws Exception {

		String path = "";
		String nombre = nombreArchivo;

		try {

			path = "/ServicioRest/WebContent/WEB-INF/content/PropuestaProyecto20140831v03.pdf";
			//path = "/ServicioRest/WebContent/WEB-INF/content/"+nombre;
			// http://localhost:8080/ServicioRest/WebContent/WEB-INF/ViewerJS#../content/PropuestaProyecto20140831v03.pdf

		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	@GET
	@Path("/pdf")
	@Produces("application/pdf")
	public Response getPDF() {

		String PDF_FILE = "C:\\wamp\\www\\RescatistasApp\\www\\PropuestaProyecto20140831v03.pdf";
		File file = new File(PDF_FILE);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=\"test_PDF_file.pdf\"");
		return response.build();

	}

	@GET
	@Path("/pdfPrueba")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile() {
		File file = new File(
				"C:\\wamp\\www\\RescatistasApp\\www\\PropuestaProyecto20140831v03.pdf");
		return (Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).header(
				"Content-Disposition",
				"attachment; filename=\"" + file.getName() + "\"") // optional
				.build());
	}

	// /////////////////////////////////////////////////////////////////////////
	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("finalizarPlan")
	public void finalizarPlan(@QueryParam("idEstadoRescatista") String idEstadoRescatista) throws Exception {
		manager = null;
		context = null;

		
		EstadoRescatista estadoRescatista = new EstadoRescatista();
		estadoRescatista.setId(new Long(idEstadoRescatista));

		try {
			// 1. Obtaining Context
			context = ClienteUtility.getInitialContext();
			// 2. Generate JNDI Lookup name
			// String lookupName = getLookupName();
			// 3. Lookup and cast
			manager = (RescatistaEBR) context.lookup(conexion);
			manager.RealizadoPendiente(estadoRescatista);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
