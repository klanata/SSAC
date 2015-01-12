package com.web.beans.infoCatastrofe;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class FileDownloadView {
     
    private StreamedContent file;
     
    public FileDownloadView() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("C:/wildfly-8.0.0.Final/Proyecto/imagenes.war/pdfuno.pdf");
        file = new DefaultStreamedContent(stream, "imagen/pdf", "pdfuno.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}