package test;

import java.util.ArrayList;

import model.entities.Company;
import hibernate.HibernateController;

public class TestEntities {
	
	public static void main(String[] args) {
		
		HibernateController hibernate = new HibernateController();		
		hibernate.start();
		
		
		//Test insert company		
//		Company c1 = new Company();
//		c1.setCompanyId("44444");
//		c1.setCity("La Laguna");
//		
//	//	hibernate.save(c1);
//		
//		ArrayList<Company> list = (ArrayList<Company>) hibernate.selectAll("Company");
//		
//		for (int i = 0; i <list.size(); i++) {
//			
//			System.out.println(list.get(i).getCompanyId());
//			
//		}
	}
	

}
