package com.moimah.hibernate.spring.utils;

/**
 * Pojo que contiene la informacion de imagen de 
 * producto codificado en B64
 * 
 * 
 * @author moimah
 *
 */
public class ProductPNG {
	
	private String id;
	private String b64;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getB64() {
		return b64;
	}
	public void setB64(String b64) {
		this.b64 = b64;
	} 
	
	

}
