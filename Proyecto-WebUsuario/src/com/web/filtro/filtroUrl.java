package com.web.filtro;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.sun.org.apache.bcel.internal.generic.LLOAD;



/**
 * Servlet Filter implementation class filtroUrl
 */
@WebFilter("/*")

public class filtroUrl implements Filter , Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@ManagedProperty("#{catastrofeUsuarioBean}")
	//private CatastrofeBean catastrofeUsuarioBean = new CatastrofeBean();
	private String logoCatastrofe = "toma del filtro";
	private  Long id ;
	private String  idCatastrofe ;
	private String  cssCatastrofe ;//
	private String descripcionCatastrofe;
	
	
	/*
	
	public CatastrofeBean getCatastrofeUsuarioBean() {
		return catastrofeUsuarioBean;
	}

	public void setCatastrofeUsuarioBean(CatastrofeBean catastrofeUsuarioBean) {
		this.catastrofeUsuarioBean = catastrofeUsuarioBean;
	}*/

	public String getLogoCatastrofe() {
		return logoCatastrofe;
	}

	public void setLogoCatastrofe(String logoCatastrofe) {
		this.logoCatastrofe = logoCatastrofe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
			 boolean noProteger = noProteger(urlStr);
			 System.out.print(urlStr+"protegido: ");
			 System.out.print(noProteger);
			  
			  //Si no requiere protección continúo normalmente.
			  if (noProteger(urlStr)) {
				 // res.sendRedirect(req.getContextPath() + "/Index.xhtml");
			   chain.doFilter(request, response);
			    return;
			  }
			  
			  	String index="http://localhost:8080/proyecto-webusuario/index.xhtml" ;	
			/*	if(urlStr.compareTo(index)==0) 
				{
					 res.sendRedirect(req.getContextPath() + "/Index.xhtml");
					
				}
				*/
				Catastrofe existeURL = existeCatastrofeURL(urlStr);
				 //si no es null obtengo los datos de la catastrofe y los guardo en variables
				if (existeURL != null)  {
					
				HttpSession objetoCat= req.getSession(true);
				objetoCat.setAttribute("nombreCatastrofeUsuario",existeURL.getNombreEvento());
				
				
				res.sendRedirect(req.getContextPath() + "/Index.xhtml");
					
					
			  }//else { res.sendRedirect(req.getContextPath() + "/Error.xhtml");}
			 
			  //El recurso requiere protección, pero el usuario ya está logueado.
			  chain.doFilter(request, response);
		}
		
			//
	
	
	
	
	
	private boolean noProteger(String urlStr) {

		String index="http://localhost:8080/proyecto-webusuario/index.xhtml" ;	
		
		
		/*if(urlStr.compareTo(index)==0)
		{
			 // res.sendRedirect(req.getContextPath() + "/Index.xhtml");
			     return true;
			
			
		}
		
			Catastrofe existeURL = existeCatastrofeURL(urlStr);
		  //si no es null obtengo los datos de la catastrofe y los guardo en variables
			if (existeURL != null) {
			  
			  //copio los datos de la catastrofe X
			  logoCatastrofe = existeURL.getLogo().toString();
			  id= existeURL.getId();
			  idCatastrofe = id.toString();
			   cssCatastrofe = existeURL.getCss().toString();
			   descripcionCatastrofe = existeURL.getDescripcion().toString();
			 
			//  urlStr = index;
		     return true;
		  }
			*/ if (urlStr.endsWith("Index.xhtml") ||(urlStr.endsWith("agregarImagenPersona.xhtml")) )
			    return true;
			
			  if (urlStr.indexOf("/javax.faces.resource/") != -1)
				    return true;
		  
		 return false;
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
			///	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logoCatastrofe",c.getLogo() ); 		
				
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
