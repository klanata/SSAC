package com.web.beans.reportes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import clienteutility.ClienteUtility;

import com.core.data.entites.Administrador;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.service.negocio.remote.AdministradorEBR;
import com.web.beans.administrador.AdministradorBean;


@ManagedBean(name="ReporteDonacion")
@RequestScoped
public class ReporteDonacionesBean implements Serializable { 

 


 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	 @ManagedProperty("#{DeBienesBean}")
	 private DonacionesDeBienesBean deBienesBean;
	
	public String TipoDonacion;

	private Date fechaInicio;
	private Date fechaFinal;
	
	private Collection<DeBienes> listaDeBienes = new ArrayList<DeBienes>();
	private Collection<DeServicios> listaDeServicios = new ArrayList<DeServicios>();
	private Collection<Economica> listaEconomica =  new ArrayList<Economica>();
     
	
	
	public DonacionesDeBienesBean getDeBienesBean() {
		return deBienesBean;
	}

	public void setDeBienesBean(DonacionesDeBienesBean deBienesBean) {
		this.deBienesBean = deBienesBean;
	}

	public Collection<DeBienes> getListaDeBienes() {
		return listaDeBienes;
	}

	public void setListaDeBienes(Collection<DeBienes> listaDeBienes) {
		this.listaDeBienes = listaDeBienes;
	}

	public Collection<DeServicios> getListaDeServicios() {
		return listaDeServicios;
	}

	public void setListaDeServicios(Collection<DeServicios> listaDeServicios) {
		this.listaDeServicios = listaDeServicios;
	}

	public Collection<Economica> getListaEconomica() {
		return listaEconomica;
	}

	public void setListaEconomica(Collection<Economica> listaEconomica) {
		this.listaEconomica = listaEconomica;
	}

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
		
		
    	if (TipoDonacion.equals("De_Bienes")){
    		
    		Collection<DeBienes> lista = new ArrayList<DeBienes>();
    				
			lista = manager.listaDeBienesEnTiempo(fechaInicio, fechaFinal);
			
			
			Iterator<DeBienes> it = lista.iterator();
			
			while(it.hasNext())
			{
				//DonacionesDeBienesBean
			}
			
			
    	}
    	else if (TipoDonacion.equals("De_Servicios")) {
			
    		
		} else {
			
			Collection<Economica> lista = new ArrayList<Economica>();
			lista = manager.listaDeEconomicaEnTiempo(fechaInicio, fechaFinal);

		}
    	
    }
   
     
}