package com.web.beans.infoCatastrofe;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.PedidoDeAyudaEBR;
import com.core.service.negocio.remote.RescatistaEBR;


@ManagedBean(name="mapaPedidoAyuda")
@ViewScoped
public class MapaPedidoAyuda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();

	
	private MapModel polygonModel;	
	
	private double latitud;
	
	private double longitud;		
	
	Long idCatastrofe;		
    
    private Marker marker = new Marker(null, "");
    
    private String descripcion;
        
    
    @PostConstruct
    public void init() {
    	
    	FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
		idCatastrofe = (Long)sesion.getAttribute("idmongo");		

    	
            if ((idCatastrofe == null) || (idCatastrofe == 0))
    		{	
    			System.out.println("No existe la catástrofe. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("Home?faces-redirect=true");
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
			        
			        //Marker marker;			        			        
	     			     
			        //marker = new Marker(coord1, "");
			        
			        marker.setLatlng(coord1);
			        
			        String icono = "http://localhost:8080/Proyecto-WebUsuario/imagen/" + "icon-122.png";
			        			        
			        marker.setIcon(icono);			        			        
			        			        
			        polygonModel.addOverlay(marker);
			        
			        marker.setDraggable(true);
			        			       			        			     			     			        			       			        			    			            												      
    			
    		    }catch (Exception excep){
    				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
    			}  
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


	public Long getIdCatastrofe() {
		return idCatastrofe;
	}


	public void setIdCatastrofe(Long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}	

	
	public Marker getMarker() {
		return marker;
	}


	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	//	------------------ Operaciones ---------------------
	
	public void onMarkerDrag(MarkerDragEvent event) {
		marker = event.getMarker();     	
		
		/*
		double latP = marker.getLatlng().getLat();
		double lngP = marker.getLatlng().getLng();				
		
		System.out.println("lat: " + latP); 
		System.out.println("lng: " + lngP); 
		
		LatLng p = new LatLng(latP, lngP);
		
		boolean existe = insidePolygon(p);
		
		if (existe) {
			System.out.println("esta en la zona: ");			
		}
		else {
			System.out.println("no esta en la zona: ");
		}
		*/
		
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));		
    }
	
	/*
	public void onPoligonSelect(MarkerDragEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rectangle Selected", null));
				
		Marker marker;
		
		marker = event.getMarker();
		
		double latP = marker.getLatlng().getLat();
		double lngP = marker.getLatlng().getLng();
		
		polygonModel.addOverlay(marker);
		
		System.out.println("lat: " + latP); 
		System.out.println("lng: " + lngP); 
    }
	
	*/
	
	public boolean insidePolygon(LatLng p) {
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
		idCatastrofe = (Long)sesion.getAttribute("idmongo");		
    	
        if ((idCatastrofe == null) || (idCatastrofe == 0))
    	{	
    			System.out.println("No existe la catástrofe. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("Home?faces-redirect=true");
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
										
					List<Double> list = manager.ListarCoordenasCatastrofe(idCatastrofe);					
					
					int counter = 0;							
					double xinters;
					int n = list.size()/2;
					
					LatLng p1 = new LatLng(0.0, 0.0);
					LatLng p2  = new LatLng(0.0, 0.0);
					
					p1 = new LatLng(list.get(0),list.get(1));										
					
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
					
					for (int i=1; i<n; i++){						
						p2 = polygon.getPaths().get(i % n);
						if (p.getLng() > Math.min(p1.getLng(), p2.getLng()))
						{
							if (p.getLng() <= Math.max(p1.getLng(), p2.getLng()))
					         {
					            if (p.getLat() <= Math.max(p1.getLat(), p2.getLat()))
					            {
					               if (p1.getLng() != p2.getLng()) {
					                  xinters = (p.getLng() - p1.getLng()) * (p2.getLat() - p1.getLat()) / (p2.getLng() - p1.getLng()) + p1.getLat();
					                  if (p1.getLat() == p2.getLat() || p.getLat() <= xinters)
					                     counter++;
					               }
					            }
					         }
					      }
					      p1 = p2;
					   }
					
						if (counter % 2 == 0)
						{
							return(false);
						}
						else
						{
							return(true);
						}												
					    			    																				
    			}catch (Exception excep){
    				System.out.println("Excepción en el bean MapaPedidoAyuda: " + excep.getMessage());      		 			       	            	
    			}  
    		}		
				
		return (false);	
	}
		
	
	public void  registrarPedidoAyuda(){	
		
		double latP = marker.getLatlng().getLat();
		double lngP = marker.getLatlng().getLng();
		
		
		
				
		LatLng p = new LatLng(latP, lngP);
		
		boolean existe = insidePolygon(p);
		
		if (existe) {
			//System.out.println("Esta en la zona en registrarPedidoAyuda registro Pedido.");		
			try{ 
				
				PedidoDeAyudaEBR manager = null;				
    			Context context = null;			
    			//FacesMessage message = null; 
    			
    			try {
    	            // 1. Obtaining Context
    	            context = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    	            manager = (PedidoDeAyudaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//PedidoDeAyudaEB!com.core.service.negocio.remote.PedidoDeAyudaEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
				
    			RescatistaEBR managerR = null;				
    			Context contextR = null;			    			
    			
    			try {
    	            // 1. Obtaining Context
    				contextR = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    				managerR = (RescatistaEBR) contextR.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
				
    			Date fechaPublicacion= new Date();
        		fechaPublicacion.getTime();
        		
        		FacesContext contexto = FacesContext.getCurrentInstance();
    			HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
    			idCatastrofe = (Long)sesion.getAttribute("idmongo");
           		Long id = manager.crearPedido(idCatastrofe, descripcion, latP, lngP, fechaPublicacion);   
           		
           		if (id==0){
           			
           			//FacesContext contex = FacesContext.getCurrentInstance(); 
        	        //FacesMessage messages = new FacesMessage("Error al solicitar pedido de ayuda. "); 
        	        //contexto.addMessage("pedidoAyudaBean", messages);
           		}
           		else {  
           			
           			managerR.asignarRescatistaCatastrofe(id);           		
        			this.descripcion = "";
        			
        			
        			 
        	        
        	        FacesMessage messages = new FacesMessage("Pedido realizada con exito !!",null); 
        	        contexto.addMessage("deServicioBean", messages);
        	        
        			/*
               		
               		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    				handler.performNavigation("Home?faces-redirect=true");
        	        */
        	          
           		}
           		           												        
			}catch (Exception excep){
				System.out.println("Excepción en MapaPedidoAyuda en registrarPedidoAyuda() " + excep.getMessage());   				
			}  
			
		}
		else {
			System.out.println("No esta en la zona registrarPedidoAyuda NO registro Pedido.");
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage messages = new FacesMessage("Debe hacer click en la zona de la catástrofe !!"); 
 	        contexto.addMessage("pedidoAyudaBean", messages);
			
		}		
		
	}
	

}
