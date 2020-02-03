package utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import be.quodlibet.boxable.line.LineStyle;

public class PDFGenerator {
	public void crearPDF(String name) {
	 PDDocument document = new PDDocument();    
     
     //Saving the document
     try {
    	File file = new File(name);
    	PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

    	PDPage Page = new PDPage();
    	PDPageContentStream contentStream = new PDPageContentStream(document, Page);
    	
    	contentStream.beginText(); 
         
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLineAtOffset(25, 500);

        contentStream.showText("Cliente");
        contentStream.showText("nombre"); 
        contentStream.showText("numero"); 
        contentStream.showText("telefono"); 
        contentStream.showText("direccion");
        contentStream.endText();
        contentStream.close();
        document.addPage( Page );
        PDPageContentStream cos = new PDPageContentStream(document, Page);
        
        float margin = 50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = Page.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = Page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;

        BaseTable table = new BaseTable(yPosition, yStartNewPage,bottomMargin, tableWidth, margin, document, Page, true, drawContent);
        Row<PDPage> headerRow = table.createRow(50);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(100, "Header");
        cell.setFont(fontBold);
        cell.setFontSize(20);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
        table.addHeaderRow(headerRow);

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(30, "black left plain");
        cell.setFontSize(15);
        cell = row.createCell(70, "black left bold");
        cell.setFontSize(15);
        cell.setFont(fontBold);

        row = table.createRow(20);
        cell = row.createCell(50, "red right mono");
        cell.setTextColor(Color.RED);
        cell.setFontSize(15);
        cell.setFont(fontMono);
        // horizontal alignment
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
        cell = row.createCell(50, "green centered italic");
        cell.setTextColor(Color.GREEN);
        cell.setFontSize(15);
        cell.setFont(fontItalic);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

        row = table.createRow(20);
        cell = row.createCell(40, "rotated");
        cell.setFontSize(15);
        // rotate the text
        cell.setTextRotated(true);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        // long text that wraps
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        // long text that wraps, with more line spacing
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        cell.setLineSpacing(2);

        table.draw();

        cos.close();



        document.addPage( Page );
    	
    	
    	
    	
    	
    	document.save(file);
		
		//Closing the document  
		document.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
