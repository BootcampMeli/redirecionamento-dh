package com.digitalhouse.redirecionamento.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Link {
	
	private Integer id;
	private String url;
	private Integer quantidadeAcessos;
	
	@JsonIgnore
	private boolean valido;

	public Link(Integer id,String url, boolean valido) {
		this.id = id;
		this.url = url;
		this.valido = valido;
		this.quantidadeAcessos = 0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeAcessos() {
		return quantidadeAcessos;
	}

	public void setQuantidadeAcessos(Integer quantidadeAcessos) {
		this.quantidadeAcessos = quantidadeAcessos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	public boolean isValid() {
		return this.valido;
	}
	
	public void addRedirecionamento() {
		this.quantidadeAcessos++;
	}
	
	
	

}
