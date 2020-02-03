package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import hibernate.HibernateController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StartController implements Initializable{
	@FXML
	private VBox view;
    @FXML
    private ProgressIndicator progress;
    
    private HibernateController hibernate = new HibernateController();
    
    RootController rootController;
    
  //Tarea asincrona	
  	private Task<Void> tarea;
  
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		startTask();
		
	}
	
	
	public StartController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartView.fxml"));
		loader.setController(this);
		loader.load();
		
	}
	
	/**
	 * Metodo que ejecuta una tarea. 
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public Task<Void> executeTask() {
		 
	        Task task =  new Task() {
	            @Override
	            protected Object call() throws Exception {
	            		            	
	            	hibernate.start();
	            	rootController = new RootController(hibernate);
				
				return true;
			}
		};
		
		task.setOnSucceeded(e -> { 		
			
			//Si se ha cargado hibernate cargar la vista pricipal y cerrar la vista de progreso			
			
			Scene scene = new Scene(rootController.getRoot());
			Stage stage = new Stage();
			stage.setTitle("New Window");
			stage.setScene(scene);
			stage.show();
			
			 Stage window = (Stage) getView().getScene().getWindow();
			 window.close();
			

		});
		
		task.setOnFailed(e -> {
			//TODO mensaje de error
		});
	       
	        
	        return task; 
	    }
	
	
	public void startTask() {
		 
		   tarea = executeTask();
		    new Thread(tarea).start();        
		   
	}
	
	
	public VBox getView() {
		return view;
	}

}
