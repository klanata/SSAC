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
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import clienteutility.ClienteUtility;

import com.core.data.entites.Administrador;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.service.negocio.remote.AdministradorEBR;
import com.web.beans.administrador.AdministradorBean;
import com.web.beans.ong.OngBean;


@ManagedBean(name="ReporteDonacion")
@RequestScoped
public class ReporteDonacionesBean implements Serializable { 

 


 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	@ManagedProperty("#{DonacionesDeBienesBean}")
	private DonacionesDeBienesBean deBienesBean;
	
	public String TipoDonacion;

	private Date fechaInicio;
	private Date fechaFinal;
	
	private ArrayList<DeBienes> listaDeBienes = new ArrayList<DeBienes>();
	private ArrayList<DeServicios> listaDeServicios = new ArrayList<DeServicios>();
	private ArrayList<Economica> listaEconomica =  new ArrayList<Economica>();
    
	private Integer cantidadDB;
	private Integer cantidadDS;
	private Integer cantidadDE;
	
	
	private List<DeBienes> filtroDeBienes;
    
    //private OngBean selectedOng;   
	
	
	public List<DeBienes> getFiltroDeBienes() {
		return filtroDeBienes;
	}

	public void setFiltroDeBienes(List<DeBienes> filtroDeBienes) {
		this.filtroDeBienes = filtroDeBienes;
	}

	public Integer getCantidadDB() {
		return cantidadDB;
	}

	public void setCantidadDB(Integer cantidadDB) {
		this.cantidadDB = cantidadDB;
	}

	public Integer getCantidadDS() {
		return cantidadDS;
	}

	public void setCantidadDS(Integer cantidadDS) {
		this.cantidadDS = cantidadDS;
	}

	public Integer getCantidadDE() {
		return cantidadDE;
	}

	public void setCantidadDE(Integer cantidadDE) {
		this.cantidadDE = cantidadDE;
	}

	public DonacionesDeBienesBean getDeBienesBean() {
		return deBienesBean;
	}

	public void setDeBienesBean(DonacionesDeBienesBean deBienesBean) {
		this.deBienesBean = deBienesBean;
	}

	public ArrayList<DeBienes> getListaDeBienes() {
		return listaDeBienes;
	}

	public void setListaDeBienes(ArrayList<DeBienes> listaDeBienes) {
		this.listaDeBienes = listaDeBienes;
	}

	public ArrayList<DeServicios> getListaDeServicios() {
		return listaDeServicios;
	}

	public void setListaDeServicios(ArrayList<DeServicios> listaDeServicios) {
		this.listaDeServicios = listaDeServicios;
	}

	public ArrayList<Economica> getListaEconomica() {
		return listaEconomica;
	}

	public void setListaEconomica(ArrayList<Economica> listaEconomica) {
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
		
		
    	
    		
    		Collection<DeBienes> listaDB = new ArrayList<DeBienes>();
    		listaDB = manager.listaDeBienesEnTiempo(fechaInicio, fechaInicio);
			
    		Iterator<DeBienes> it = listaDB.iterator();
    		int i= 0;
    		while(it.hasNext())
    		{
    			DeBienes d = it.next();
    			listaDeBienes.add(i, new DeBienes(d.getUsuario(), d.getFechaRealizada(), d.getNombreItem(), d.getCantidad(), d.getOng()) );
    			i++;
    			
    		}
    		
			cantidadDB = listaDB.size();
			
			/*Collection<Economica> listaDE = new ArrayList<Economica>();
			listaDE = manager.listaDeEconomicaEnTiempo(fechaInicio, fechaFinal);
			
			cantidadDE = listaDE.size();
			
			Collection<DeServicios> listaDS = new ArrayList<DeServicios>();
			listaDS = manager.listaDeServiciosEnTiempo(fechaInicio, fechaFinal);
			cantidadDS = listaDS.size();*/
			
			
    	
   
	}
	///////////////////
	 
}