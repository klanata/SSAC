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

@WebServlet("/image/*")
public class ImagenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

    private String imagePath;

    
    public void init() throws ServletException {
        
        this.imagePath = "/wildfly-8.0.0.Final/Proyecto/imagenes.war";
               
    }
   

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String requestedImage = request.getPathInfo();
        
        if (requestedImage == null) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
        
        if (!image.exists()) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
       
        String contentType = getServletContext().getMimeType(image.getName());
        
        if (contentType == null || !contentType.startsWith("image")) {            
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        
        Files.copy(image.toPath(), response.getOutputStream());
    }

}
