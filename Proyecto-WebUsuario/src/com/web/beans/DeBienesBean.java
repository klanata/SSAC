package com.web.beans;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import clienteutility.ClienteUtility;
import com.core.service.negocio.remote.DeBienesEBR;




@ManagedBean(name="deBienesBean")
@SessionScoped
public class DeBienesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long idOng;
	private String usuario = "";
	private Date fechaRealizada;
	private String nombreItem = "";
	private Integer cantidad;
	
	public Long getIdOng() {
		return idOng;
	}
	public void setIdOng(Long idOng) {
		this.idOng = idOng;
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
	public String getNombreItem() {
		return nombreItem;
	}
	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public void registrarDonacionDeBienes(){
		DeBienesEBR manager = null;
		Context context = null;
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
           manager = (DeBienesEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//DeBienesEB!com.core.service.negocio.remote.DeBienesEBR");
		
		} catch (NamingException e) {
        e.printStackTrace();
		}
		try{    	
			Date fechaRealizada = new Date();
    		fechaRealizada.getTime();
	
		manager.crearDonacionDeBienes(idOng, usuario, fechaRealizada, nombreItem, cantidad);    	
		}catch (Exception excep){
		System.out.println("Excepcion en donacion de bienes: " + excep.getMessage());      		 			       
		
		}
	}
}