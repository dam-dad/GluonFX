package com.gluonapplication;

import static com.gluonapplication.GluonApplication.INVOICE_VIEW;
import static com.gluonapplication.GluonApplication.PRODUCT_VIEW;
import static com.gluonapplication.GluonApplication.TAX_VIEW;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.scene.image.Image;

/**
 * Clase de tipo DrawerManager
 * @author moimah
 *
 */
public class DrawerManager {

	
	/**
	 * Vincula todas las vistas a un cajon/menu
	 * @param app aplicacionn actual
	 */
    public static void buildDrawer(MobileApplication app) {
        NavigationDrawer drawer = app.getDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("DAD",
                "Administration",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);
        
        final Item invoiceItem = new ViewItem("Facturas", MaterialDesignIcon.HOME.graphic(), INVOICE_VIEW, ViewStackPolicy.SKIP);
        final Item productItem = new ViewItem("Productos", MaterialDesignIcon.SHOP.graphic(), PRODUCT_VIEW);
        final Item taxItem = new ViewItem("Impuestos", MaterialDesignIcon.GAVEL.graphic(), TAX_VIEW);
        
        drawer.getItems().addAll(invoiceItem, productItem, taxItem);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Salir", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
    }
}