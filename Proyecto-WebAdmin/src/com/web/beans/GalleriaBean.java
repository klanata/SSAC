package com.web.beans;
import java.util.ArrayList;  
import java.util.List;  

import javax.annotation.PostConstruct;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="galeriaBean")
@SessionScoped
public class GalleriaBean {  
  
    private List<String> images;  
  
    private String effect = "Shuffle";  
  
    @PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
        images.add("galeria1.jpg");  
        images.add("galeria3.jpg");
        images.add("galeria4.jpg");
        /*for(int i=1;i<=2;i++) {  
            images.add("galleria" + i + ".jpg");  
        }  */
    }  
  
    public List<String> getImages() {  
        return images;  
    }  
  
    public String getEffect() {  
        return effect;  
    }  
  
    public void setEffect(String effect) {  
        this.effect = effect;  
    }  
}  
           