package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="testingBean")
@SessionScoped
public class TestingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	public void CargarDatos()
	{
		/////////////////////////////////////////////////////////////////////////
		//CARGAR CONECCION CON CATASTROFE
		cargarCatastrofe();	
		
		/////////////////////////////////////////////////////////////////////////
		//CARGAR CONECCION CON RESCATISTA
		
		
		////////////////////////////////////////////////////////////////////////
		//CARGAR CONECCION CON PLAN DE RIESGO
		
	}
    public void cargarCatastrofe()
    {
    	CatastrofeEBR manager = null;		
		
		Context context = null;
		 
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
		double c = 10;
       		try {
				Long in= manager.ingesarCatastrofe("this.nombreEvento", "this.descripcion", "logo", c,c, true, false, "css", null, null, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
    	
    
    	}
	
    public void cargarRescatista()
    {
    	
    	
    }
    

}
