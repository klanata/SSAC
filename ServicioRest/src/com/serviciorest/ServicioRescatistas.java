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

import cross_cuting.enums.PlanesPendientesRescatistaDTO;

@Path("rescatista") 
@Stateless
@LocalBean
public class ServicioRescatistas {

private RescatistaEBR manager;
	
	private Context context;
	
	private String conexion = "ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR";
	
		
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("login")
	public MiBoolean login(@QueryParam("nick") String nick, @QueryParam("pass") String pass) throws Exception
	{
		manager = null;
		context = null;
		
		MiBoolean existeUsuario = new MiBoolean();
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup(conexion);
            //Descomentar lo siguiente cuando esté implementado el método en core:
            boolean b = manager.buscarUsuario(nick, pass);
            existeUsuario.setBooleanValue(b);
           
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return existeUsuario;
	}
	
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("verPendientes")
	public List<PlanesPendientesRescatistaDTO> getPlanesPendientes(@QueryParam("nick") String nick) throws Exception
	{
		manager = null;
		context = null;
		
		List<PlanesPendientesRescatistaDTO> planesPendientes = new ArrayList<PlanesPendientesRescatistaDTO>();
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup(conexion);
            //Descomentar lo siguiente cuando esté implementado el método en core:
            //path = manager.getRutaPlan(nombreArchivo);
           
           Long idCatastrofe = new Long(1);//Borrar cuando se modifique el método.
           //planesPendientes= (List<PlanesPendientesRescatistaDTO>) manager.listarPendientesRescatistaPorCatastrofe(nick);//cambiar cuando se modifique el método.
           
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		/*Éstos son datos de prueba*/
		List<PlanesPendientesRescatistaDTO> listaPlanesAux = new ArrayList<PlanesPendientesRescatistaDTO>();
		
		PlanesPendientesRescatistaDTO a = new PlanesPendientesRescatistaDTO();
		a.setIdCatastrofe(new Long(1));
		a.setIdEstadoRescatista(new Long(1));
		a.setNombreTarea("Inundacion");
		a.setEstadoTarea(true);
		a.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(a);
		
		PlanesPendientesRescatistaDTO b = new PlanesPendientesRescatistaDTO();
		b.setIdCatastrofe(new Long(2));
		b.setIdEstadoRescatista(new Long(2));
		b.setNombreTarea("IncendioForestal");
		b.setEstadoTarea(true);
		b.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(b);
		
		PlanesPendientesRescatistaDTO c = new PlanesPendientesRescatistaDTO();
		c.setIdCatastrofe(new Long(3));
		c.setIdEstadoRescatista(new Long(3));
		c.setNombreTarea("Dengue");
		c.setEstadoTarea(true);
		c.setUrlArchivo("http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf");
		listaPlanesAux.add(c);
		
		return listaPlanesAux;
		
		//return planesPendientes;
	}
	
	///////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("plan")
	public String getPlan(@QueryParam("nombreArchivoPlan") String nombreArchivo) throws Exception
	{
			
		String path = "";
		
		try {
            
           path = "/ServicioRest/WebContent/WEB-INF/content/PropuestaProyecto20140831v03.pdf";
           //http://localhost:8080/ServicioRest/WebContent/WEB-INF/ViewerJS#../content/PropuestaProyecto20140831v03.pdf
           
 
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
        response.header("Content-Disposition", "attachment; filename=\"test_PDF_file.pdf\"");
        return response.build();

    }
    
    @GET
    @Path("/pdfPrueba")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile() {
      File file = new File("C:\\wamp\\www\\RescatistasApp\\www\\PropuestaProyecto20140831v03.pdf");
      return (Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
          .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
          .build());
    }

	
}
