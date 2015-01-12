package com.web.beans.infoCatastrofe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;

 
@ManagedBean
public class ImagesView {
     
	
	
private MapModel polygonModel;
	
	private MapModel advancedModel;
	
	private Marker marker;
	
	private double latitud;
	
	private double longitud;
	
	
	
    public MapModel getPolygonModel() {
		return polygonModel;
	}

	public void setPolygonModel(MapModel polygonModel) {
		this.polygonModel = polygonModel;
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	private List<String> imagesMostrar;
     
    private String pdfCatastrofe;
    
    
    public String getPdfCatastrofe() {
		return pdfCatastrofe;
	}

	public void setPdfCatastrofe(String pdfCatastrofe) {
		this.pdfCatastrofe = pdfCatastrofe;
	}

	public List<String> getImagesMostrar() {
		return imagesMostrar;
	}

	public void setImagesMostrar(List<String> imagesMostrar) {
		this.imagesMostrar = imagesMostrar;
	}

	@PostConstruct
    public void init() {
    
    	
		/*ServletRequest request = null;
		HttpServletRequest req = (HttpServletRequest) request;
		@SuppressWarnings("null")
		HttpSession session = req.getSession();
		Long idCatastrofe = (long) session.getAttribute("idCatastrofeUsuario");*/
		//Object dato = session.getAttribute(identificador);
    	Long idCatastrofe = new Long(1);
    	//
        if ((idCatastrofe == null) || (idCatastrofe == 00))
		{	
			System.out.println("No existe la catástrofe al asignar imagenes. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{        
	        CatastrofeEBR manager = null;				
			Context context = null;			
			//FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
	            context = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            manager = (CatastrofeEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
		
			try{
				
				System.out.print("esto obtiene de id catastrofe");
				System.out.print(idCatastrofe);
				imagesMostrar = new ArrayList<String>();
		        int posicion = 1;
				if(idCatastrofe != 0){	
		        ///////////////////////////////////////////
					Catastrofe c = new Catastrofe();
				//obtener catastrofe
				 c = manager.buscaCatastrofePorId(idCatastrofe);	

			        latitud = c.getCoordenadasX();
			        longitud = c.getCoordenadasY();
			        String logoCatatrofe = c.getLogo();
				pdfCatastrofe = c.getPlanDeRiesgo().getRutaArchivo();	
				boolean prioridad = c.getPrioridad();
				
				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
				res = manager.listaImagenesDeCatastrofe(idCatastrofe);				
				Long id;
				String path;				
				
				
		        
				for (ImagenCatastrofe imgCatastrofe : res){
					id = imgCatastrofe.getId();
					path = imgCatastrofe.getPath();					
					imagesMostrar.add(path);
			//		
				
				}										       
				
				List<Double> list = manager.ListarCoordenasCatastrofe(idCatastrofe);
				
				for (int i=0; i<=list.size()-1; i++){
					double resultado = list.get(i);
					System.out.println("valor de coordenadas en list: " + resultado);
				}
				
				//Construyo el poligono
				
				polygonModel = new DefaultMapModel();
				Polygon polygon = new Polygon();
				int j= 0;
				while ((j >= 0) && (j < list.size())){
					double lat = list.get(j);
					j++;
					double lng = list.get(j);
					LatLng coord1 = new LatLng(lat, lng);
					polygon.getPaths().add(coord1);
					j++;
				}
				
				if (prioridad){
					polygon.setStrokeColor("#FF0000");
			        polygon.setFillColor("#FF0000");						
				}
				else {
					polygon.setStrokeColor("#FF9900");
			        polygon.setFillColor("#FF9900");
				}
				
		        polygon.setStrokeOpacity(0.7);
		        polygon.setFillOpacity(0.7);			      
		        
		        polygonModel.addOverlay(polygon);
		        
				////////////////////////////////////////
		        
		        
		        
		        
		        advancedModel = new DefaultMapModel();
		        
		        LatLng coord1 = new LatLng(latitud, longitud);
		        
		        
		        advancedModel.addOverlay(new Marker(coord1, "Konyaalti", logoCatatrofe, "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		        
				}}	
		    catch (Exception excep){
				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
			}  
		}    	
    	  	
    	
    	
    	   }
 
    public List<String> getImages() {
        return imagesMostrar;
    }
}