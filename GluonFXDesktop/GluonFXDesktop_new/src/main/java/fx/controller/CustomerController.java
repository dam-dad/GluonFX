package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import hibernate.HibernateController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.beans.CustomerBean;
import model.beans.TaxBean;
import model.entities.Customer;
import model.entities.Tax;

public class CustomerController implements Initializable {
	
	//FXML
	
	@FXML
    private VBox view;

    @FXML
    private TableView<CustomerBean> clientCompanyTable;

    @FXML
    private TableColumn<CustomerBean, String> clientCompanyColumn;

    @FXML
    private Button addBill;

    @FXML
    private Button deleteBill;

    @FXML
    private Label tittleLbl;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField directionTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField phoneTxt;
    
    @FXML
    private JFXButton addBttn;

    @FXML
    private JFXButton delBttn;
    
    //model
    
    List<CustomerBean> customerList = new ArrayList<CustomerBean>();
    
    //hibernate
    
    HibernateController hibernate;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

	}

	public CustomerController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BSClientView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@SuppressWarnings("unchecked")
	public void selectAllCustomer() {

		customerList = new ArrayList<CustomerBean>();

		List<Customer> list = hibernate.selectAll("Customer");

		for (Customer i : list) {
			customerList.add(new CustomerBean(i));
		}
		clientCompanyTable.setItems(FXCollections.observableArrayList(customerList));

	}
	
	public void injectHibernate(HibernateController hibernate) {
		this.hibernate = hibernate;
	}
	
	public VBox getView() {
		return this.view;
	}
}
