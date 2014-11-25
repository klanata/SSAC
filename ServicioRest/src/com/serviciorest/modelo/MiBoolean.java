package com.serviciorest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class MiBoolean {
	


	private Boolean booleanValue;

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

}
