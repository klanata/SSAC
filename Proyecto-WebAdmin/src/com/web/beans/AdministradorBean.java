package com.web.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.AdministradorEBR;

@ManagedBean(name="administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	

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
	
	public String LoginUsuario(){				
	
		
		AdministradorEBR manager = null;		
		
		Context context = null;
		String outcome = null;	
		FacesMessage message = null;
        
		 
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
		try{
		
			//Boolean exito=	manager.existeUsuario(txtLogin, MD5(txtPassword));
			Boolean exito=	manager.existeUsuario(txtLogin, txtPassword);
			
			if(exito==true){
				System.out.print("sucess");
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido/a", txtLogin);
				outcome = "success";
				
			}else{
				System.out.print("false");
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Nick o password incorrecto.");
				outcome = "failure";
				
			}
			
		return outcome;
		}catch(Exception e)
		{
			e.getMessage();
			return "failure";
		}
	}
	////
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
  

}
