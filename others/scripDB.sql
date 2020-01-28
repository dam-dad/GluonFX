CREATE DATABASE 7057507_administration_db;

USE 7057507_administration_db;


CREATE TABLE company(
id INT AUTO_INCREMENT,
company_id VARCHAR(255),
name VARCHAR (500),
address VARCHAR(500),
city VARCHAR(255),
country VARCHAR(255),
email VARCHAR(255),
phone VARCHAR(255),
CONSTRAINT pk_company PRIMARY KEY(id)
);


CREATE TABLE customer(
id INT AUTO_INCREMENT,
customer_id VARCHAR(255),
name VARCHAR (500),
address VARCHAR(500),
city VARCHAR(255),
country VARCHAR(255),
email VARCHAR(255),
phone VARCHAR(255),
CONSTRAINT pk_customer PRIMARY KEY(id)
);

CREATE TABLE tax(
id INT AUTO_INCREMENT,
tax_id VARCHAR(30),
percentage DOUBLE,
description VARCHAR(255),
CONSTRAINT pk_tax PRIMARY KEY(id)
);

CREATE TABLE pay_method(
id INT AUTO_INCREMENT,
description VARCHAR(255),
CONSTRAINT pk_pay_method PRIMARY KEY(id)
);

CREATE TABLE product(
id INT AUTO_INCREMENT,
product_id VARCHAR(30),
name VARCHAR(255),
description VARCHAR(500),
price DOUBLE,
stock INT,
url VARCHAR(500),
CONSTRAINT pk_product PRIMARY KEY(id)
);

CREATE TABLE concept(
id INT AUTO_INCREMENT,
description VARCHAR(600),
price DOUBLE,
CONSTRAINT pk_concept PRIMARY KEY(id)
);

CREATE TABLE invoice(
id INT AUTO_INCREMENT,
invoice_number VARCHAR(255) DEFAULT '0',
company_id INT,
customer_id INT,
invoice_date DATE,
status int DEFAULT 0,
concept_id INT,
pay_method_id INT,
price DOUBLE DEFAULT 0,
tax_id INT,
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_invoice PRIMARY KEY(id),
CONSTRAINT fk_ivoice_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_ivoice_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_ivoice_03 FOREIGN KEY (concept_id) REFERENCES concept(id),
CONSTRAINT fk_ivoice_04 FOREIGN KEY (pay_method_id) REFERENCES pay_method(id),
CONSTRAINT fk_ivoice_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);

CREATE TABLE invoice_detail(
id INT AUTO_INCREMENT,
invoice_id INT,
product_id INT,
quantity DOUBLE,
price DOUBLE ,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_invoice_details_01 PRIMARY KEY(id),
CONSTRAINT fk_invoice_details_01 FOREIGN KEY (invoice_id) REFERENCES invoice(id),
CONSTRAINT fk_invoice_details_02 FOREIGN KEY (product_id) REFERENCES product(id)
);



CREATE TABLE budget(
id INT AUTO_INCREMENT,
budget_number VARCHAR(255) DEFAULT '0',
company_id INT,
customer_id INT,
budget_date DATE,
status int DEFAULT 0,
concept_id INT,
price DOUBLE DEFAULT 0,
tax_id INT,
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_budget PRIMARY KEY(id),
CONSTRAINT fk_budget_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_budget_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_budget_03 FOREIGN KEY (concept_id) REFERENCES concept(id),
CONSTRAINT fk_budget_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);



CREATE TABLE budget_detail(
id INT AUTO_INCREMENT,
budget_id INT,
product_id INT,
quantity DOUBLE,
price DOUBLE ,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_budget_details_01 PRIMARY KEY(id),
CONSTRAINT fk_budget_details_01 FOREIGN KEY (budget_id) REFERENCES budget(id),
CONSTRAINT fk_budget_details_02 FOREIGN KEY (product_id) REFERENCES product(id)
);


CREATE TABLE work_order(
id INT AUTO_INCREMENT,
work_order_number VARCHAR(255) DEFAULT '0',
company_id INT,
customer_id INT,
work_order_date DATE,
status int DEFAULT 0,
concept_id INT,
price DOUBLE DEFAULT 0,
tax_id INT,
tax_total DOUBLE DEFAULT 0,
price_taxes_included DOUBLE DEFAULT 0,
CONSTRAINT pk_work_order PRIMARY KEY(id),
CONSTRAINT fk_work_order_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_work_order_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_work_order_03 FOREIGN KEY (concept_id) REFERENCES concept(id),
CONSTRAINT fk_work_order_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);

CREATE TABLE work_order_detail(
id INT AUTO_INCREMENT,
work_order_id INT,
product_id INT,
quantity DOUBLE,
price DOUBLE ,
price_unit DOUBLE DEFAULT 0,
CONSTRAINT pk_work_order_details_01 PRIMARY KEY(id),
CONSTRAINT fk_work_order_details_01 FOREIGN KEY (work_order_id) REFERENCES work_order(id),
CONSTRAINT fk_work_order_details_02 FOREIGN KEY (product_id) REFERENCES product(id)
);

--Trigger for invoice number

DELIMITER //
CREATE TRIGGER insert_invoice_number BEFORE INSERT ON invoice
	FOR EACH ROW
      	BEGIN
      		SET NEW.invoice_number = (SELECT CONCAT_WS('',(SELECT YEAR(NEW.invoice_date)), (SELECT LPAD((MONTH(NEW.invoice_date)),2,'0')), (SELECT LPAD((SELECT COUNT(invoice_number) + 1  FROM invoice WHERE YEAR(invoice_date) = YEAR(NEW.invoice_date) AND MONTH(invoice_date) = MONTH(NEW.invoice_date)),4,'0'))));
      	END;
//
DELIMITER ;

--Trigger for budget number

DELIMITER //
CREATE TRIGGER insert_budget_number BEFORE INSERT ON budget
	FOR EACH ROW
      	BEGIN
      		SET NEW.budget_number = (SELECT CONCAT_WS('','B',(SELECT YEAR(NEW.budget_date)), (SELECT LPAD((MONTH(NEW.budget_date)),2,'0')), (SELECT LPAD((SELECT COUNT(budget_number) + 1  FROM budget WHERE YEAR(budget_date) = YEAR(NEW.budget_date) AND MONTH(budget_date) = MONTH(NEW.budget_date)),4,'0'))));
      	END;
//
DELIMITER ;

--Trigger for work order number

DELIMITER //
CREATE TRIGGER insert_work_order_number BEFORE INSERT ON work_order
	FOR EACH ROW
      	BEGIN
      		SET NEW.work_order_number = (SELECT CONCAT_WS('','WO',(SELECT YEAR(NEW.work_order_date)), (SELECT LPAD((MONTH(NEW.work_order_date)),2,'0')), (SELECT LPAD((SELECT COUNT(work_order_number) + 1  FROM work_order WHERE YEAR(work_order_date) = YEAR(NEW.work_order_date) AND MONTH(work_order_date) = MONTH(NEW.work_order_date)),4,'0'))));
      	END;
//
DELIMITER ;



--Inserts for test database

INSERT INTO company (company_id, name, address, city, country, email, phone) 
VALUES ('D0001', 'MyCompany', 'C/pincel 12','la laguna', 'Spain', 'info@mycompany.es', '686868');

INSERT INTO customer (customer_id, name, address, city, country, email, phone)
VALUES ('A0001', 'Amazon', 'C/Peliaguda 11','la laguna', 'Spain', 'info@amazon.es', '6333333');

INSERT INTO tax (tax_id, percentage, description) VALUES ('IGIC', 7, 'IMPUESTO CANARIO BASE'); 

INSERT INTO pay_method(description) VALUES ('BANK TRANSFER'); 

INSERT INTO product (product_id, name, description, price, stock, url) 
VALUES ('10000191', 'Consolador', 'placentero y peque�o', 9.90, 900, 'www.pornhub.com');

INSERT INTO concept (description) VALUES ('kit de placer'); 

INSERT INTO invoice (company_id, customer_id, invoice_date, concept_id, pay_method_id, tax_id)
VALUES (1, 1, '2020-01-20', 1, 1, 1);
INSERT INTO invoice_detail (invoice_id, product_id, quantity) VALUES (1, 1, 3);
INSERT INTO product (product_id, name, description, price, url) 
VALUES ('10000192', 'Consolador', 'placentero y peque�o', 1.90, 'www.pornhub.com');