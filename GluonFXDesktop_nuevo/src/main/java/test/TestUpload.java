package test;

import java.io.File;

import entities.Product;
import utils.ProductUtil;

public class TestUpload {
	
	public static void main(String[] args) {
		
		File f = new File("imagen.PNG");
				
		Product p = new Product();
		p.setProductId("aaa001");
		p.setName("Prueba");
		p.setPrice(22.24);
		
		
		ProductUtil.uploadProduct(p);
		
		Product p2 = new Product();
		p2.setProductId("aaa002");
		p2.setName("Prueba2");
		p2.setPrice(69.99);
		
		ProductUtil.uploadProductWithImage(f.getAbsolutePath(),p2);
		
	}

}
