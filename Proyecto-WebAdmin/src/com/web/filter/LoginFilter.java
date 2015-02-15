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
		   // Obtengo el bean que representa el usuario desde el scope sesi�n
		  LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
		  
		  //Proceso la URL que est� requiriendo el cliente
		  String urlStr = req.getRequestURL().toString().toLowerCase();
		  boolean noProteger = noProteger(urlStr);
		 
		  
		  //Si no requiere protecci�n contin�o normalmente.
		  if (noProteger(urlStr)) {
		    chain.doFilter(request, response);
		    return;
		  }
		  
		  //El usuario no est� logueado
		  //if (loginBean == null || !loginBean.estaLogeado()) {			 
		  //  res.sendRedirect(req.getContextPath() + "/home.xhtml");
		  //  return;
		  //}
		 
		  //El recurso requiere protecci�n, pero el usuario ya est� logueado.
		  chain.doFilter(request, response);
	}

	private boolean noProteger(String urlStr) {

		/*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protecci�n. Sin duda que habr�a que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuraci�n o algo que no requiera
		 * compilaci�n.
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
		
	}

}
