package com.gluonapplication;

import com.gluonapplication.views.InvoiceView;
import com.gluonapplication.views.ProductView;
import com.gluonapplication.views.TaxView;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase principal de timpo MobileApplication
 * @author moimah
 *
 */
public class GluonApplication extends MobileApplication {

    public static final String INVOICE_VIEW = HOME_VIEW;
    public static final String PRODUCT_VIEW = "Product View";
    public static final String TAX_VIEW = "Tax View";
    
    /**
     * Inicializa las vistas y las añade al Drawer
     */
    @Override
    public void init() {
    	
    	
        addViewFactory(INVOICE_VIEW, () -> new InvoiceView().getView());
        addViewFactory(PRODUCT_VIEW, () -> new ProductView().getView());
        addViewFactory(TAX_VIEW, () -> new TaxView().getView());

        DrawerManager.buildDrawer(this);
    }

    /**
     * Vincula estilos y iconos a la aplicacion, define resolucion inicial para escritorio
     */
    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);      

        scene.getStylesheets().add(GluonApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(GluonApplication.class.getResourceAsStream("/icon.png")));
        
        if(Platform.isDesktop()){
        	scene.getWindow().setWidth(800);
        	scene.getWindow().setHeight(800);
        }
    }
}
