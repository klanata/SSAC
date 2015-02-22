package clienteutility;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/css/*")
public class CSSServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

    private String cssPath;

    
    public void init() throws ServletException {
        
        this.cssPath = "/wildfly-8.0.0.Final/Proyecto/imagenes.war";
               
    }
   

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String requestedCss = request.getPathInfo();
        
        if (requestedCss == null) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        File css = new File(cssPath, URLDecoder.decode(requestedCss, "UTF-8"));
        
        if (!css.exists()) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
       
        String contentType = getServletContext().getMimeType(css.getName());
        
        if (contentType == null) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(css.length()));
        
        Files.copy(css.toPath(), response.getOutputStream());
    }

}
