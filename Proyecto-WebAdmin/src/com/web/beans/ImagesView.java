package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Marker;

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
	
	private MapModel advancedModel;
	
	private Marker marker;
	
	private double latitud;
	
	private double longitud;
    
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

					/*
					String poligono;
					poligono = catastrofe.getPoligono();					
					
					int index = 0;
					char fin = ']';
					boolean esNegativo = false; 
					char digChar;
					double coordenada = 0;			
					List<Double> list = new ArrayList<Double>();					
					
					do{
						//Recorro hasta ":":
						coordenada = 0;	
						while(poligono.charAt(index) != ':'){
							index ++;														
						}
						index ++;
						if (poligono.charAt(index) == '-'){
							esNegativo = true;							
						}
						else {
							esNegativo = false;
						}
						index ++;
						while(poligono.charAt(index) != '.'){
							digChar = poligono.charAt(index);	
							int dig = digChar - '0';
							coordenada = coordenada*10 + dig;
							index ++;																	
						}	
						index ++;
						int cont = 1;
						double digDespDeLaComa = 0;						
						while((poligono.charAt(index) >= '0') && (poligono.charAt(index) <= '9')){
							digChar = poligono.charAt(index);	
							int dig = digChar - '0';
							double acum;
							double multi = 1;
							for(int x = 1; x <= cont; x++) {
								multi = 0.1 * multi;								
							}
							acum = dig * multi;
							cont  ++;
							index ++;
							digDespDeLaComa = digDespDeLaComa + acum;							
						}	
						coordenada = coordenada + digDespDeLaComa;
						if (esNegativo) {
							coordenada = coordenada * (-1);
						}		
						
						index ++;
						list.add(coordenada);																
						System.out.println("value of coordenada: " + coordenada);								
						
					}while( poligono.charAt(index) != fin );										
					
					*/
										
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
			        String logoCatatrofe = catastrofe.getLogo();
			        
			        advancedModel = new DefaultMapModel();
			        
			        LatLng coord1 = new LatLng(latitud, longitud);
			        
			        
			        advancedModel.addOverlay(new Marker(coord1, "Konyaalti", logoCatatrofe, "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
			        
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

 
	//	------------------ Operaciones ---------------------

	

	public void asignarImagen(){
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarImgCatastrofe?faces-redirect=true");			
	}
	
	public void borrarImagen(){			
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("quitarImagenCatastrofe?faces-redirect=true");		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeImg", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");		
	}
    

}
