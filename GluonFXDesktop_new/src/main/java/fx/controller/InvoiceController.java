package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import fx.dialogs.AddDetailDialogController;
import fx.dialogs.AddDetailDialogModel;
import model.entities.Company;
import model.entities.Customer;
import model.entities.Invoice;
import model.entities.InvoiceDetail;
import model.entities.Product;
import model.beans.CompanyBean;
import model.beans.CustomerBean;
import model.beans.InvoiceBean;
import model.beans.InvoiceDetailBean;
import model.beans.ProductBean;
import model.beans.TaxBean;
import hibernate.HibernateController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;


public class InvoiceController implements Initializable {
	Company DEFAULT_COMPANY;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@FXML
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
	private Label productsTotalLbl;

	@FXML
	private Label taxLbl;

	@FXML
	private Label totalLbl;

	@FXML
	private JFXComboBox<TaxBean> taxCombo;

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
	private ComboBox<CustomerBean> clientSelectCombo;

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
	private TableView<InvoiceDetailBean> productsTable;

	@FXML
	private TableColumn<InvoiceDetailBean, ProductBean> columnProduct;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnUds;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnPrice;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnSubtotal;

	@FXML
	private TableColumn<InvoiceDetailBean,?> columnActions;
	
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

	/*
	 * Every change call this method for update all the UI
	 */
	public void updateContent() {
		selectAllInvoices();
		selectAllCompanys();
		selectAllCustomers();
		selectAllProducts();

	}

	private void selectAllCompanys() {
		listCompanies = new ArrayList<CompanyBean>();

		List<Company> list = hibernate.selectAll("Company");
		
		DEFAULT_COMPANY= list.get(0);

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

		clientSelectCombo.setItems(FXCollections.observableArrayList(listCustomer));

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

	}

	/*
	 * Listener to detect the invoice selected and update the view
	 */
	private void onInvoiceSelected(InvoiceBean nv) {

		masterInvoiceBean = nv;

		try {

			try {

				// Set everything at start position
				dateTxt.setText(masterInvoiceBean.getInvoiceDate().toString());
				invoiceIDTxt.setText("");
				totalLbl.setText("0");
				taxLbl.setText("0");
				taxPercentageLbl.setText("0");
				totalLbl.setText("0");
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
				
				try {
					taxLbl.textProperty().unbindBidirectional(masterInvoiceBean.taxTotalProperty());
				} catch (Exception e) {
				}
				try {
					totalLbl.textProperty().unbindBidirectional(masterInvoiceBean.priceTaxesIncludedProperty());
				} catch (Exception e) {
				}
				try {
					taxPercentageLbl.textProperty().unbindBidirectional(masterInvoiceBean.getTax().percentageProperty());
				} catch (Exception e) {
				}
				try {
					productsTable.itemsProperty().unbindBidirectional(masterInvoiceBean.invoiceDetailsProperty());
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
					taxLbl.textProperty().unbindBidirectional(masterInvoiceBean.taxTotalProperty());
				} catch (Exception e) {
				}
				try {
					conceptArea.textProperty().unbindBidirectional(masterInvoiceBean.getConceptInvoices().get(0).getDescription());
				} catch (Exception e) {
				}
				try {
					Bindings.unbindBidirectional(priceTxt.textProperty(),masterInvoiceBean.getConceptInvoices().get(0).priceProperty());
				} catch (Exception e) {
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// Bindings
			
			try {
				invoiceIDTxt.textProperty().bindBidirectional(masterInvoiceBean.invoiceNumberProperty());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(productsTotalLbl.textProperty(), masterInvoiceBean.priceProperty(), new NumberStringConverter());
				
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(totalLbl.textProperty(), masterInvoiceBean.priceTaxesIncludedProperty(), new NumberStringConverter());	
				
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(taxLbl.textProperty(), masterInvoiceBean.taxTotalProperty(), new NumberStringConverter());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(taxPercentageLbl.textProperty(), masterInvoiceBean.getTax().percentageProperty(), new NumberStringConverter());
			} catch (Exception e) {
			}
			try {
				taxCombo.valueProperty().bindBidirectional(masterInvoiceBean.taxProperty());
			} catch (Exception e) {
			}
			try {
				productsTable.itemsProperty().bindBidirectional(masterInvoiceBean.invoiceDetailsProperty());
			} catch (Exception e) {
			}

			try {
				conceptArea.textProperty().bindBidirectional(masterInvoiceBean.getConceptInvoices().get(0).descriptionProperty());
				Bindings.bindBidirectional(priceTxt.textProperty(), masterInvoiceBean.getConceptInvoices().get(0).priceProperty(),new NumberStringConverter());
			} catch (Exception e) {
			}

			try {
				clientSelectCombo.getSelectionModel().select(masterInvoiceBean.getCustomer());
				masterInvoiceBean.customerProperty().bind(clientSelectCombo.getSelectionModel().selectedItemProperty());
			} catch (Exception e) {
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

		if (productsTable.getSelectionModel().getSelectedItem() != null) {

			int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Eliminar detalle");
			alert.setHeaderText("Va a eliminar el datalle seleccionado");

			alert.setContentText("¿Está seguro de ello?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				InvoiceDetail invoiceDetail = productsTable.getSelectionModel().getSelectedItem().getInvoiceDetail();

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

}
