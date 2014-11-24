package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="listaOngsBean")
@ViewScoped
public class ListaOngsBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OngBean> ongsBean = new ArrayList<OngBean>();    
    private List<OngBean> filtroOngBean;
    
    private OngBean selectedOng;
    private List<OngBean> selectedOngs;
    
    @ManagedProperty("#{ongBean}")
    private OngBean ongBean;
    
	
	@PostConstruct
    public void init() {
				
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEvento");
		System.out.println("El id del evento: " + idEventoString);										
		
	}

	
	//	------------------ Getter and setter methods ---------------------

	public ArrayList<OngBean> getOngsBean() {
		return ongsBean;
	}
	public void setOngsBean(ArrayList<OngBean> ongsBean) {
		this.ongsBean = ongsBean;
	}
	public OngBean getSelectedOng() {
		return selectedOng;
	}
	public void setSelectedOng(OngBean selectedOng) {
		this.selectedOng = selectedOng;
	}
	public List<OngBean> getFiltroOngBean() {
		return filtroOngBean;
	}
	public void setFiltroOngBean(List<OngBean> filtroOngBean) {
		this.filtroOngBean = filtroOngBean;
	}
	public List<OngBean> getSelectedOngs() {
		return selectedOngs;
	}
	public void setSelectedOngs(List<OngBean> selectedOngs) {
		this.selectedOngs = selectedOngs;
	}
	
			

}
