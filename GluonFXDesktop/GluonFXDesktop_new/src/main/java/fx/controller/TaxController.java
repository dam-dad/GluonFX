package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import hibernate.HibernateController;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import model.beans.InvoiceBean;
import model.beans.TaxBean;
import model.entities.Invoice;
import model.entities.Tax;

public class TaxController implements Initializable {

	@FXML
    private VBox view;

    @FXML
    private TableView<TaxBean> taxTable;

    @FXML
    private TableColumn<TaxBean, String> taxNameColumn;

    @FXML
    private TableColumn<TaxBean, Number> taxPercentageColumn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField percentageText;

    @FXML
    private TextArea descriptionArea;
    
    @FXML
    private JFXButton addTax;

    @FXML
    private JFXButton delTax;
    
    @FXML
    private Label percentageError;
    
    @FXML
    private Label nameError;
    
    @FXML
    private Label taxError;
    
    //Model
	
    private List<TaxBean> taxList = new ArrayList<TaxBean>();
    
    // hibernate
 	private HibernateController hibernate;
 	
 	//TaxBean
 	private TaxBean taxes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Ocultar labels de error
		
		taxError.setVisible(false);
		nameError.setVisible(false);
		percentageError.setVisible(false);
		
		//Table columns configuration
		taxNameColumn.setCellValueFactory(v -> v.getValue().taxIdProperty());
		taxPercentageColumn.setCellValueFactory(v -> v.getValue().percentageProperty());
		
		//Table 
		
		taxTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onTaxSelected(nv));
		
		//Actions
		
		addTax.setOnAction(evt -> onAddTax());
		delTax.setOnAction(evt -> onDelTax());
		
		//Listeners
		
		nameText.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(taxes != null) {
					if(newValue) {
						hibernate.update(taxes.getTax());
					}
				}
				
			}
		});
		
		percentageText.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(taxes != null) {
					if(newValue) {
						hibernate.update(taxes.getTax());
					}
				}
			}
		});
		
		descriptionArea.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(taxes != null) {
					if(newValue) {
						hibernate.update(taxes.getTax());
					}
				}
			}
		});
	}
	
	private void onDelTax() {
		if(taxes == null) {
			taxError.setText("Selecciona un impuesto para eliminarlo");
			taxError.setVisible(true);
		}else {
			hibernate.delete(taxes.getTax());
			taxError.setVisible(false);
			selectAllTaxes();
		}
		
	}

	private void onAddTax() {
		
		if(nameText.getText().isEmpty()) {
			nameError.setVisible(true);
		}else if(percentageText.getText().isEmpty()) {
			percentageError.setVisible(true);
			nameError.setVisible(false);
		}else {
			Tax tax = new Tax();
			
			tax.setId(taxList.get(taxList.size()-1).getId()+1);
			tax.setDescription(descriptionArea.getText());
			tax.setTaxId(nameText.getText());
			tax.setPercentage(Double.parseDouble(percentageText.getText()));
			
			hibernate.save(tax);
			selectAllTaxes();
			nameError.setVisible(false);
			percentageError.setVisible(false);
		}
		
	}

	private void onTaxSelected(TaxBean nv) {
		
		//Unbind
		
		try {
			Bindings.unbindBidirectional(nameText.textProperty(), taxes.taxIdProperty());
		}catch(Exception e) {}
		try {
			Bindings.unbindBidirectional(percentageText.textProperty(), taxes.percentageProperty());
		}catch(Exception e) {}
		try {
			Bindings.unbindBidirectional(descriptionArea.textProperty(), taxes.descriptionProperty());
		}catch(Exception e) {}
		
		//Asignación del nuevo Bean
		taxes = nv;
		
		//Bindings
		
		try {
			Bindings.bindBidirectional(nameText.textProperty(), taxes.taxIdProperty());
		}catch(Exception e) {}
		try {
			Bindings.bindBidirectional(percentageText.textProperty(), taxes.percentageProperty(), new NumberStringConverter());
		}catch(Exception e) {}
		try {
			Bindings.bindBidirectional(descriptionArea.textProperty(), taxes.descriptionProperty());
		}catch(Exception e) {}
		
	}

	public TaxController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaxesView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@SuppressWarnings("unchecked")
	public void selectAllTaxes() {

		taxList = new ArrayList<TaxBean>();

		List<Tax> list = hibernate.selectAll("Tax");

		for (Tax i : list) {
			taxList.add(new TaxBean(i));
		}
		taxTable.setItems(FXCollections.observableArrayList(taxList));

	}
	
	public void injectHibernate(HibernateController hibernate) {
		this.hibernate = hibernate;
	}
	
	public VBox getView() {
		return this.view;
	}

}
