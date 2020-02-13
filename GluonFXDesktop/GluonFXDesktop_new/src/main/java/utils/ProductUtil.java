package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.entities.Product;

public class ProductUtil{
	
	private final static String KEY_ID ="product_id";
	private final static String KEY_NAME ="name";
	private final static String KEY_DESCRIPTION ="description";
	private final static String KEY_PRICE ="price";
	private final static String KEY_IMAGE = "image";
	private final static String KEY_SERVER = "https://www.moimah.com/gluonfx/uploads/add.php";


	private static String encoder(String filePath) {

		String base64File = "";
		File file = new File(filePath);
		
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a file from file system
			byte fileData[] = new byte[(int) file.length()];
			imageInFile.read(fileData);
			base64File = Base64.getEncoder().encodeToString(fileData);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
			
		} catch (IOException ioe) {
			System.out.println("Exception while reading the file " + ioe);
		}
		
		return base64File;
	}

	public static void uploadProductWithImage(String filePath, Product product) {

		String base64File = encoder(filePath);
		
		if(base64File != null) { //Upload product with image
			
			try {
				Unirest.post(KEY_SERVER)
				.field(KEY_ID, product.getProductId())								
				.field(KEY_NAME, product.getName())
				.field(KEY_DESCRIPTION, product.getDescription())
				.field(KEY_PRICE, product.getPrice())
				
				.field(KEY_IMAGE, base64File)
				.asString();
				System.out.println("ok");
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
			
			
			
		}
			
		

	}
	
	public static void uploadProduct(Product product) {

	
			try {
				Unirest.post(KEY_SERVER)
				.field(KEY_ID, product.getProductId())								
				.field(KEY_NAME, product.getName())
				.field(KEY_DESCRIPTION, product.getDescription())
				.field(KEY_PRICE, product.getPrice())
				.asString();
				System.out.println("ok");
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
			
		}
			
		

	}



