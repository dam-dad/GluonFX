USE administration_db;

CREATE TABLE company(
company_id VARCHAR(255),
name VARCHAR (500),
address VARCHAR(500),
city VARCHAR(255),
country VARCHAR(255),
email VARCHAR(255),
phone VARCHAR(255),
CONSTRAINT pk_company PRIMARY KEY(company_id)
);


CREATE TABLE customer(
customer_id VARCHAR(255),
name VARCHAR (500),
address VARCHAR(500),
city VARCHAR(255),
country VARCHAR(255),
email VARCHAR(255),
phone VARCHAR(255),
CONSTRAINT pk_customer PRIMARY KEY(customer_id)
);

CREATE TABLE tax(
tax_id VARCHAR(30),
percentage DOUBLE,
description VARCHAR(255),
CONSTRAINT pk_tax PRIMARY KEY(tax_id)
);

CREATE TABLE pay_method(
pay_method_id INT AUTO_INCREMENT,
description VARCHAR(255),
CONSTRAINT pk_pay_method PRIMARY KEY(pay_method_id )
);

CREATE TABLE product(
product_id VARCHAR(30),
name VARCHAR(255),
description VARCHAR(500),
price DOUBLE,
url VARCHAR(500),
CONSTRAINT pk_product PRIMARY KEY(product_id)
);

CREATE TABLE concept(
concept_id INT AUTO_INCREMENT,
description VARCHAR(600),
price DOUBLE,
CONSTRAINT pk_concept PRIMARY KEY(concept_id)
);


CREATE TABLE invoice(
invoice_id INT AUTO_INCREMENT,
invoice_number VARCHAR(255) DEFAULT '0',
company_id VARCHAR(255),
customer_id VARCHAR(255),
invoice_date DATE,
status int DEFAULT 0,
concept_id INT,
pay_method_id INT,
price DOUBLE DEFAULT 0,
tax_id VARCHAR(30),
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_invoice PRIMARY KEY(invoice_id),
CONSTRAINT fk_ivoice_01 FOREIGN KEY (company_id) REFERENCES company(company_id),
CONSTRAINT fk_ivoice_02 FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
CONSTRAINT fk_ivoice_03 FOREIGN KEY (concept_id) REFERENCES concept(concept_id),
CONSTRAINT fk_ivoice_04 FOREIGN KEY (pay_method_id) REFERENCES pay_method(pay_method_id),
CONSTRAINT fk_ivoice_05 FOREIGN KEY (tax_id) REFERENCES tax(tax_id)
);


CREATE TABLE work_order(
work_order_id INT AUTO_INCREMENT,
work_order_number VARCHAR(255) DEFAULT '0',
company_id VARCHAR(255),
customer_id VARCHAR(255),
work_order_date DATE,
status int DEFAULT 0,
concept_id INT,
price DOUBLE DEFAULT 0,
tax_id VARCHAR(30),
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_work_order PRIMARY KEY(work_order_id),
CONSTRAINT fk_work_order_01 FOREIGN KEY (company_id) REFERENCES company(company_id),
CONSTRAINT fk_work_order_02 FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
CONSTRAINT fk_work_order_03 FOREIGN KEY (concept_id) REFERENCES concept(concept_id),
CONSTRAINT fk_work_order_04 FOREIGN KEY (tax_id) REFERENCES tax(tax_id)
);

CREATE TABLE budget(
budget_id INT AUTO_INCREMENT,
budget_number VARCHAR(255) DEFAULT '0',
company_id VARCHAR(255),
customer_id VARCHAR(255),
budget_date DATE,
status int DEFAULT 0,
concept_id INT,
price DOUBLE DEFAULT 0,
tax_id VARCHAR(30),
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_budget PRIMARY KEY(budget_id),
CONSTRAINT fk_budget_01 FOREIGN KEY (company_id) REFERENCES company(company_id),
CONSTRAINT fk_budget_02 FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
CONSTRAINT fk_budget_03 FOREIGN KEY (concept_id) REFERENCES concept(concept_id),
CONSTRAINT fk_budget_04 FOREIGN KEY (tax_id) REFERENCES tax(tax_id)
);


CREATE TABLE invoice_detail(
invoice_detail_id INT AUTO_INCREMENT,
invoice_id INT,
product_id VARCHAR(30),
quantity DOUBLE,
price DOUBLE ,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_invoice_details_01 PRIMARY KEY(invoice_detail_id,product_id),
CONSTRAINT fk_invoice_details_01 FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id),
CONSTRAINT fk_invoice_details_02 FOREIGN KEY (product_id) REFERENCES product(product_id)
);


CREATE TABLE work_order_detail(
order_detail_id INT AUTO_INCREMENT,
work_order_id INT,
product_id VARCHAR(30),
quantity DOUBLE,
price DOUBLE,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_work_order_details_01 PRIMARY KEY(order_detail_id, product_id),
CONSTRAINT fk_work_order_details_01 FOREIGN KEY (work_order_id) REFERENCES work_order(work_order_id),
CONSTRAINT fk_work_order_details_02 FOREIGN KEY (product_id) REFERENCES product(product_id)
);


CREATE TABLE budget_detail(
budget_detail_id INT AUTO_INCREMENT,
budget_id INT,
product_id VARCHAR(30),
quantity DOUBLE,
price DOUBLE,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_budget_details_01 PRIMARY KEY(budget_detail_id, product_id),
CONSTRAINT fk_budget_details_01 FOREIGN KEY (budget_id) REFERENCES budget(budget_id),
CONSTRAINT fk_budget_details_02 FOREIGN KEY (product_id) REFERENCES product(product_id)
);


DELIMITER //
CREATE TRIGGER insert_id_invoice BEFORE INSERT ON invoice
	FOR EACH ROW
      	BEGIN
      		SET NEW.invoice_number = (SELECT CONCAT_WS('',(SELECT YEAR(NEW.invoice_date)), (SELECT LPAD((MONTH(NEW.invoice_date)),2,'0')), (SELECT LPAD((SELECT COUNT(invoice_number) + 1  FROM invoice WHERE YEAR(invoice_date) = YEAR(NEW.invoice_date) AND MONTH(invoice_date) = MONTH(NEW.invoice_date)),4,'0'))));
      	END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER insert_id_work_order BEFORE INSERT ON work_order
	FOR EACH ROW
      	BEGIN
      		SET NEW.work_order_number = (SELECT CONCAT_WS('','WO',(SELECT YEAR(NEW.work_order_date)), (SELECT LPAD((MONTH(NEW.work_order_date)),2,'0')), (SELECT LPAD((SELECT COUNT(work_order_number) + 1  FROM work_order WHERE YEAR(work_order_date) = YEAR(NEW.work_order_date) AND MONTH(work_order_date) = MONTH(NEW.work_order_date)),4,'0'))));
      	END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER insert_id_budget BEFORE INSERT ON budget
	FOR EACH ROW
      	BEGIN
      		SET NEW.budget_number = (SELECT CONCAT_WS('','B',(SELECT YEAR(NEW.budget_date)), (SELECT LPAD((MONTH(NEW.budget_date)),2,'0')), (SELECT LPAD((SELECT COUNT(budget_number) + 1  FROM budget WHERE YEAR(budget_date) = YEAR(NEW.budget_date) AND MONTH(budget_date) = MONTH(NEW.budget_date)),4,'0'))));
      	END;
//
DELIMITER ;

INSERT INTO company (company_id, name, address, city, country, email, phone) 
VALUES ('D0001', 'MyCompany', 'C/pincel 12','la laguna', 'Spain', 'info@mycompany.es', '686868');

INSERT INTO customer (customer_id, name, address, city, country, email, phone)
VALUES ('A0001', 'Amazon', 'C/Peliaguda 11','la laguna', 'Spain', 'info@amazon.es', '6333333');
 


INSERT INTO tax (tax_id, percentage, description) VALUES ('IGIC', 7, 'IMPUESTO CANARIO BASE'); 



INSERT INTO pay_method(description) VALUES ('BANK TRANSFER'); 


INSERT INTO product (product_id, name, description, price, url) 
VALUES ('10000191', 'Consolador', 'placentero y pequeño', 9.90, 'www.pornhub.com');



INSERT INTO concept (description) VALUES ('kit de placer'); 



INSERT INTO invoice (company_id, customer_id, invoice_date, concept_id, pay_method_id, tax_id)
VALUES ('D0001', 'A0001', '2020-01-20', 1, 1, 'IGIC');
INSERT INTO invoice_detail (invoice_id, product_id, quantity) VALUES (1, '10000191', 3);
INSERT INTO product (product_id, name, description, price, url) 
VALUES ('10000192', 'Consolador', 'placentero y pequeño', 1.90, 'www.pornhub.com');