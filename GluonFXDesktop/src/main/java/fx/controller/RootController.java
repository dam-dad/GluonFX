package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hibernate.HibernateController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RootController implements Initializable  {
	@FXML
	private VBox root;
	@FXML
	private VBox mainView;
	@FXML
	private Button btnInvoice;
	@FXML
	private Button btnBudget;
	@FXML
	private Button btnWorkOrder;
	@FXML
	private Button btnProduct;
	@FXML
	private Button btnTax;
	@FXML
	private Button btnCustomer;
	@FXML
	private Button btnCompany;
	
	//Hibernate controller
	HibernateController hibernate = new HibernateController();
	
	
	//TODO subcontrollers
	InvoiceController subControllerInvoice;
	
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		
		//Subcontrollers
		
		try {
			
			//initialize subcontrollers
			subControllerInvoice = new InvoiceController();	
									
			//inject hibernate controller
			subControllerInvoice.injectHibernate(hibernate);
			
			//View at begin				
			mainView.getChildren().add(subControllerInvoice.getView());
			VBox.setVgrow(subControllerInvoice.getView(), Priority.ALWAYS);
			
			
			
		} catch (Exception e) {
			System.out.println("Error cargando las vistas");
		}
				
		
		//listeners
		btnInvoice.setOnAction(e->onClickButtonAction(0));
		btnBudget.setOnAction(e->onClickButtonAction(1));
		
		
	}
	
	
private void onClickButtonAction(int c) {
	
	mainView.getChildren().clear();
		
		switch (c) {
		
		case 0: 
			
			mainView.getChildren().add(subControllerInvoice.getView());
			VBox.setVgrow(subControllerInvoice.getView(), Priority.ALWAYS);
			
			//TODO inyect
			//TODO update content
			
			break;
		case 1:

			break;

		case 2:

			break;

		}
		
		//Todo 
	}
	
	public RootController(HibernateController hibernate) throws IOException {
		
		//Se inyecta el objeto hibernate provinente del StartController		
		this.hibernate = hibernate;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	
	public VBox getRoot() {
		return root;
	}
}
