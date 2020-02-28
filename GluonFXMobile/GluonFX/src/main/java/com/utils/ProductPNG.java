package com.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Pojo de ProductPNG,
 * Objeto que contiene una imagen "png" en B64 y un id con el nombre del fichero
 * @author moimah
 *
 */
public class ProductPNG {

	private StringProperty id = new SimpleStringProperty();
	private StringProperty b64 = new SimpleStringProperty();
	
	
	public final StringProperty idProperty() {
		return this.id;
	}
	
	public final String getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final String id) {
		this.idProperty().set(id);
	}
	
	public final StringProperty b64Property() {
		return this.b64;
	}
	
	public final String getB64() {
		return this.b64Property().get();
	}
	
	public final void setB64(final String b64) {
		this.b64Property().set(b64);
	}
	
	
	
	
}
