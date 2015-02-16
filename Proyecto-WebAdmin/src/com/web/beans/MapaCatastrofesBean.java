package com.web.beans;


import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.annotation.PostConstruct; 

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="mapaCatastrofesBean")
@SessionScoped
public class MapaCatastrofesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();		
	
	private MapModel polygonModel = new DefaultMapModel();;	
	
	private double latitud;
	
	private double longitud;
	
	private Marker marker;
    
	@PostConstruct
    public void init() {
		
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
			
			List<Catastrofe> catastrofes = manager.listaCatastrofes();			
					
			Catastrofe catastrofe;
			Long idCatastrofe;
	    	String nombreEvento;
	    	String descripcionCatastrofe;
	    	String logo;
	    	double coordenadasX;
	    	double coordenadasY;
	    	Boolean activa;
	    	Boolean prioridad;
	    	String css;		
	    	
	    	
	    	for (int i=0; i<=catastrofes.size()-1; i++){
	    		catastrofe = catastrofes.get(i);
	    		idCatastrofe = catastrofe.getId();
	    		nombreEvento = catastrofe.getNombreEvento();
	    		descripcionCatastrofe = catastrofe.getDescripcion();												
	    		logo = catastrofe.getLogo();																								
	    		coordenadasX = catastrofe.getCoordenadasX();
	    		coordenadasY = catastrofe.getCoordenadasY();
	    		activa = catastrofe.getActiva();
	    		prioridad = catastrofe.getPrioridad();  
	    		css = null;
	    		catastrofeBean = new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad,css);			
								
	    		List<Double> list = manager.ListarCoordenasCatastrofe(idCatastrofe);
			
			
	    		//Construyo el poligono
			
				//polygonModel = new DefaultMapModel();
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
			
				if (catastrofe.getPrioridad()){
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
		        			        
		        latitud = catastrofe.getCoordenadasX();
		        longitud = catastrofe.getCoordenadasY();
		        			       
		        LatLng coord1 = new LatLng(latitud, longitud);
	        			       			        			     			     
		        marker = new Marker(coord1, nombreEvento);
		        
		        String icono = "http://localhost:8080/Proyecto-WebAdmin/image/" + logo;
		        			        
		        marker.setIcon(icono);
		        			        
		        polygonModel.addOverlay(marker);
	        
	        
	    	}								       
		
	    }catch (Exception excep){
			System.out.println("Excepción en MapaCatastrofesBean: " + excep.getMessage());      		 			       	            	
		}  
			
		
	}
	
	//	------------------ Getter and setter methods ---------------------
		
	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}

	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}

	public MapModel getPolygonModel() {
		return polygonModel;
	}

	public void setPolygonModel(MapModel polygonModel) {
		this.polygonModel = polygonModel;
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

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	//	------------------ Operaciones ---------------------
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();           
        
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }
			
}
