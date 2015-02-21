package com.web.beans;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;

import com.core.service.negocio.remote.CatastrofeEBR;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import clienteYoutube.*;
import com.web.beans.resultadoBusqueda;

@ManagedBean(name = "busquedaYoutube")
@SessionScoped
public class Busqueda {

	private String busca;
	private List<SearchResult> searchResultList = new ArrayList<SearchResult>();
	private List<resultadoBusqueda> buscarResultadoList = new ArrayList<resultadoBusqueda>();
	private Long idCatastrofe;

	
	public String getBusqueda() {
		return busca;
	}

	public void setBusqueda(String busqueda) {
		this.busca = busqueda;
	}

	public List<SearchResult> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<SearchResult> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public List<resultadoBusqueda> getBuscarResultadoList() {
		return buscarResultadoList;
	}

	public void setBuscarResultadoList(
			List<resultadoBusqueda> buscarResultadoList) {
		this.buscarResultadoList = buscarResultadoList;
	}

	public Long getIdCatastrofe() {
		return idCatastrofe;
	}

	public void setIdCatastrofe(Long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}

	

	@PostConstruct
	public void init() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession) contexto.getExternalContext()
				.getSession(true);
		idCatastrofe = (Long) sesion.getAttribute("idmongo");

		System.out.print("esto obtiene de id catastrofe");
		System.out.print(idCatastrofe);
		//String res = this.registrar();
		

		if ((idCatastrofe == null) || (idCatastrofe == 00)) {
			System.out.println("id = o o null. ");
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext
					.getCurrentInstance().getApplication()
					.getNavigationHandler();
			handler.performNavigation("Error?faces-redirect=true");
		} else {
			CatastrofeEBR manager = null;
			Context context = null;
			// FacesMessage message = null;

			try {
				// 1. Obtaining Context
				context = ClienteUtility.getInitialContext();
				// 2. Generate JNDI Lookup name
				// String lookupName = getLookupName();
				// 3. Lookup and cast
				manager = (CatastrofeEBR) context
						.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
				
				if (idCatastrofe != 0) {
					// /////////////////////////////////////////
					Catastrofe c = new Catastrofe();
					// obtener catastrofe
					c = manager.buscaCatastrofePorId(idCatastrofe);
					// servicios
					//Aca obtendria los filtros de la catastrofe
					List<String> listaFiltros = manager.listarFiltrosDeCatastrofe(c.getId(), "Youtube");
					String filtro = new String();
					for(int i=0;i<listaFiltros.size();i++){
						filtro += " " + listaFiltros.get(i);
					}
				    
				    //filtro = "Inundacion Uruguay";
					System.out.println("Filtros de base: " + filtro);
				    this.setBusqueda(filtro);

				}
				String res = this.registrar();

			} 
			catch (NamingException e) {
				e.printStackTrace();
			}
			catch (Exception excep) {
				System.out.println("Excepción en ImagenesView: "
						+ excep.getMessage());
			}
		}
	}
	
	public String registrar() {
		
		searchResultList = Search.principal(busca);
		System.out.println("\n Desde el Busca Managed Beans \n");
		// Search.prettyPrint(searchResultList.iterator(), busca);

		Iterator<SearchResult> iteratorSearchResults = searchResultList
				.iterator();
		int i = 0;
		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Double checks the kind is video.
			if (rId.getKind().equals("youtube#video")) {
				Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails()
						.get("default");

				String url = "<iframe id=\"player\" type=\"text/html\" width=\"310\" height=\"185\"src=\"http://www.youtube.com/embed/"
						+ rId.getVideoId()
						+ "?enablejsapi=1&origin=http://example.com\"frameborder=\"0\"></iframe>";
				String imagen = "https://i.ytimg.com/vi/" + rId.getVideoId()
						+ "/default.jpg";

				buscarResultadoList.add(i,
						new resultadoBusqueda(rId.getVideoId(), singleVideo
								.getSnippet().getTitle(), url, imagen));
				i = i + 1;

				
			}
		}

		return "success";
	}
	
}
