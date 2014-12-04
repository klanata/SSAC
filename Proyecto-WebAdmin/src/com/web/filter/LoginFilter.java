package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.controller.home.LoginBean;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		   // Obtengo el bean que representa el usuario desde el scope sesión
		  LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
		  
		  //Proceso la URL que está requiriendo el cliente
		  String urlStr = req.getRequestURL().toString().toLowerCase();
		  boolean noProteger = noProteger(urlStr);
		  System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");
		  
		  //Si no requiere protección continúo normalmente.
		  if (noProteger(urlStr)) {
		    chain.doFilter(request, response);
		    return;
		  }
		  
		  //El usuario no está logueado
		  if (loginBean == null || !loginBean.estaLogeado()) {
		    res.sendRedirect(req.getContextPath() + "/login.xhtml");
		    return;
		  }
		 
		  //El recurso requiere protección, pero el usuario ya está logueado.
		  chain.doFilter(request, response);
	}

	private boolean noProteger(String urlStr) {

		/*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protección. Sin duda que habría que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuración o algo que no requiera
		 * compilación.
		 */
		  if (urlStr.endsWith("home.xhtml"))
		    return true;
		  if (urlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  return false;
		}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
