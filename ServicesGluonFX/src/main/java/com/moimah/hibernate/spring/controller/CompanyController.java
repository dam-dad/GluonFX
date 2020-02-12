package com.moimah.hibernate.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moimah.hibernate.spring.daos.CompanyDao;
import com.moimah.hibernate.spring.entities.Company;


@Controller
public class CompanyController {

	@Autowired
	private CompanyDao companyDao; // Inyectamos el DAO dentro del Controller
	
	/*
	 * Crea una nueva compañia	
	 * http://localhost:9002/createCompany?company_id=73828292&name=Manuel&address=calle%20Puente&city=la%20laguna&country=gibraltar&email=manuel@gay.com&phone=696969
	 */
	@RequestMapping(value = "/createCompany", method = RequestMethod.POST)
	@ResponseBody
	public String create(String company_id, String name, String address, String city, String country, String email, String phone) {

		try {

			Company company = new Company(company_id, name, address, city, country, email, phone, null, null, null);
			
			companyDao.create(company);

			return "Compañia creada correctamente";

		} catch (Exception e) {

			return "Error en la creación de la compañia";
		}
	}
	
	
	/*
	 * ELimina una compañia por su id
	 * http://localhost:9002/deleteCompany?id=2
	 */
	@RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
	@ResponseBody
	public String delete(int id) {

		try {

			Company company = new Company();
			company.setId(id);

			companyDao.delete(company);

			return "Compañia eliminada correctamente";

		} catch (Exception e) {

			return "Error en la eliminación de la compañia";
		}

	}
		
	/*
	 * Actualiza una compañia
	 * http://localhost:9002/updateCompany?id=1&company_id=555555&name=cansado&address=cama&city=la laguna&country=cataluna&email=yo@ami.com&phone=463622
	 */
	@RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
	@ResponseBody
	public String update(int id, String company_id, String name, String address, String city, String country, String email, String phone) {

		try {

			Company company = new Company();
			company.setId(id);
			company.setCompanyId(company_id);
			company.setName(name);
			company.setAddress(address);
			company.setCity(city);
			company.setCountry(country);
			company.setEmail(email);
			company.setPhone(phone);
			
			companyDao.update(company);

			return "Compañía actualizada";

		} catch (Exception e) {

			return "Error en la actualización de la compañia";
		}
	}
	
	
	/*
	 * Devuelve una lista de todas las compañias
	 * http://localhost:9002/allCompany
	 */	
	@RequestMapping(value = "/allCompany")
	@ResponseBody	
	public String  all() {
		
		
		List<Company> list = companyDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/*
	 * Devuelve una compañia por si id
	 * http://localhost:9002//companyById?id=1
	 */	
	@RequestMapping(value = "/companyById")
	@ResponseBody	
	public String byId(int id) {
		
		Company company = companyDao.getCompanyById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(company);
				 
		
		return  json;
	}
	
}