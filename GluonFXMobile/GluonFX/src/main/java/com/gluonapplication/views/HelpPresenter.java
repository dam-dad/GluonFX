package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelpPresenter {

	@FXML
    private View primary;

    @FXML
    private WebView webView;
	   

    public void initialize() {
        primary.setShowTransitionFactory(BounceInRightTransition::new);
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Ayuda");
                
                
            }
        });
        
       WebEngine engine = webView.getEngine();
       engine.load("http://moimah.com/gluonfx/help.htm");
       
    
    }
    
   
}
