package fx.controller;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.ResourceBundle;

import hibernate.HibernateController;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.Company;
import entities.Customer;
import entities.Invoice;
import entities.Product;
import fx.beans.CompanyBean;
import fx.beans.CustomerBean;
import fx.beans.InvoiceBean;
import fx.beans.ProductBean;
import hibernate.HibernateController;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.css.Size;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
<<<<<<< HEAD
=======
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
import javafx.scene.layout.VBox;

public class InvoiceController implements Initializable {
	
	//FXML

	@FXML
<<<<<<< HEAD
    private VBox view;
	
	@FXML
	private TableView<?> billSelector;

	@FXML
	private Button addBill;

	@FXML
	private Button deleteBill;

	@FXML
	private ComboBox<String> bsName;

	@FXML
	private Label bsID;

	@FXML
	private Label bsDir;

	@FXML
	private Label bsPC;

	@FXML
	private Label bsEmail;

	@FXML
	private Label bsTlp;

	@FXML
	private TextField billFld;

	@FXML
	private ComboBox<String> cltName;

	@FXML
	private Label cltID;

	@FXML
	private Label cltDir;

	@FXML
	private Label cltPC;

	@FXML
	private Label cltEmail;

	@FXML
	private Label cltTlp;

	@FXML
	private TextField dateFld;

	@FXML
	private TextArea conceptArea;

	@FXML
	private TextField priceFld;

	@FXML
	private TableView<?> productsTbl;

	@FXML
	private Button addProduct;

	@FXML
	private Button delProducts;

	@FXML
	private ComboBox<String> statusCmb;

	@FXML
	private TableView<?> taxTable;

	@FXML
	private TextField totalFld;
	
	//hibernate
	HibernateController hibernate;
	
	//model
	
	//Strings
	StringProperty billTxt = new SimpleStringProperty();
	StringProperty dateTxt = new SimpleStringProperty();
	StringProperty conceptTxt = new SimpleStringProperty();
	StringProperty priceTxt = new SimpleStringProperty();

	//ObservableList y list para las tablas, hacer los objetos
	
	private ObservableList<Object> billObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> billLst =  new SimpleListProperty<Object>(billObs);
	private ObservableList<Object> productsObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> productsLst =  new SimpleListProperty<Object>(productsObs);
	private ObservableList<Object> taxObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> taxLst =  new SimpleListProperty<Object>(taxObs);
	
	//ArrayList para Combobox, a rellenar con los estados
	
	ObservableList<String> statusArray = FXCollections.observableArrayList("Ejemplo1", "Ejemplo2");
	ObservableList<String> bsArray = FXCollections.observableArrayList("Empresa1", "Empresa2");
	ObservableList<String> cltArray = FXCollections.observableArrayList("Cliente1", "Cliente2");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Darle valores base a los combobox
		
		statusCmb.setItems(statusArray);
		bsName.setItems(bsArray);
		cltName.setItems(cltArray);
		
		//Bindings
		
		billFld.textProperty().bindBidirectional(billTxt);
		dateFld.textProperty().bindBidirectional(dateTxt);
		conceptArea.textProperty().bindBidirectional(conceptTxt);
		priceFld.textProperty().bindBidirectional(priceTxt);
=======
	private VBox view;

	@FXML
	private GridPane tableGrid;

	@FXML
	private TableView<InvoiceBean> tableInvoices;

	@FXML
	private TableColumn<InvoiceBean, String> columnNumeroFactura;

	@FXML
	private TableColumn<InvoiceBean, LocalDate> columnFecha;

	@FXML
	private TableColumn<InvoiceBean, CustomerBean> columnCliente;

	@FXML
	private JFXButton addInvoice;

	@FXML
	private JFXButton delInvoice;

	@FXML
	private JFXButton leftHideBttn;

	@FXML
	private ImageView leftHideImage;

	@FXML
	private TextField invoiceIDTxt;

	@FXML
	private TextField dateTxt;

	@FXML
	private TextField nameClientTxt;

	@FXML
	private TextField idClientTxt;

	@FXML
	private TextField directionclientTxt;

	@FXML
	private TextField cpClientTxt;

	@FXML
	private TextField emailClientTxt;

	@FXML
	private TextField tlpClientTxt;

	@FXML
	private ImageView companyIcon;

	@FXML
	private TextArea conceptArea;

	@FXML
	private JFXTextField priceTxt;

	@FXML
	private JFXButton addProductBttn;

	@FXML
	private TableView<?> productsTable;

	@FXML
	private Label productsTotalLbl;

	@FXML
	private Label taxLbl;

	@FXML
	private Label totalLbl;

	@FXML
	private JFXComboBox<?> taxCombo;

	@FXML
	private Label taxPercentageLbl;

	@FXML
	private JFXButton rightHideBttn;

	@FXML
	private ImageView rightHideImage;

	@FXML
	private VBox configurationBox;

	@FXML
	private JFXButton invoiceStatusBttn;

	@FXML
	private VBox invoiceStatusBox;

	@FXML
	private JFXCheckBox createdCheck;

	@FXML
	private JFXCheckBox pendingCheck;

	@FXML
	private JFXCheckBox chargedCheck;

	@FXML
	private JFXCheckBox overdueCheck;

	@FXML
	private JFXButton clientSelectBttn;

	@FXML
	private VBox clientSelectBox;

	@FXML
	private ComboBox<String> clientSelectCombo;

	@FXML
	private JFXButton paymentMethodBttn;

	@FXML
	private VBox paymentMethodBox;

	@FXML
	private JFXCheckBox cashCheck;

	@FXML
	private JFXCheckBox bankTransferCheck;

	@FXML
	private JFXCheckBox creditCheck;

	@FXML
	private JFXButton generatePDFBttn;

	@FXML
	private TableColumn<?, ?> columnProduct;

	@FXML
	private TableColumn<?, ?> columnUds;

	@FXML
	private TableColumn<?, ?> columnPrice;

	@FXML
	private TableColumn<?, ?> columnSubtotal;

	@FXML
	private TableColumn<?, ?> columnActions;
	
    @FXML
    private HBox leftHideBox;

	// hibernate
	private HibernateController hibernate;

	// Main Lists
	private List<InvoiceBean> listInvoices = new ArrayList<InvoiceBean>();
	private List<CompanyBean> listCompanies = new ArrayList<CompanyBean>();
	private List<CustomerBean> listCustomer = new ArrayList<CustomerBean>();
	private List<ProductBean> listProducts = new ArrayList<ProductBean>();
	private List<String> listCustomersNames = new ArrayList<String>();

	// Beans and atributes for current invoice
	private InvoiceBean masterInvoiceBean; // The most important bean he is the fucking master
	
	private int hideLeftController = 0; // Si esá a 0 no está oculto, si está en 1 está oculto
	private int hideRightController = 0;

//	private List<InvoiceDetailBean> listInvoiceDetails = new ArrayList<InvoiceDetailBean>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Insert list of status
		insertListOfStatus();

		// table invoice configuration
		columnNumeroFactura.setCellValueFactory(v -> v.getValue().invoiceNumberProperty());
		columnFecha.setCellValueFactory(v -> v.getValue().invoiceDateProperty());
		columnCliente.setCellValueFactory(v -> v.getValue().customerProperty());
		
		

		// table invoice listener
		tableInvoices.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onInvoiceSelected(nv));

		// table details configuration;
		columnProduct.setCellValueFactory(v -> v.getValue().productProperty());
		columnUds.setCellValueFactory(v -> v.getValue().quantityProperty());
		columnPrice.setCellValueFactory(v -> v.getValue().priceUnitProperty());
		columnSubtotal.setCellValueFactory(v -> v.getValue().priceProperty());
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

		// Buttons actions

		leftHideBttn.setOnAction(evt -> onLeftHideAction());

	}

	private void onLeftHideAction() {
		if(hideLeftController == 0) {
			tableGrid.setMaxWidth(125);
			leftHideBox.setMaxWidth(170);
			leftHideImage.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			hideLeftController = 1;
		}else {
			tableGrid.setMaxWidth(Double.MAX_VALUE);
			leftHideBox.setMaxWidth(-1);
			leftHideImage.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			hideLeftController = 0;
		}
		
	}
	
	public InvoiceController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvoiceView.fxml"));
		loader.setController(this);
		loader.load();
	}

	// injectors
	public void injectHibernate(HibernateController hibernate) {
		this.hibernate = hibernate;
	}
<<<<<<< HEAD
	
	
	
	//getters y setters
	
	public VBox getView() {
		return view;
	}

	public final StringProperty billTxtProperty() {
		return this.billTxt;
	}
	

	public final String getBillTxt() {
		return this.billTxtProperty().get();
	}
	

	public final void setBillTxt(final String billTxt) {
		this.billTxtProperty().set(billTxt);
	}
	

	public final StringProperty dateTxtProperty() {
		return this.dateTxt;
	}
	

	public final String getDateTxt() {
		return this.dateTxtProperty().get();
=======

	/*
	 * Every change call this method for update all the UI
	 */
	public void updateContent() {
		selectAllInvoices();
		selectAllCompanys();
		selectAllCustomers();
		selectAllProducts();

	}

	/*
	 * Make a query for get all invoices from bbdd and update the corresponding list
	 */
	public void selectAllInvoices() {

		listInvoices = new ArrayList<InvoiceBean>();

		List<Invoice> list = hibernate.selectAll("Invoice");

		for (Invoice i : list) {
			listInvoices.add(new InvoiceBean(i));
		}

		tableInvoices.setItems(FXCollections.observableArrayList(listInvoices));

	}

	/*
	 * Make a query for get all companies from bbdd and update the corresponding
	 * list
	 */

	public void selectAllCustomers() {

		listCustomer = new ArrayList<CustomerBean>();

		List<Customer> list = hibernate.selectAll("Customer");

		for (Customer c : list) {
			listCustomer.add(new CustomerBean(c));
			listCustomersNames.add(new CustomerBean(c).getName());
		}

		clientSelectCombo.setItems(FXCollections.observableArrayList(listCustomersNames));

	}

	/*
	 * Make a query for get all customers from bbdd and update the corresponding
	 * list
	 */
	public void selectAllProducts() {

		listProducts = new ArrayList<ProductBean>();

		List<Product> list = hibernate.selectAll("Product");

		for (Product p : list) {
			listProducts.add(new ProductBean(p));
		}

>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
	}

<<<<<<< HEAD
	public final void setDateTxt(final String dateTxt) {
		this.dateTxtProperty().set(dateTxt);
	}
	

	public final StringProperty conceptTxtProperty() {
		return this.conceptTxt;
=======
	/*
	 * Listener to detect the invoice selected and update the view
	 */
	private void onInvoiceSelected(InvoiceBean nv) {

		masterInvoiceBean = nv;

		calculatePrices();

		try {

			try {

				// Set everything at start position
				dateTxt.setText(LocalDate.now().toString());
				invoiceIDTxt.setText("");
				totalLbl.setText("0");
				taxLbl.setText("0");
				taxPercentageLbl.setText("0");
				totalLbl.setText("0");
				lblTaxID.setText("Impuesto");
				conceptArea.setText("");
				priceTxt.setText("");

				// Unbindings
				try {
					dateTxt.textProperty().unbindBidirectional(masterInvoiceBean.invoiceDateProperty());
				} catch (Exception e) {
				}
				try {
					invoiceIDTxt.textProperty().unbindBidirectional(masterInvoiceBean.invoiceNumberProperty());
				} catch (Exception e) {
				}
				try {
					productsTotalLbl.textProperty().unbindBidirectional(masterInvoiceBean.priceProperty());
				} catch (Exception e) {
				}
				}
				try {
					taxLbl.textProperty().unbindBidirectional(masterInvoiceBean.taxTotalProperty());
				} catch (Exception e) {
				}
				try {
					totalLbl.textProperty()
							.unbindBidirectional(masterInvoiceBean.priceTaxesIncludedProperty());
				} catch (Exception e) {
				}
				try {
					taxPercentageLbl.textProperty().unbindBidirectional(masterInvoiceBean.getTax().percentageProperty());
				} catch (Exception e) {
				}
				try {
					tableDetails.itemsProperty().unbindBidirectional(masterInvoiceBean.invoiceDetailsProperty());
				} catch (Exception e) {
				}
				try {
					masterInvoiceBean.companyProperty().unbind();
				} catch (Exception e) {
				}
				try {
					masterInvoiceBean.customerProperty().unbind();
				} catch (Exception e) {
				}
				try {
					masterInvoiceBean.statusProperty().unbind();
				} catch (Exception e) {
				}
				try {
					lblTaxID.textProperty().unbindBidirectional(masterInvoiceBean.getTax().taxIdProperty());
				} catch (Exception e) {
				}
				try {
					conceptArea.textProperty().unbindBidirectional(masterInvoiceBean.getconcep);
				} catch (Exception e) {
				}
				try {
					Bindings.unbindBidirectional(txtPrecio.textProperty(),
							masterInvoiceBean.getConceptId().priceProperty());
				} catch (Exception e) {
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// Bindings
			try {
				dateInvoice.valueProperty().bindBidirectional(masterInvoiceBean.invoiceDateProperty());
			} catch (Exception e) {
			}
			try {
				txtInvoiceNumber.textProperty().bindBidirectional(masterInvoiceBean.invoiceNumberProperty());
			} catch (Exception e) {
			}
			try {
				txtTotal.decimalProperty().bindBidirectional(masterInvoiceBean.priceProperty());
			} catch (Exception e) {
			}
			try {
				txtPrice.decimalProperty().bindBidirectional(masterInvoiceBean.priceProperty());
			} catch (Exception e) {
			}
			try {
				txtPriceTaxIncluded.decimalProperty().bindBidirectional(masterInvoiceBean.priceTaxesIncludedProperty());
			} catch (Exception e) {
			}
			try {
				txtTaxTotal.decimalProperty().bindBidirectional(masterInvoiceBean.taxTotalProperty());
			} catch (Exception e) {
			}
			try {
				txtPercent.decimalProperty().bindBidirectional(masterInvoiceBean.getTax().percentageProperty());
			} catch (Exception e) {
			}
			try {
				lblTaxID.textProperty().bindBidirectional(masterInvoiceBean.getTax().taxIdProperty());
			} catch (Exception e) {
			}
			try {
				tableDetails.itemsProperty().bindBidirectional(masterInvoiceBean.invoiceDetailsProperty());
			} catch (Exception e) {
			}

			try {
				txtConcept.textProperty().bindBidirectional(masterInvoiceBean.getConceptId().descriptionProperty());
				Bindings.bindBidirectional(txtPrecio.textProperty(), masterInvoiceBean.getConceptId().priceProperty(),
						new NumberStringConverter());
			} catch (Exception e) {
			}

			try {
				cmbCompany.getSelectionModel().select(masterInvoiceBean.getCompany());
				masterInvoiceBean.companyProperty().bind(cmbCompany.getSelectionModel().selectedItemProperty());
			} catch (Exception e) {
			}

			try {
				cmbCustomer.getSelectionModel().select(masterInvoiceBean.getCustomer());
				masterInvoiceBean.customerProperty().bind(cmbCustomer.getSelectionModel().selectedItemProperty());
			} catch (Exception e) {
			}

			try {
				cmbStatus.getSelectionModel().select(masterInvoiceBean.getStatus());
				masterInvoiceBean.statusProperty().bind(cmbStatus.getSelectionModel().selectedIndexProperty());
			} catch (Exception e) {
			}

			// calculatePrices();

		} catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
	}
	

<<<<<<< HEAD
	public final String getConceptTxt() {
		return this.conceptTxtProperty().get();
	}
	

	public final void setConceptTxt(final String conceptTxt) {
		this.conceptTxtProperty().set(conceptTxt);
	}
	

	public final StringProperty priceTxtProperty() {
		return this.priceTxt;
	}
	
=======
	/*
	 * Insert a list of status in the comboStatus
	 */
	public void insertListOfStatus() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Creada");
		list.add("Pendiente");
		list.add("Cobrada");
		list.add("Atrasada");

		cmbStatus.setItems(FXCollections.observableArrayList(list));
	}

	public void updateView() {

	}

	/*
	 * Calculate prices for the current invoice
	 */
	public void calculatePrices() {

		System.out.println(
				"CALCULANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

		double totalWoTax = 0;
		double totalTax = 0;
		double totalTaxIncl = 0;

		try {
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

	public final String getPriceTxt() {
		return this.priceTxtProperty().get();
	}
	

	public final void setPriceTxt(final String priceTxt) {
		this.priceTxtProperty().set(priceTxt);
	}
	

	public final ListProperty<Object> billLstProperty() {
		return this.billLst;
	}
	

	public final ObservableList<Object> getBillLst() {
		return this.billLstProperty().get();
	}
	

	public final void setBillLst(final ObservableList<Object> billLst) {
		this.billLstProperty().set(billLst);
	}
	

	public final ListProperty<Object> productsLstProperty() {
		return this.productsLst;
	}
	

	public final ObservableList<Object> getProductsLst() {
		return this.productsLstProperty().get();
	}
	

	public final void setProductsLst(final ObservableList<Object> productsLst) {
		this.productsLstProperty().set(productsLst);
	}
	

	public final ListProperty<Object> taxLstProperty() {
		return this.taxLst;
	}
	

<<<<<<< HEAD
	public final ObservableList<Object> getTaxLst() {
		return this.taxLstProperty().get();
=======
			}

		} catch (Exception e) {

		}

>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
	}
	

<<<<<<< HEAD
	public final void setTaxLst(final ObservableList<Object> taxLst) {
		this.taxLstProperty().set(taxLst);
	}
	
	
	
	
	
=======
	@FXML
	private void onActionAddDetail(ActionEvent event) {

		InvoiceDetail invoiceDetail = new InvoiceDetail();

		if (tableInvoices.getSelectionModel().getSelectedItem() != null) {

			int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();

			AddDetailDialogController dialog = new AddDetailDialogController(masterInvoiceBean.getInvoice());
			dialog.chargeProducts(listProducts);
			Optional<AddDetailDialogModel> result = dialog.showAndWait();

			double quantity = result.get().getCantidad();
			double priceUnit = result.get().getProductSelected().getProduct().getPrice();
			double price = quantity * priceUnit;
			invoiceDetail.setInvoice(masterInvoiceBean.getInvoice());
			invoiceDetail.setProduct(result.get().getProductSelected().getProduct());
			invoiceDetail.setPrice(price);
			invoiceDetail.setPriceUnit(priceUnit);
			invoiceDetail.setQuantity(quantity);

			hibernate.save(invoiceDetail);
			updateContent();

			tableInvoices.getSelectionModel().select(indexInvoiceFocused);

		}
	}

	@FXML
	private void onActionAddInvoice(ActionEvent event) {

	}

	@FXML
	private void onActionRemoveDetail(ActionEvent event) {

		if (tableDetails.getSelectionModel().getSelectedItem() != null) {

			int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Eliminar detalle");
			alert.setHeaderText("Va a eliminar el datalle seleccionado");

			alert.setContentText("¿Está seguro de ello?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				InvoiceDetail invoiceDetail = tableDetails.getSelectionModel().getSelectedItem().getInvoiceDetail();

				hibernate.delete(invoiceDetail);

				updateContent();

				tableInvoices.getSelectionModel().select(indexInvoiceFocused);

			} else {
				alert.close();
			}

		}

	}

	@FXML
	private void onActionRemoveInvoice(ActionEvent event) {

	}

	public VBox getView() {
		return view;
	}
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

}
