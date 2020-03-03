package com.gluonapplication.views;

import java.util.Optional;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.converter.InputStreamInputConverter;
import com.gluonhq.connect.converter.JsonInputConverter;
import com.gluonhq.connect.converter.JsonOutputConverter;
import com.gluonhq.connect.converter.OutputStreamOutputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import com.model.beans.Tax;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase de tipo controller/presenter para tax
 * @author moimah
 *
 */
public class TaxPresenter {

	@FXML
    private View secondary;

    @FXML
    private Tab tabMainTax;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnNewTax;

    @FXML
    private Button btnModifyTax;

    @FXML
    private Button btnDeleteTax;

    @FXML
    private TableView<Tax> tableTaxes;

    @FXML
    private TableColumn<Tax, String> columnTaxId;

    @FXML
    private TableColumn<Tax, Number> columnTaxPercentage;

    @FXML
    private TableColumn<Tax, String> columnTaxDescription;

    @FXML
    private Tab tabDialog;

    @FXML
    private TextField txtTaxId;

    @FXML
    private TextField txtTaxDescription;

    @FXML
    private TextField txtTaxPercentage;

    @FXML
    private Button btnSaveTax;

    @FXML
    private Button btnCancelTax;

    //model
    private TaxModel model = new TaxModel();
    //tasa seleccionada
    private Tax masterTax;
  //Opcion 0 nuevo / guardar 1
    private int option = 0; // 0 new, 1 update
    

	/**
	 *  Inizializacion, configuracion inicial, bindeos y listeners
	 */	
    public void initialize() {
    	
    	
        secondary.setShowTransitionFactory(BounceInRightTransition::new);       
        
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Impuestos");
              
            }
        });
        
        
        tableTaxes.itemsProperty().bindBidirectional(model.listTaxesProperty());
        model.taxSelectedProperty().bind(tableTaxes.getSelectionModel().selectedItemProperty());
        
        columnTaxId.setCellValueFactory(v -> v.getValue().taxIdProperty());
        columnTaxPercentage.setCellValueFactory(v -> v.getValue().percentageProperty());
        columnTaxDescription.setCellValueFactory(v -> v.getValue().descriptionProperty());
        
        tableTaxes.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onTaxSelected(nv));
        
        txtTaxId.textProperty().bindBidirectional(model.taxIdProperty());
        txtTaxPercentage.textProperty().bindBidirectional(model.percentageProperty());
        txtTaxDescription.textProperty().bindBidirectional(model.descriptionProperty());
        
		btnSaveTax.disableProperty()
		.bind(model.taxIdProperty().isNull().or(model.descriptionProperty().isNull())
				.or(model.descriptionProperty()
					.isNull())
					.or(model.percentageProperty().isNull()));
				
		tabDialog.getTabPane().getTabs().remove(tabDialog);

		try {
			updateContent();
		} catch (Exception e) {

		}
   
    }
    
    
    /**
	 * Actualiza los impuestos de la lista con llamada al metodo
	 * que realiza reques a bbdd
	 */
    private void updateContent() {
		
    	getAllTaxes();
		
	}


    /**
	 * Listener que detecta el impuesto seleccionado sobre la tableTaxes
	 * @param nv tax seleccionado
	 */
	private void onTaxSelected(Tax nv) {
		
		this.masterTax  = nv;
    	
	}
	
	
	/**
	 * Listener que responde a btnDeleteTax, comprueba que se ha seleccionado
	 * un impuesto, pide confirmacion y lo elimina
	 * @param event
	 */
	@FXML
    void onActionBtnDeleteTax(ActionEvent event) {
		
		if (masterTax != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Desea eliminar el impuesto: " + masterTax.getTaxId());

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				deleteTax(masterTax);
			} else {
				alert.close();
			}

		}
		
    }
	
	
	/**
	 * Listener que responde al btnModify, situa el tabDialog
	 * como principal, cambia el estado a modificando 1		
	 * @param event
	 */
    @FXML
    void onActionBtnModify(ActionEvent event) {

    	option = 1;
		if (masterTax != null) {
			tabDialog.setText("Modificar");
			tabRequest(masterTax);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Seleccione un producto");
			alert.showAndWait();

		}

    }

 

    
    /**
	 * Listener que responde al btnNewTax, situa el tabDialog
	 * como principal, cambia el estado a nuevo 0	
	 * @param event
	 */
	@FXML
	void onActionBtnNewTax(ActionEvent event) {

		option = 0;
		tabDialog.setText("Nuevo");
		tabRequest(new Tax());

	}

	
	/**
	 * Responde al btnSearchTax, se activa un cuadro de dialogo
	 * que recibe el codigo del impuesto, realiza busquedas.
	 * En caso de encontrar resultados se abre tab para modificar 
	 * @param event
	 */
    @FXML
    void onActionBtnSearchTax(ActionEvent event) {
    	
    	Tax tax = null; // Producto de busqueda

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Buscar producto");
		dialog.setGraphic(null);
		dialog.setHeaderText(null);
		dialog.setContentText("Introduce el codigo:");

		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {

			String question = result.get();

			for (Tax t : model.getListTaxes()) {

				if (t.getTaxId().contains(question)) {
					tax = t;
					break;
				} 

			}

			if (tax != null) {
				tableTaxes.getSelectionModel().select(tax);
				onActionBtnModify(null);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("No hay resultados");
				alert.showAndWait();
			}
		}


    }
    
    /**
	 * Oculta el tabDialog, volviendo al tabMainTax
	 * @param event
	 */
    @FXML
    void onActionbtnCancelTax(ActionEvent event) {
    	
		tabDialog.getTabPane().getTabs().add(tabMainTax);
		tabMainTax.getTabPane().getSelectionModel().select(tabMainTax);
		tabDialog.getTabPane().getTabs().remove(tabDialog);

    }

	/**
	 * Carga todos los datos del modelo sobre un objeto tax.
	 * Dependiendo de si es nuevo o update, llama al metodo correspondiente
	 * @param event
	 */
    @FXML
    void onActionbtnSaveTax(ActionEvent event) {
    	
    	tabDialog.getTabPane().getTabs().add(tabMainTax);
		tabMainTax.getTabPane().getSelectionModel().select(tabMainTax);
		tabDialog.getTabPane().getTabs().remove(tabDialog);

		Tax tax = null;

		if (option == 1) {

			tax = masterTax;
			tax.setTaxId(model.getTaxId());
			tax.setDescription(model.getDescription());
			tax.setPercentage(Double.valueOf(model.getPercentage()));
		
			
			updateTax(tax);	

		} else {

			tax = new Tax();
			tax.setTaxId(model.getTaxId());
			tax.setDescription(model.getDescription());
			tax.setPercentage(Double.valueOf(model.getPercentage()));
			
			insertTax(tax);
		}
			

    }
    
    /**
	 * Hace un llamamiento a la tab taxDialog, poniendola en primer plano, 
	 * la carga con el contenido recibido de impuesto
	 * @param tax impuesto a editar o null si es nuevo
	 */
	private void tabRequest(Tax tax) {
		
		//clean interface
		model.setTaxId("");
		model.setDescription("");
		model.setPercentage("");
		
		

		tabMainTax.getTabPane().getTabs().add(tabDialog);
		tabDialog.getTabPane().getSelectionModel().select(tabDialog);
		tabMainTax.getTabPane().getTabs().remove(tabMainTax);

		try {
			model.setTaxId(tax.getTaxId());
		} catch (Exception e) {
		}
		try {
			model.setPercentage(""+tax.getPercentage());
		} catch (Exception e) {
		}
		try {
			model.setDescription(tax.getDescription());
		} catch (Exception e) {
		}
		
	}
    
	/**
	 * Realiza consulta de todos los productos de la bbdd 
	 * y los aplica sobre el modelo.
	 */
    private void getAllTaxes() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("/tax")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Tax> result = DataProvider.retrieveList(restClient.createListDataReader(Tax.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
              
            	model.setListTaxes(result);
         
            }
        });        
		
	}
    
    
    /**
	 * Elimina un impuesto existente de la bbdd, y actualiza el contenido
	 * @param tax impuesto a eliminar
	 */
	private void deleteTax(Tax tax) {
		
		
		// create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("DELETE")
                .host("http://52.161.156.63:4132")                
                .path("tax/"+tax.getId())                   
                .contentType("application/json;charset=UTF-8");
                
        
        // create an observable object
        GluonObservableObject<Tax> result = new GluonObservableObject<>(); 
        result.set(tax);	
        
        //Create the converters
        OutputStreamOutputConverter<Tax> outputConverter = new JsonOutputConverter<>(Tax.class);
	    InputStreamInputConverter<Tax> inputConverter = new JsonInputConverter<>(Tax.class);      
	        
	    //Delete the object 
        DataProvider.removeObject(result, restClient.createObjectDataRemover(outputConverter, inputConverter));
        
        result.stateProperty().addListener((obs, ov, nv) -> {

			updateContent();

		});   
		
	}
    
    
	/**
	 * Inserta un nuevo impuesto en la bbdd y actualiza el contenido
	 * @param tax impuesto a insertar
	 */
	private void insertTax(Tax tax) {
		
	
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/tax")	            
	               .contentType("application/json");
	              
	      //Create the converters     	       
	       OutputStreamOutputConverter<Tax> outputConverter = new JsonOutputConverter<>(Tax.class);
	       InputStreamInputConverter<Tax> inputConverter = new JsonInputConverter<>(Tax.class);
	    	       
	       	       
	       //store object on the server
	       GluonObservableObject<Tax> result =  DataProvider.storeObject(tax, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {		
	    	   
				updateContent();		
								
			}); 
		     
	}
	
	
	/**
	 * Actualiza un impuesto existente y actualiza el contenido
	 * @param tax impuesto a actualizar
	 */
	private void updateTax(Tax tax) {
			
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("PUT")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/tax")	            
	               .contentType("application/json;charset=utf8");
	              
	      //Create the converters     	       
	       OutputStreamOutputConverter<Tax> outputConverter = new JsonOutputConverter<>(Tax.class);
	       InputStreamInputConverter<Tax> inputConverter = new JsonInputConverter<>(Tax.class);
	    	       
	       	       
	     //store object on the server
	       GluonObservableObject<Tax> result =  DataProvider.storeObject(tax, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {		
	    	   
				updateContent();						
				
				
			}); 
		     
}
    
    
    
    
}
