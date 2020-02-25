package fx.controller;

import java.awt.Desktop;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.animation.alert.CenterTransition;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import fx.decoder.CodingPDF;
import fx.decoder.PDFBase64;
import fx.dialogs.AddDetailDialogController;
import fx.dialogs.AddDetailDialogModel;
import model.entities.Company;
import model.entities.Customer;
import model.entities.Invoice;
import model.entities.InvoiceDetail;
import model.entities.PayMethod;
import model.entities.Product;
import model.entities.Tax;
import model.beans.CompanyBean;
import model.beans.CustomerBean;
import model.beans.InvoiceBean;
import model.beans.InvoiceDetailBean;
import model.beans.PayMethodBean;
import model.beans.PrimaryModel;
import model.beans.ProductBean;
import model.beans.TaxBean;
import hibernate.HibernateController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class InvoiceController implements Initializable {
	

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
	private VBox rightHideVBox;

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
	private JFXRadioButton createdRadius;

	@FXML
	private ToggleGroup statusToggle;

	@FXML
	private JFXRadioButton pendingRadius;

	@FXML
	private JFXRadioButton chargedRadius;

	@FXML
	private JFXRadioButton overdueRadius;

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
	private JFXRadioButton cashRadius;

	@FXML
	private ToggleGroup methodsToggle;

	@FXML
	private JFXRadioButton bankTransferRadius;

	@FXML
	private JFXRadioButton creditRadius;

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
	private TableColumn<InvoiceDetailBean, ?> columnActions;

	@FXML
	private HBox leftHideBox;

	@FXML
	private TextField invoiceStatus;

	@FXML
	private HBox rightHideBox;

	@FXML
	private VBox centerVBox;

	// hibernate
	private HibernateController hibernate;

	// Main Lists
	private List<InvoiceBean> listInvoices = new ArrayList<InvoiceBean>();
	private List<CustomerBean> listCustomer = new ArrayList<CustomerBean>();
	private List<ProductBean> listProducts = new ArrayList<ProductBean>();
	private List<TaxBean> listTax = new ArrayList<TaxBean>();
	private List<String> listCustomersNames = new ArrayList<String>();
	private List<PayMethod> listPayMethods = new ArrayList<PayMethod>();

	// Beans and atributes for current invoice
	private InvoiceBean masterInvoiceBean; // The most important bean he is the fucking master

	private boolean hideLeftController = true; // Si esá a 0 no está oculto, si está en 1 está oculto
	private boolean hideRightController = true;
	private boolean hideStatusController = true;
	private boolean hideClientController = true;
	private boolean hidePaymentController = true;
	
	//Properties
	
	private DoubleProperty totalProducts = new SimpleDoubleProperty();
	private DoubleProperty taxPrice = new SimpleDoubleProperty();
	
	//Stage
	private Stage stage;
	
	//Decoder
	PDFBase64 decoder = new PDFBase64();
	CodingPDF pdfCoded = new CodingPDF();

//	Utils
	Company DEFAULT_COMPANY;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

		leftHideBttn.setOnAction(event -> onLeftHideAction());
		rightHideBttn.setOnAction(event -> onRightHideAction());
		createdRadius.setOnAction(event -> onCreatedAction());
		pendingRadius.setOnAction(event -> onPendingAction());
		chargedRadius.setOnAction(event -> onChargedAction());
		overdueRadius.setOnAction(event -> onOverdueAction());
		invoiceStatusBttn.setOnAction(event -> onStatusAction());
		clientSelectBttn.setOnAction(event -> onClientAction());
		paymentMethodBttn.setOnAction(event -> onPaymentAction());
		generatePDFBttn.setOnAction(event -> onPDFAction());
		
		//Listener
		
		/*methodsToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		              Toggle old_toggle, Toggle new_toggle) {
		            if (methodsToggle.getSelectedToggle() != null) {
		              if(cashRadius.isSelected()) {
		            	  
		              }
		            }
		          }
		        });*/

	}

	//Generador de pdf
	@SuppressWarnings("static-access")
	private void onPDFAction() {
		try {
			
			if(masterInvoiceBean != null) {
				System.out.println(masterInvoiceBean.getId());
				
				HttpResponse<JsonNode> itemResponse = Unirest.get("http://188.76.34.188:4132/pdf/1")
						.asJson();
				if(itemResponse.getStatus() == 200) {
					JsonNode json = itemResponse.getBody();
					pdfCoded.setBase64(json.getObject().getString("base64"));
					pdfCoded.setInvoiceId(masterInvoiceBean.getInvoiceNumber());
				}
				
				
				FileChooser fChooser = new FileChooser();
				fChooser.setTitle("Selecciona la ubicación de tu PDF");
				fChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
				fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF(*.pdf)", "*.pdf"));
				File file = fChooser.showSaveDialog(stage);
				
				decoder.decoder(pdfCoded.getBase64(), file.toString());
				Desktop.getDesktop().open(file);
			}
		} catch (UnirestException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void getStage(Stage stage) {
		this.stage = stage;
	}

	private void onPaymentAction() {
		if(hidePaymentController) {
			paymentMethodBox.setVisible(false);
			paymentMethodBox.setManaged(false);
			hidePaymentController = false;
		}else {
			paymentMethodBox.setVisible(true);
			paymentMethodBox.setManaged(true);
			hidePaymentController = true;
		}
	}

	private void onClientAction() {
		if(hideClientController) {
			clientSelectBox.setVisible(false);
			clientSelectBox.setManaged(false);
			hideClientController = false;
		}else {
			clientSelectBox.setVisible(true);
			clientSelectBox.setManaged(true);
			hideClientController = true;
		}
	}

	private void onStatusAction() {
		if (hideStatusController) {
			invoiceStatusBox.setVisible(false);
			invoiceStatusBox.setManaged(false);
			hideStatusController = false;
		} else {
			invoiceStatusBox.setVisible(true);
			invoiceStatusBox.setManaged(true);
			hideStatusController = true;

		}
	}

	private void onRightHideAction() {
		if (hideRightController) {
			rightHideVBox.setMaxWidth(125);
			rightHideBox.setMaxWidth(170);
			rightHideBttn.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			invoiceStatusBttn.setFont(Font.font("System", 10));
			invoiceStatusBttn.setText("Estado de factura");
			clientSelectBttn.setFont(Font.font("System", 10));
			clientSelectBttn.setText("Selección cliente");
			paymentMethodBttn.setFont(Font.font("System", 10));
			paymentMethodBttn.setText("Métodos de pago");
			generatePDFBttn.setFont(Font.font("System", 10));
			generatePDFBttn.setText("Generar PDF");
			rightHideBox.setMinWidth(145);
			hideRightController = false;
		} else {
			rightHideBox.setMaxWidth(Double.MAX_VALUE);
			rightHideVBox.setMaxWidth(-1);
			rightHideBttn.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			invoiceStatusBttn.setFont(Font.font("System", 18));
			invoiceStatusBttn.setText("Estado de factura         ");
			clientSelectBttn.setFont(Font.font("System", 18));
			clientSelectBttn.setText("Selección cliente        ");
			paymentMethodBttn.setFont(Font.font("System", 18));
			paymentMethodBttn.setText("Métodos de pago      ");
			generatePDFBttn.setFont(Font.font("System", 18));
			generatePDFBttn.setText("Generar PDF               ");
			rightHideBox.setMinWidth(300);
			hideRightController = true;
		}
	}

	private void onOverdueAction() {
		masterInvoiceBean.setStatus(3);
		invoiceStatus.setText("Atrasada");
	}

	private void onChargedAction() {
		masterInvoiceBean.setStatus(2);
		invoiceStatus.setText("Cobrada");
	}

	private void onPendingAction() {
		masterInvoiceBean.setStatus(1);
		invoiceStatus.setText("Pendiente");
	}

	private void onCreatedAction() {
		masterInvoiceBean.setStatus(0);
		invoiceStatus.setText("Creada");
	}

	private void onLeftHideAction() {
		if (hideLeftController) {
			tableGrid.setMaxWidth(125);
			leftHideBox.setMaxWidth(170);
			leftHideImage.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			addInvoice.setFont(Font.font("System", 10));
			delInvoice.setFont(Font.font("System", 10));
			leftHideBox.setMinWidth(145);
			hideLeftController = false;
		} else {
			tableGrid.setMaxWidth(Double.MAX_VALUE);
			leftHideBox.setMaxWidth(-1);
			leftHideImage.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			addInvoice.setFont(Font.font("System", 18));
			delInvoice.setFont(Font.font("System", 18));
			leftHideBox.setMinWidth(300);
			hideLeftController = true;
		}

	}

	public InvoiceController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvoiceViewFinal.fxml"));
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
		selectAllTax();

	}

	@SuppressWarnings("unchecked")
	private void selectAllTax() {

		listTax = new ArrayList<TaxBean>();

		List<Tax> list = hibernate.selectAll("Tax");

		for (Tax i : list) {
			listTax.add(new TaxBean(i));
		}
		taxCombo.setItems(FXCollections.observableArrayList(listTax));

	}

	@SuppressWarnings("unchecked")
	private void selectAllCompanys() {
		new ArrayList<CompanyBean>();

		List<Company> list = hibernate.selectAll("Company");

		DEFAULT_COMPANY = list.get(0);

	}

	/*
	 * Make a query for get all invoices from bbdd and update the corresponding list
	 */
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public void selectAllCustomers() {

		List<PayMethod> list = hibernate.selectAll("PayMethod");

		for (PayMethod pm : list) {
			listPayMethods.add(pm);
		}

		clientSelectCombo.setItems(FXCollections.observableArrayList(listCustomer));

	}
	
	@SuppressWarnings("unchecked")
	public void selectAllPayMethods() {

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

		@SuppressWarnings("unchecked")
		List<Product> list = hibernate.selectAll("Product");

		for (Product p : list) {
			listProducts.add(new ProductBean(p));
		}

	}

	/*
	 * Listener to detect the invoice selected and update the view
	 */
	private void onInvoiceSelected(InvoiceBean nv) {

		try {

			try {

				
				
				

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
					taxPercentageLbl.textProperty()
							.unbindBidirectional(masterInvoiceBean.getTax().percentageProperty());
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
					conceptArea.textProperty()
							.unbindBidirectional(masterInvoiceBean.getConceptInvoices().get(0).getDescription());
				} catch (Exception e) {
				}
				try {
					Bindings.unbindBidirectional(priceTxt.textProperty(),
							masterInvoiceBean.getConceptInvoices().get(0).priceProperty());
				} catch (Exception e) {
				}
				try {
					Bindings.unbindBidirectional(masterInvoiceBean.taxProperty(), taxCombo.valueProperty());
				}catch(Exception e) {}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				nameClientTxt.textProperty().unbindBidirectional(masterInvoiceBean.getCustomer().nameProperty());
				idClientTxt.textProperty().unbindBidirectional(masterInvoiceBean.getCustomer().idProperty());
				directionclientTxt.textProperty()
						.unbindBidirectional(masterInvoiceBean.getCustomer().addressProperty());
				cpClientTxt.textProperty().unbindBidirectional(masterInvoiceBean.getCustomer().cityProperty());
				emailClientTxt.textProperty().unbindBidirectional(masterInvoiceBean.getCustomer().emailProperty());
				tlpClientTxt.textProperty().unbindBidirectional(masterInvoiceBean.getCustomer().phoneProperty());
			} catch (Exception e) {
			}try {
				Bindings.unbindBidirectional(taxPercentageLbl.textProperty(), totalProducts);
			}catch(Exception e) {}
			try {
				Bindings.unbindBidirectional(taxLbl.textProperty(), taxPrice);
			}catch(Exception e) {}

			// Asignación de la nueva factura

			masterInvoiceBean = nv;
			dateTxt.setText(masterInvoiceBean.getInvoiceDate().toString());
			UpdatePrice();
			
			// Esto es una solución temporal, una guarrada

			if (masterInvoiceBean.getStatus() == 0) {
				createdRadius.setSelected(true);
				invoiceStatus.setText("Creada");
			} else if (masterInvoiceBean.getStatus() == 1) {
				pendingRadius.setSelected(true);
				invoiceStatus.setText("Pendiente");
			} else if (masterInvoiceBean.getStatus() == 2) {
				chargedRadius.setSelected(true);
				invoiceStatus.setText("Cobrada");
			} else {
				overdueRadius.setSelected(true);
				invoiceStatus.setText("Atrasada");
			}
			
			if(masterInvoiceBean.getPayMethod().getId() == 0) {
				cashRadius.setSelected(true);
			}else if(masterInvoiceBean.getPayMethod().getId() == 0) {
				bankTransferRadius.setSelected(true);
			}else {
				creditRadius.setSelected(true);
			}

			// Bindings

			try {
				invoiceIDTxt.textProperty().bindBidirectional(masterInvoiceBean.invoiceNumberProperty());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(productsTotalLbl.textProperty(), masterInvoiceBean.priceProperty(),
						new NumberStringConverter());

			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(totalLbl.textProperty(), masterInvoiceBean.priceTaxesIncludedProperty(),
						new NumberStringConverter());

			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(taxLbl.textProperty(), masterInvoiceBean.taxTotalProperty(),
						new NumberStringConverter());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(taxPercentageLbl.textProperty(),
						masterInvoiceBean.getTax().percentageProperty(), new NumberStringConverter());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(masterInvoiceBean.taxProperty(), taxCombo.valueProperty());
			} catch (Exception e) {
			}
			try {
				productsTable.itemsProperty().bindBidirectional(masterInvoiceBean.invoiceDetailsProperty());
			} catch (Exception e) {
			}

			try {
				conceptArea.textProperty()
						.bindBidirectional(masterInvoiceBean.getConceptInvoices().get(0).descriptionProperty());
				Bindings.bindBidirectional(priceTxt.textProperty(),
						masterInvoiceBean.getConceptInvoices().get(0).priceProperty(), new NumberStringConverter());
			} catch (Exception e) {
			}

			try {
				clientSelectCombo.getSelectionModel().select(masterInvoiceBean.getCustomer());
				masterInvoiceBean.customerProperty().bind(clientSelectCombo.getSelectionModel().selectedItemProperty());
			} catch (Exception e) {
			}
			// Bindeo cliente
			try {
				nameClientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().nameProperty());
				idClientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().idProperty(),
						new NumberStringConverter());
				directionclientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().addressProperty());
				cpClientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().cityProperty());
				emailClientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().emailProperty());
				tlpClientTxt.textProperty().bindBidirectional(masterInvoiceBean.getCustomer().phoneProperty());
			} catch (Exception e) {
			}
			try {
				Bindings.bindBidirectional(taxPercentageLbl.textProperty(), totalProducts, 
						new NumberStringConverter());
			}catch(Exception e) {}
			try {
				Bindings.bindBidirectional(taxLbl.textProperty(), taxPrice, new NumberStringConverter());
			}catch(Exception e) {}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onClientChanged(CustomerBean nv) {
		nameClientTxt.setText(nv.getName());
		idClientTxt.setText(Integer.toString(nv.getId()));
		directionclientTxt.setText(nv.getAddress());
		cpClientTxt.setText(nv.getCountry());
		emailClientTxt.setText(nv.getEmail());
		tlpClientTxt.setText(nv.getPhone());
	}

	/*
	 * Insert a list of status in the comboStatus
	 */

	@FXML
	private void onActionAddDetail(ActionEvent event) {

		InvoiceDetail invoiceDetail = new InvoiceDetail();

		if (tableInvoices.getSelectionModel().getSelectedItem() != null) {

			int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();

			AddDetailDialogController dialog = new AddDetailDialogController(masterInvoiceBean.getInvoice());
			dialog.chargeProducts(listProducts);
			Optional<AddDetailDialogModel> result = dialog.showAndWait();

			try {
				double quantity = result.get().getCantidad();
				double priceUnit = result.get().getProductSelected().getProduct().getPrice();
				invoiceDetail.setInvoice(masterInvoiceBean.getInvoice());
				invoiceDetail.setProduct(result.get().getProductSelected().getProduct());
				invoiceDetail.setPrice(quantity * priceUnit);
				invoiceDetail.setPriceUnit(priceUnit);
				invoiceDetail.setQuantity(quantity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			hibernate.save(invoiceDetail);
			updateContent();

			tableInvoices.getSelectionModel().select(indexInvoiceFocused);

		}
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
	
	public void UpdatePrice() {
		double sum = 0;
		for(int i = 0; i < productsTable.getItems().size(); i++) {
			sum = sum + (columnUds.getCellData(i).doubleValue() * columnSubtotal.getCellData(i).doubleValue());
		}
		totalProducts.set(sum);
		
		/*taxPrice.set((masterInvoiceBean.getTax().getPercentage()/100)*totalProducts.get());*/
	}

	public VBox getView() {
		return view;
	}

}
