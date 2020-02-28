package com.moimah.pdf;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.moimah.hibernate.spring.entities.Invoice;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Clase que genera documento pdf
 * 
 * 
 * @author blackbacil
 *
 */
public class PDF {
	
	public static final String JRXML_FILE = "/reports/factura.jrxml";
	public static final String PDF_FILE = "pdf/factura.pdf";
	
	/**
	 * Proporciona vista previa del pdf de la factura
	 * @param mainInvoice factura a generar pdf
	 * @throws JRException
	 */
	public static void vistaPrevia(Invoice mainInvoice) throws JRException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(Main.class.getResourceAsStream(JRXML_FILE));		
		
		// Pasamos por parametros los datos que son de 1 dimension
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("precioSin", mainInvoice.getPrice());
		parameters.put("iva", mainInvoice.getTax().getPercentage());
		parameters.put("impuestos", mainInvoice.getTaxTotal());
		parameters.put("totalPagar", mainInvoice.getPriceTaxesIncluded());
		parameters.put("eName", mainInvoice.getCompany().getName());
		parameters.put("eId", mainInvoice.getCompany().getCompanyId());
		parameters.put("ePhone", mainInvoice.getCompany().getPhone());
		parameters.put("eEmail", mainInvoice.getCompany().getEmail());
		parameters.put("eAddress", mainInvoice.getCompany().getAddress());
		parameters.put("eCity", mainInvoice.getCompany().getCity());
		parameters.put("cName", mainInvoice.getCustomer().getName());
		parameters.put("cId", mainInvoice.getCustomer().getCustomerId());
		parameters.put("cPhone", mainInvoice.getCustomer().getPhone());
		parameters.put("cEmail", mainInvoice.getCustomer().getEmail());
		parameters.put("cAddress", mainInvoice.getCustomer().getAddress());
		parameters.put("cCity", mainInvoice.getCustomer().getCity());
		parameters.put("id", mainInvoice.getInvoiceNumber());
		parameters.put("pago", mainInvoice.getPayMethod().getDescription());
		parameters.put("impue", mainInvoice.getTax().getTaxId());
		
		// generamos el informe con  los parametros mas una lista de objetos que en este caso seran el detalle
        JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(FacturaDataProvider.getPdf(mainInvoice)));
        
        // visualiza el informe generado
        JasperViewer.viewReport(jasperPrint);
        
        
	}
	
	
	/**
	 * Gera fichero pdf a partir de una factura 
	 * @param mainInvoice factura a generar pdf
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public static File generarPdf(Invoice mainInvoice) throws JRException, IOException {
		
		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(Main.class.getResourceAsStream(JRXML_FILE));

		// Pasamos por parametros los datos que son de 1 dimension
		Map<String, Object> parameters = new HashMap<String, Object>();
		try {
		parameters.put("precioSin", mainInvoice.getPrice());
		parameters.put("iva", mainInvoice.getTax().getPercentage());
		parameters.put("impuestos", mainInvoice.getTaxTotal());
		parameters.put("totalPagar", mainInvoice.getPriceTaxesIncluded());
		parameters.put("eName", mainInvoice.getCompany().getName());
		parameters.put("eId", mainInvoice.getCompany().getCompanyId());
		parameters.put("ePhone", mainInvoice.getCompany().getPhone());
		parameters.put("eEmail", mainInvoice.getCompany().getEmail());
		parameters.put("eAddress", mainInvoice.getCompany().getAddress());
		parameters.put("eCity", mainInvoice.getCompany().getCity());
		parameters.put("cName", mainInvoice.getCustomer().getName());
		parameters.put("cId", mainInvoice.getCustomer().getCustomerId());
		parameters.put("cPhone", mainInvoice.getCustomer().getPhone());
		parameters.put("cEmail", mainInvoice.getCustomer().getEmail());
		parameters.put("cAddress", mainInvoice.getCustomer().getAddress());
		parameters.put("cCity", mainInvoice.getCustomer().getCity());
		parameters.put("id", mainInvoice.getInvoiceNumber());
		parameters.put("pago", mainInvoice.getPayMethod().getDescription());
		parameters.put("impue", mainInvoice.getTax().getTaxId());
		

		// generamos el informe con  los parametros mas una lista de objetos que en este caso seran el detalle
        JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(FacturaDataProvider.getPdf(mainInvoice)));
        
        // exporta el informe a un fichero PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, PDF_FILE);
        return new File(PDF_FILE);
        // Abre el archivo PDF generado con el programa predeterminado del sistema
		//Desktop.getDesktop().open(new File(PDF_FILE));
		}catch (Exception e) {
			new Exception("error por falta de parametros");
		}
		return null;
	}



}