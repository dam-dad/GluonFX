package fx.dialogs;

import entities.Product;
import fx.beans.InvoiceDetailBean;
import fx.beans.ProductBean;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class AddDetailDialogModel {
	
	private StringProperty productId = new SimpleStringProperty();
	private ListProperty<ProductBean> listProducts = new SimpleListProperty<ProductBean>(); 
	private ObjectProperty<ProductBean> productSelected = new SimpleObjectProperty<ProductBean>(); 
	private DoubleProperty cantidad = new SimpleDoubleProperty(); 
	private DoubleProperty stock = new SimpleDoubleProperty(); 
	private ObjectProperty<Image> imgProduct = new SimpleObjectProperty<Image>();
	public final StringProperty productIdProperty() {
		return this.productId;
	}
	
	public final String getProductId() {
		return this.productIdProperty().get();
	}
	
	public final void setProductId(final String productId) {
		this.productIdProperty().set(productId);
	}
	
	public final ListProperty<ProductBean> listProductsProperty() {
		return this.listProducts;
	}
	
	public final ObservableList<ProductBean> getListProducts() {
		return this.listProductsProperty().get();
	}
	
	public final void setListProducts(final ObservableList<ProductBean> listProducts) {
		this.listProductsProperty().set(listProducts);
	}
	
	public final ObjectProperty<ProductBean> productSelectedProperty() {
		return this.productSelected;
	}
	
	public final ProductBean getProductSelected() {
		return this.productSelectedProperty().get();
	}
	
	public final void setProductSelected(final ProductBean productSelected) {
		this.productSelectedProperty().set(productSelected);
	}
	
	public final DoubleProperty cantidadProperty() {
		return this.cantidad;
	}
	
	public final double getCantidad() {
		return this.cantidadProperty().get();
	}
	
	public final void setCantidad(final double cantidad) {
		this.cantidadProperty().set(cantidad);
	}
	
	public final DoubleProperty stockProperty() {
		return this.stock;
	}
	
	public final double getStock() {
		return this.stockProperty().get();
	}
	
	public final void setStock(final double stock) {
		this.stockProperty().set(stock);
	}
	
	public final ObjectProperty<Image> imgProductProperty() {
		return this.imgProduct;
	}
	
	public final Image getImgProduct() {
		return this.imgProductProperty().get();
	}
	
	public final void setImgProduct(final Image imgProduct) {
		this.imgProductProperty().set(imgProduct);
	}
	
	
	
	 
	
	

}
