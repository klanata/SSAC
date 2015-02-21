package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="imagesView")
@RequestScoped
public class ImagesView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	private List<ImagenCatastrofeBean> images = new ArrayList<ImagenCatastrofeBean>(); 
	
	@ManagedProperty("#{imgCatastrofeBean}")
    private ImagenCatastrofeBean imgCatastrofeBean;
	
	private MapModel polygonModel;	
	
	private double latitud;
	
	private double longitud;
	
	private Marker marker;
    
    @PostConstruct
    public void init() {
    	
    		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
            if ((idEventoString == null) || (idEventoString == ""))
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
    				Long idCatastrofe = new Long(idEventoString);
    				
    				Catastrofe catastrofe = new Catastrofe();
					catastrofe = manager.buscaCatastrofePorId(idCatastrofe); 				
												
			    	String nombreEvento;
			    	String descripcionCatastrofe;
			    	String logo;
			    	double coordenadasX;
			    	double coordenadasY;
			    	Boolean activa;
			    	Boolean prioridad;
			    	String css;					    	
			    	
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
					
					//Muestra la lista de coordenadas del poligono
					//for (int i=0; i<=list.size()-1; i++){
						//double resultado = list.get(i);
						//System.out.println("valor de coordenadas en list: " + resultado);
					//}
					
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
			        ///////////////////////////////////////////
			        
    				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
    				res = manager.listaImagenesDeCatastrofe(idCatastrofe);				
    				Long id;
    				String path;				
    				int i = 0;
    				for (ImagenCatastrofe imgCatastrofe : res){
    					id = imgCatastrofe.getId();
    					path = imgCatastrofe.getPath();					
    					images.add(i, new ImagenCatastrofeBean(id,path));
    					i = i + 1;
    				}										       
    			
    		    }catch (Exception excep){
    				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
    			}  
    		}    		    	
                        
    }
    
    
    //	------------------ Getter and setter methods ---------------------
    

	public List<ImagenCatastrofeBean> getImages() {
		return images;
	}

	public void setImages(List<ImagenCatastrofeBean> images) {
		this.images = images;
	}

	public ImagenCatastrofeBean getImgCatastrofeBean() {
		return imgCatastrofeBean;
	}

	public void setImgCatastrofeBean(ImagenCatastrofeBean imgCatastrofeBean) {
		this.imgCatastrofeBean = imgCatastrofeBean;
	}


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

	public void siguiente(){	
		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{
			Long idCatastrofe = new Long(idEventoString);
			
			CatastrofeEBR manager = null;				
			Context context = null;			
			FacesMessage message = null; 
			
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
				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
				res = manager.listaImagenesDeCatastrofe(idCatastrofe);	
				
				if (res.size() == 0){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar al menos una imagen de la catástrofe.");
	    			FacesContext.getCurrentInstance().addMessage(null, message);					
				}
				else{
					ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("vistaOngsCatastrofe?faces-redirect=true");					
				}
												
			}catch (Exception excep){
	    		System.out.println("Excepcion al listar los ImagesView: " + excep.getMessage());      		 			       
		           		
	    	}
												
		}
	}
	
	public void asignarImagen(){
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarImgCatastrofe?faces-redirect=true");			
	}
	
	public void borrarImagen(){			
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("quitarImagenCatastrofe?faces-redirect=true");		
	}
	
	public void cancelar(){		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificarCatastrofe?faces-redirect=true");		
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();                   
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }

}
