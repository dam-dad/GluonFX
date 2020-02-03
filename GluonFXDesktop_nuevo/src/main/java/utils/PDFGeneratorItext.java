package utils;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Invoice;

public class PDFGeneratorItext {
	public void crearPDF(String name,Invoice invoice) {
		Document document = new Document();
        
        try{
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
             
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);                
             
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            table.addCell("Concepto");
            table.addCell("Unidades");
            table.addCell("Precio_Unidad");
            table.addCell("Precio");
             
            table.addCell(invoice.getConceptInvoices().get(0).getDescription());
            table.addCell("1.00");
            table.addCell(invoice.getConceptInvoices().get(0).getPrice().toString());
            table.addCell(invoice.getConceptInvoices().get(0).getPrice().toString());
            
            for(int i =0; i<invoice.getInvoiceDetails().size();i++) {
            	table.addCell(invoice.getInvoiceDetails().get(i).getProduct().getName());
            	table.addCell(invoice.getInvoiceDetails().get(i).getQuantity().toString());
            	table.addCell(invoice.getInvoiceDetails().get(i).getPriceUnit().toString());
            	table.addCell(invoice.getInvoiceDetails().get(i).getPrice().toString());
            }
            table.addCell("");
        	table.addCell("");
        	table.addCell("");
        	table.addCell(invoice.getPrice().toString());
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
             
             
            // Agregamos la tabla al documento            
            document.add(table);
             
            document.close();
             
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
	}

}
