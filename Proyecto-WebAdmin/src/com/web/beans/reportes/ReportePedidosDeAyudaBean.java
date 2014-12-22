package com.web.beans.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;


import com.core.data.entites.PedidoDeAyuda;
import com.core.service.negocio.remote.AdministradorEBR;


@ManagedBean(name="reportePedidoAyuda")
@RequestScoped
public class ReportePedidosDeAyudaBean implements Serializable {

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		
		
		@ManagedProperty("#{PedidoAyudaBean}")
		private PedidoDeAyudaBean pedidoAyudaBean;
		private Date fechaInicio;
		private Date fechaFinal;
		
		private ArrayList<PedidoDeAyudaBean> lista = new ArrayList<PedidoDeAyudaBean>();
		
		private List<PedidoDeAyuda> filtro;
		
	    
		public PedidoDeAyudaBean getPedidoAyudaBean() {
			return pedidoAyudaBean;
		}
		public void setPedidoAyudaBean(PedidoDeAyudaBean pedidoAyudaBean) {
			this.pedidoAyudaBean = pedidoAyudaBean;
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


		public ArrayList<PedidoDeAyudaBean> getLista() {
			return lista;
		}


		public void setLista(ArrayList<PedidoDeAyudaBean> lista) {
			this.lista = lista;
		}



		public List<PedidoDeAyuda> getFiltro() {
			return filtro;
		}

	public void setFiltro(List<PedidoDeAyuda> filtro) {
			this.filtro = filtro;
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
			
			
	    	
	    		
	    		Collection<PedidoDeAyuda> listapedidos = new ArrayList<PedidoDeAyuda>();
	    		listapedidos = manager.listaPedidosAyuda(fechaInicio, fechaFinal);
				
	    		Iterator<PedidoDeAyuda> it = listapedidos.iterator();
	    		int i= 0;
	    		while(it.hasNext())
	    		{
	    			
	    			
	    			PedidoDeAyuda d = it.next();
	    			lista.add(i, new PedidoDeAyudaBean(d.getId(), d.getDescripcion(), d.getCoordenadasX(), d.getCoordenadasY(), d.getFechaPublicacion(),d.getCatastrofe(), d.getBajaLogica()));
	    			i++;
	    			
	    		}
	    		
				
				
				
				}
	    	
	   
		}
		///////////////////