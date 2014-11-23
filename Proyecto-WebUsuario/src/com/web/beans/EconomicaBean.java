package com.web.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import clienteutility.ClienteUtility;
import com.core.service.negocio.remote.EconomicaEBR;


@ManagedBean(name="economicaBean")
@SessionScoped
public class EconomicaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long ong;
	private String usuario = "";
	private Date fechaRealizada;
	private BigDecimal monto;
	
	public Long getOng() {
		return ong;
	}
	public void setOng(Long ong) {
		this.ong = ong;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public void  registrarDonacionEconomica(){		
		

		EconomicaEBR manager = null;
		
		Context context = null;
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (EconomicaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//EconomicaEB!com.core.service.negocio.remote.EconomicaEBR");
 
		 } catch (NamingException e) {
	            e.printStackTrace();
	        }
		try{    		   	    	  
    	   		
    		//Date fechaPublicacion = new Date();
    		//fechaPublicacion.getTime();
    		
       		manager.crearDonacionEconomica(ong, usuario, fechaRealizada, monto);    	
    		
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en donacion economica: " + excep.getMessage());      		 			       
	        
    		
    	}    
	}
}
	
