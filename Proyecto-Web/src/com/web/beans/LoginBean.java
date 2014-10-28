package com.web.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.core.service.negocio.ServiciosSeguridad;



@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ServiciosSeguridad servicioSeguridad;
	
	private String txtLogin;
	private String txtPassword;
	
	public String getTxtLogin() {
		return txtLogin;
	}
	
	public void setTxtLogin(String txtLogin) {
		this.txtLogin = txtLogin;
		
	}
	public String getTxtPassword() {
		return txtPassword;
	}
	
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String ingresar_ON_CLICK(){
		
		String outcome = null;
		
		if(servicioSeguridad.existeUsuario(this.txtLogin, this.txtPassword)){
			outcome = "index.xhtml";
		}else{
			outcome = "error";
		}
		return outcome;
	}
	
	public void showInformationMessage(String m){
		
		System.out.println(m);
		
	}
	
	public void showErrorMessage(String e){
		
		System.out.println(e);
		
	}

}
