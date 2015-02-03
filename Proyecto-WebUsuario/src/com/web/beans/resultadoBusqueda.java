package com.web.beans;

public class resultadoBusqueda {
	
	private String idVideo;
	private String titleVideo;
	private String thumbnailVideo;
	private String imagenVideo;	

	//	------------------ Constructors  --------------------------------	   
	public resultadoBusqueda() {	
	}					
		
	public resultadoBusqueda(String idVideo, String titleVideo, String thumbnailVideo, String imagenVideo) {
		super();
		this.idVideo = idVideo;
		this.titleVideo = titleVideo;
		this.thumbnailVideo = thumbnailVideo;
		this.imagenVideo = imagenVideo;
	}



	//	------------------ Getter and setter methods ---------------------
	
	public String getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(String idVideo) {
		this.idVideo = idVideo;
	}
	public String getTitleVideo() {
		return titleVideo;
	}
	public void setTitleVideo(String titleVideo) {
		this.titleVideo = titleVideo;
	}
	public String getThumbnailVideo() {
		return thumbnailVideo;
	}
	public void setThumbnailVideo(String thumbnailVideo) {
		this.thumbnailVideo = thumbnailVideo;
	}
	public String getImagenVideo() {
		return imagenVideo;
	}
	public void setImagenVideo(String imagenVideo) {
		this.imagenVideo = imagenVideo;
	}
	

}
