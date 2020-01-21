package entities;
// Generated 21 ene. 2020 12:50:14 by Hibernate Tools 5.2.12.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company generated by hbm2java
 */
@Entity
@Table(name="company"
    ,catalog="7057507_administration_db"
)
public class Company  implements java.io.Serializable {


     private String companyId;
     private String name;
     private String address;
     private String city;
     private String country;
     private String email;
     private String phone;

    public Company() {
    }

	
    public Company(String companyId) {
        this.companyId = companyId;
    }
    public Company(String companyId, String name, String address, String city, String country, String email, String phone) {
       this.companyId = companyId;
       this.name = name;
       this.address = address;
       this.city = city;
       this.country = country;
       this.email = email;
       this.phone = phone;
    }
   
     @Id 

    
    @Column(name="company_id", unique=true, nullable=false)
    public String getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    
    @Column(name="name", length=500)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="address", length=500)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    
    @Column(name="city")
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    @Column(name="country")
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="phone")
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }




}


