package com.web.beans.reportes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import clienteutility.ClienteUtility;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.service.negocio.remote.AdministradorEBR;

@ManagedBean(name="ReporteDonacion")
@RequestScoped
public class ReporteDonacionesBean implements Serializable { 
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	@ManagedProperty("#{DonacionesDeBienesBean}")
	private DonacionesDeBienesBean deBienesBean;
	
	

	@ManagedProperty("#{DonacionDeServicioBean}")
	private DonacionDeServicioBean deServicios;
	
	

	@ManagedProperty("#{DonacionEconomica}")
	private DonacionEconomica deEconomica;
	
	public String TipoDonacion;

	private Date fechaInicio;
	private Date fechaFinal;
	
	private ArrayList<DeBienes> listaDeBienes = new ArrayList<DeBienes>();
	private ArrayList<DonacionDeServicioBean> listaDeServicios = new ArrayList<DonacionDeServicioBean>();
	private ArrayList<DonacionEconomica> listaEconomica =  new ArrayList<DonacionEconomica>();
    

	
	
	
	
	public DonacionDeServicioBean getDeServicios() {
		return deServicios;
	}

	public void setDeServicios(DonacionDeServicioBean deServicios) {
		this.deServicios = deServicios;
	}

	public DonacionEconomica getDeEconomica() {
		return deEconomica;
	}

	public void setDeEconomica(DonacionEconomica deEconomica) {
		this.deEconomica = deEconomica;
	}

	public List<DeServicios> getFiltroDeServicios() {
		return filtroDeServicios;
	}

	public void setFiltroDeServicios(List<DeServicios> filtroDeServicios) {
		this.filtroDeServicios = filtroDeServicios;
	}

	public List<Economica> getFiltroEconomica() {
		return filtroEconomica;
	}

	public void setFiltroEconomica(List<Economica> filtroEconomica) {
		this.filtroEconomica = filtroEconomica;
	}



	private List<DeBienes> filtroDeBienes;
	
	private List<DeServicios> filtroDeServicios;
	
	private List<Economica> filtroEconomica;
    
    //private OngBean selectedOng;   
	
	
	public List<DeBienes> getFiltroDeBienes() {
		return filtroDeBienes;
	}

	public void setFiltroDeBienes(List<DeBienes> filtroDeBienes) {
		this.filtroDeBienes = filtroDeBienes;
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


	public ArrayList<DonacionDeServicioBean> getListaDeServicios() {
		return listaDeServicios;
	}

	public void setListaDeServicios(
			ArrayList<DonacionDeServicioBean> listaDeServicios) {
		this.listaDeServicios = listaDeServicios;
	}

	public ArrayList<DonacionEconomica> getListaEconomica() {
		return listaEconomica;
	}

	public void setListaEconomica(ArrayList<DonacionEconomica> listaEconomica) {
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
    		
			
			
			Collection<Economica> listaDE = new ArrayList<Economica>();
			listaDE = manager.listaDeEconomicaEnTiempo(fechaInicio, fechaFinal);
			Iterator<Economica> it2 = listaDE.iterator();
    		int i2= 0;
    		
    		while(it2.hasNext())
    		{
    			Economica e = it2.next();
    			listaEconomica.add(i2, new DonacionEconomica(e.getUsuario(), e.getFechaRealizada(),e.getOng()));
    			
    			i2++;
    			
    		}
			
			
			
			
			
			
			Collection<DeServicios> listaDS = new ArrayList<DeServicios>();
			listaDS = manager.listaDeServiciosEnTiempo(fechaInicio, fechaFinal);
			Iterator<DeServicios> it3 = listaDS.iterator();
    		int i3 = 0;
    		while(it3.hasNext())
    		{
    			DeServicios d = it3.next();
    			listaDeServicios.add(i3, new DonacionDeServicioBean(d.getUsuario(), d.getFechaRealizada(), d.getAreaConocimient(), d.getCantidadHoras()));
    			i3++;
    			
    		}
			
			}
    	
   
	}
	///////////////////
	 
