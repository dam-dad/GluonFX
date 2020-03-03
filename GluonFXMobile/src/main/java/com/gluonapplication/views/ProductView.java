package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Clase tipo View para product
 * @author moimah
 *
 */
public class ProductView {
    
	/**
	 * Carga la vista fxml de resources
	 * @return vista de product
	 */
    public View getView() {
        try {
            View view = FXMLLoader.load(ProductView.class.getResource("product.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
