package com.web.filtro;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="datosCatastrofeBean")
@RequestScoped
public class datosCatastrofeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String logoCatastrofe;
	  String  idCatastrofe;
	  String  cssCatastrofe;
	  String descripcionCatastrofe;
	 
	  
	  
	  public String getLogoCatastrofe() {
		return logoCatastrofe;
	}



	public void setLogoCatastrofe(String logoCatastrofe) {
		this.logoCatastrofe = logoCatastrofe;
	}



	public String getIdCatastrofe() {
		return idCatastrofe;
	}



	public void setIdCatastrofe(String idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}



	public String getCssCatastrofe() {
		return cssCatastrofe;
	}



	public void setCssCatastrofe(String cssCatastrofe) {
		this.cssCatastrofe = cssCatastrofe;
	}



	public String getDescripcionCatastrofe() {
		return descripcionCatastrofe;
	}



	public void setDescripcionCatastrofe(String descripcionCatastrofe) {
		this.descripcionCatastrofe = descripcionCatastrofe;
	}



	public void guardarDatos(Long id,String css, String Descripccion,String logo){	
	  
		  
		  logoCatastrofe = logo;
	  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logoCatastrofe",logoCatastrofe ); 		
	  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idCatastrofe",idCatastrofe );
	  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cssCatastrofe",cssCatastrofe);
	  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("descripcionCatastrofe",descripcionCatastrofe);
	 
	  }
	

}
