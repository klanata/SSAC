package com.web.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.core.service.negocio.remote.UsuarioEBR;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;


@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioEBR servicioSeguridad;
	
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

	public String ingresar_ON_CLICK(ActionEvent event){		
		String outcome = null;	
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
		if(servicioSeguridad.existeUsuario(this.txtLogin, MD5(this.txtPassword))){
			loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido/a", txtLogin);
			outcome = "success";
		}else{
			loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Nick o password incorrecto.");
			outcome = "failure";
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
		return outcome;
	}
	
	private String MD5(String input) {

		String md5 = null;

		if(null == input) return null;

		try {

			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return md5;
	}
	
	public void showInformationMessage(String m){
		
		System.out.println(m);
		
	}
	
	public void showErrorMessage(String e){
		
		System.out.println(e);
		
	}

}
