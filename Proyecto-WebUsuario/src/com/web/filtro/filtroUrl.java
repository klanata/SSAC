package com.web.filtro;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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


import javax.servlet.http.HttpSession;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.service.negocio.remote.CatastrofeEBR;



/**
 * Servlet Filter implementation class filtroUrl
 */
@WebFilter("/*")

@ManagedBean(name="filtroUrl")
@SessionScoped
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
        // 
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// 
	}

	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
				  
			//obtengo la URL que ingresa un usuario
			String urlStr = req.getRequestURL().toString().toLowerCase();
			
			if(urlStr ==null){
				
				
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			
			
			
			 boolean noProteger = noProteger(urlStr);
			 
			  
			  //Si no requiere protecci�n contin�o normalmente.
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
				objetoCat.setAttribute("objetoCatastrofe", existeURL);
				//objetoCat.setAttribute("nombreCatastrofeUsuario",existeURL.getNombreEvento());
				ArrayList<Ong> listaOng= new ArrayList<Ong>();    
				Ong ong;
				Collection<Ong> setOng = existeURL.getOngs();
				Iterator<Ong > it = setOng.iterator();
		    	int i = 0;
		    	while(it.hasNext()){
				//for (int i=0; i<=res.size()-1; i++){    		
					ong = it.next();
					listaOng.add(ong);
		    	}
				
				objetoCat.setAttribute("idmongo", existeURL.getId());
				//objetoCat.setAttribute("descripcionCatastrofeUsuario", existeURL.getDescripcion());
				//objetoCat.setAttribute("pdfCatastrofeUsuario", existeURL.getImagenes());
				objetoCat.setAttribute("listaOngUsuario", listaOng);
				
				id = existeURL.getId();	

			
				res.sendRedirect(req.getContextPath() + "/Index.xhtml");
				
				return;
					
			  }///else { res.sendRedirect(req.getContextPath() + "/Error.xhtml");}
			 
				
				
			  //El recurso requiere protecci�n, pero el usuario ya est� logueado.
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
			*/ if (urlStr.endsWith("Index.xhtml") ||(urlStr.endsWith("agregarImagenPersona.xhtml")) || urlStr.endsWith("Error.xhtml") )
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
			
			
			//String nombreCatastrofe ;
			while(it.hasNext() && (encontre==false) )
			{
				Catastrofe c = it.next();
				String nombre = c.getNombreEvento().toLowerCase();
				
				String nombreCatastrofe = new String("http://localhost:8080/proyecto-webusuario/"+nombre +".xhtml");
				
				
				
				if( nombreCatastrofe.compareTo(urlStr)== 0)
				{ 
					 encontre = true;
					 catastrofe = c;
				}
			///	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logoCatastrofe",c.getLogo() ); 		
				
			}
			
			
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		
				  
		  return catastrofe;
		}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 
	}

}
