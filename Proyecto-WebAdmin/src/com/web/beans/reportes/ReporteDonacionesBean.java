package com.web.beans.reportes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.AdministradorEBR;


@ManagedBean(name="ReporteDonacion")
@RequestScoped
public class ReporteDonacionesBean implements Serializable { 

 


 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String TipoDonacion;
	
	
     
	public String getTipoDonacion() {
		return TipoDonacion;
	}

	public void setTipoDonacion(String tipoDonacion) {
		TipoDonacion = tipoDonacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	

	private Date fechaInicio;
	private Date fechaFinal;
	
    @PostConstruct
    public void init() {
       
    }
    public void  obtenerReporteEnelTiempo(){
    	
    	AdministradorEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (AdministradorEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }	
		
		
    	
    	
    }
   
     
}