package com.web.beans.reportes;
import javax.annotation.PostConstruct;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
 
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;

import org.primefaces.model.chart.ChartSeries;

import clienteutility.ClienteUtility;

import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.service.negocio.remote.AdministradorEBR;
 
@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel barModel;
   
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
   
 
    
	private BarChartModel initBarModel() {
    	
    	/*OBTENGO LA INSTANCIA DE CONECCION CON LA BASE*/
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
		
	
    		Date fechaInicio = new Date();
    		fechaInicio.getTime();
    		
    		/*
            int mes_ = calendar.get(Calendar.MONTH)+1;
            int dia_ = calendar.get(Calendar.DAY_OF_MONTH);*/
    		/*DONACIONES ECONOMICAS*/
			Collection<Economica> listaDE = new ArrayList<Economica>();
			listaDE = manager.listaDeEconomicaEnTiempo(fechaInicio, fechaInicio);
			Iterator<Economica> it2 = listaDE.iterator();
    		int y= 0;
    		//BigDecimal monto = new BigDecimal(0);
    		/*para formar la grafica*/
    		BarChartModel model = new BarChartModel();
    		ChartSeries donacionEconomica = new ChartSeries();
    		donacionEconomica.setLabel("Donacion Economica");
    		  //2011 2011 2012
    		int anioIni = 0;
    		int anioFin = 0;
    		Calendar calendar = Calendar.getInstance();
    		while(it2.hasNext())
    		{
    			Economica e = it2.next();
    			if(anioIni == 0)
    			{
    				calendar.setTime(e.getFechaRealizada());
    				anioIni = calendar.get(Calendar.YEAR);
    			    y++;

    			}else{
    				
    				calendar.setTime(e.getFechaRealizada());
    				anioFin = calendar.get(Calendar.YEAR);
    	        	if(anioFin == anioIni)
        			{
        				//aumento el contador
        				y++;
        				anioIni = anioFin;
        	            
        			}
        			
        			else {
        				//si los años no son iguales
        				String x = Integer.toString(anioIni);
        				//cargo valor
        				donacionEconomica.set(x, y);
        				y=1;
        			anioIni = anioFin;
        			}
    			}
				if((it2.hasNext()==false))
    			{
    				//si los años no son iguales
    				String x = Integer.toString(anioFin);
    				//cargo valor
    				donacionEconomica.set(x, y);
    				
    			}
				
    			
    		}

        /*DONACIONES DE BIENES*/
        Collection<DeBienes> listaDB = new ArrayList<DeBienes>();
		listaDB = manager.listaDeBienesEnTiempo(fechaInicio, fechaInicio);
		Iterator<DeBienes> itBienes = listaDB.iterator();
		y= 0;
		
		/*para formar la grafica*/
		
		ChartSeries donacionBienes = new ChartSeries();
		donacionBienes.setLabel("Donacion De Bienes");
		
		anioIni = 0;
		anioFin = 0;
		
		while(itBienes.hasNext())
		{
			DeBienes e = itBienes.next();
			if(anioIni == 0)
			{
				calendar.setTime(e.getFechaRealizada());
				anioIni = calendar.get(Calendar.YEAR);
			    y++;

			}else{
				
				calendar.setTime(e.getFechaRealizada());
				anioFin = calendar.get(Calendar.YEAR);
	        	if(anioFin == anioIni)
    			{
    				//aumento el contador
    				y++;
    				anioIni = anioFin;
    	            
    			}
    			
    			else {
    				//si los años no son iguales
    				String x = Integer.toString(anioIni);
    				//cargo valor
    				donacionBienes.set(x, y);
    				y=1;
    			anioIni = anioFin;
    			}
			}
			if((itBienes.hasNext()==false))
			{
				//si los años no son iguales
				String x = Integer.toString(anioFin);
				//cargo valor
				donacionBienes.set(x, y);
				
			}
			
			
		}
        
		/*DONACION DE SERVICIOS*/
        Collection<DeServicios> listaDDS = new ArrayList<DeServicios>();
        listaDDS = manager.listaDeServiciosEnTiempo(fechaInicio, fechaInicio);
		Iterator<DeServicios> itServicios = listaDDS.iterator();
		y= 0;
		
		/*para formar la grafica*/
		
		ChartSeries donacionDeServicios = new ChartSeries();
		donacionDeServicios.setLabel("Donacion De Servicios");
		
		anioIni = 0;
		anioFin = 0;
		
		while(itServicios.hasNext())
		{
			DeServicios e = itServicios.next();
			if(anioIni == 0)
			{
				calendar.setTime(e.getFechaRealizada());
				anioIni = calendar.get(Calendar.YEAR);
			    y++;

			}else{
				
				calendar.setTime(e.getFechaRealizada());
				anioFin = calendar.get(Calendar.YEAR);
	        	if(anioFin == anioIni)
    			{
    				//aumento el contador
    				y++;
    				anioIni = anioFin;
    	            
    			}
    			
    			else {
    				//si los años no son iguales
    				String x = Integer.toString(anioIni);
    				//cargo valor
    				donacionDeServicios.set(x, y);
    				y=1;
    			anioIni = anioFin;
    			}
			}
			if((itServicios.hasNext()==false))
			{
				//si los años no son iguales
				String x = Integer.toString(anioFin);
				//cargo valor
				donacionDeServicios.set(x, y);
				
			}
			
			
		}
		
		
        //CARGO LOS VALORES
        model.addSeries(donacionEconomica);
        model.addSeries(donacionBienes);
        model.addSeries(donacionDeServicios);
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
       
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Donaciones en el tiempo");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Año");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        ///se puede modificar segun cantidad datos de prueba
        yAxis.setMax(50);
    }
     
   
 
}