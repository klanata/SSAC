package com.web.filtro;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


/**
 * Servlet Filter implementation class filtroUrl
 */
@WebFilter("/*")
public class filtroUrl implements Filter {

    /**
     * Default constructor. 
     */
    public filtroUrl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
				  
			//obtengo la URL que ingresa un usuario
			String urlStr = req.getRequestURL().toString().toLowerCase();
			
		String index="http://localhost:8080/proyecto-webusuario/index.xhtml" ;	
		if(urlStr.compareTo(index)==0)
		{
			  res.sendRedirect(req.getContextPath() + "/Index.xhtml");
			     return;
			
			
		}
		else{
			Catastrofe existeURL = existeCatastrofeURL(urlStr);
		  //si no es null obtengo los datos de la catastrofe y los guardo en variables
			if (existeURL != null) {
			  
			  //copio los datos de la catastrofe X
			  String logoCatastrofe = existeURL.getLogo();
			  Long id= existeURL.getId();
			  String  idCatastrofe = id.toString();
			  String  cssCatastrofe = existeURL.getCss();
			  String descripcionCatastrofe = existeURL.getDescripcion();
			  
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logoCatastrofe",logoCatastrofe ); 		
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idCatastrofe",idCatastrofe );
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cssCatastrofe",cssCatastrofe);
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("descripcionCatastrofe",descripcionCatastrofe);
			 
			 // ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			//	handler.performNavigation("Index?faces-redirect=true");	
			  res.sendRedirect(req.getContextPath() + "/Index.xhtml");
		     return;
		  }
		 
		  else{
		//	 ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			//handler.performNavigation("Index?faces-redirect=true");	
			res.sendRedirect(req.getContextPath() + "/Error.xhtml");
			 return;
			  
		  }
		}
			//chain.doFilter(request, response); 
	}

	private Catastrofe existeCatastrofeURL(String urlStr) {

		
		CatastrofeEBR manager = null;		
		boolean encontre= false;
		Context context = null;
		Catastrofe catastrofe = null;		
		FacesMessage message = null; 
		
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
		
		try {
			Collection<Catastrofe> listaCatastrofes= manager.listaCatastrofes();
			Iterator<Catastrofe> it = listaCatastrofes.iterator();
			
			System.out.print("nombre url" + urlStr);
			//String nombreCatastrofe ;
			while(it.hasNext() && (encontre==false) )
			{
				Catastrofe c = it.next();
				String nombre = c.getNombreEvento();
				
				String nombreCatastrofe = new String("http://localhost:8080/proyecto-webusuario/"+nombre +".xhtml");
				System.out.print("nombre concatenado" + nombreCatastrofe);
				System.out.print("nombre url" + urlStr);
				
				
				if( nombreCatastrofe.compareTo(urlStr)== 0)
				{ 
					 encontre = true;
					 catastrofe = c;
				}
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				  
		  return catastrofe;
		}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
