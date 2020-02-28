package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Clase tipo View para tax
 * @author moimah
 *
 */
public class TaxView {
    
	/**
	 * Carga la vista fxml de resources
	 * @return vista de tax
	 */
    public View getView() {
        try {
            View view = FXMLLoader.load(TaxView.class.getResource("tax.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
