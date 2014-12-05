package com.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.RescatistaEBR;


@ManagedBean(name="testingBean")
@SessionScoped
public class TestingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@PostConstruct
    public void init() {
    	
		RescatistaEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
			///VICTORIA CREATE EN BASE Y HARKODEA ACA LOS VALORES PARA PODER PROBAR LEVANTAR ESTADOrESCATSTA
			//PRIMERO CREATE EN LA BASE UN RESCATISTA EJEMPLO RESCATISTA (1, PEDRO,....)
			//CREATE PEDIDO DE AYUDA (10,PEDIDO,....)
			//LLAMA A Entra a esta pagina TestingBean.xhtml y ahi te carga en la base de datos en la tabla 
			Long idRescatista = new Long(1);
			Long idPEdidoAyuda = new Long(10);
			manager.asignarRescatistaPedidoDeAyuda(idRescatista, idPEdidoAyuda);
		
 					
	}

}
