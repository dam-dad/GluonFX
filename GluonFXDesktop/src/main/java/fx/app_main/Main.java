package fx.app_main;

import utils.PDFGenerator;

public class Main {

	public static void main(String[] args) {
		//TemplateApp.main(args);
		PDFGenerator hola= new PDFGenerator();
		hola.crearPDF("C:/Users/derro/eclipse-workspace/dad/GluonFX-master/GluonFXDesktop/src/main/resources/hola.pdf");
		App.main(args);
	}

}
