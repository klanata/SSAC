package com.web.beans;
import java.util.ArrayList;  
import java.util.List;  
import javax.annotation.PostConstruct;  
  
public class GalleriaBean {  
  
    private List<String> images;  
  
    private String effect = "fade";  
  
    @PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
  
        for(int i=1;i<=2;i++) {  
            images.add("galleria" + i + ".jpg");  
        }  
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
           