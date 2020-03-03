package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Clase tipo View para invoice
 * @author moimah
 *
 */
public class InvoiceView {

	/**
	 * Carga la vista fxml de resources
	 * @return vista de Invoice
	 */
    public View getView() {
        try {
            View view = FXMLLoader.load(InvoiceView.class.getResource("invoice.fxml"));           
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}

