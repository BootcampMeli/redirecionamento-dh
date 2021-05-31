package com.digitalhouse.redirecionamento.resources.dto;

import java.io.Serializable;

public class LinkDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String url;
	
	public LinkDTO() {}

	public LinkDTO(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
