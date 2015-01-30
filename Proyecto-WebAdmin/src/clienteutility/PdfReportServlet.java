package clienteutility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reporte.pdf")
public class PdfReportServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public PdfReportServlet() {
        super();
        // 
    }
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String fileString = (String) request.getSession().getAttribute("fileString");
    	
    	if ((fileString == null) || (fileString == "")) {    
    		//System.out.println("SC_NOT_FOUND ");
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
    	
    	String jboss = System.getenv("JBOSS_HOME");
    	
    	String path = jboss + "\\Proyecto\\imagenes.war\\" + fileString;
        
        //System.out.println("path " + path); 
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();           
        baos = convertPDFToByteArrayOutputStream(path);
    	
        byte[] content = baos.toByteArray();
    	
    	//byte[] content = (byte[]) request.getSession().getAttribute("reportBytes");    	
    	if (content == null) {
    		 content = new byte[0];    		  
        }     	 
    	
        response.setContentType("application/pdf");               
        response.setContentLength(content.length);                
        response.getOutputStream().write(content);
        //request.getSession().removeAttribute("reportBytes");
        //request.getSession().removeAttribute("fileString");
    	
    }
    
    
    private static ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

	    InputStream inputStream = null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try {

		        inputStream = new FileInputStream(fileName);
		
		        byte[] buffer = new byte[1024];
		        baos = new ByteArrayOutputStream();
		
		        int bytesRead;
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            baos.write(buffer, 0, bytesRead);
		        }
		
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (inputStream != null) {
		            try {
		                inputStream.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return baos;
    }
        

}
