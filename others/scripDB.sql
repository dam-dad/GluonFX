DROP DATABASE 7057507_administration_db;

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
percentage DOUBLE(16,2) DEFAULT 0,
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
price DOUBLE(16,2) DEFAULT 0,
stock INT DEFAULT 0,
url VARCHAR(500),
CONSTRAINT pk_product PRIMARY KEY(id)
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
price DOUBLE(16,2) DEFAULT 0,
tax_id INT,
tax_total DOUBLE(16,2) DEFAULT 0,
price_taxes_included DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_invoice PRIMARY KEY(id),
CONSTRAINT fk_ivoice_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_ivoice_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_ivoice_04 FOREIGN KEY (pay_method_id) REFERENCES pay_method(id),
CONSTRAINT fk_ivoice_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);

CREATE TABLE concept_invoice(
id INT AUTO_INCREMENT,
invoice_id INT,
description VARCHAR(600),
price DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_concept PRIMARY KEY(id),
CONSTRAINT fk_invoice_concept FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);

CREATE TABLE invoice_detail(
id INT AUTO_INCREMENT,
invoice_id INT,
product_id INT ,
quantity DOUBLE(16,2) DEFAULT 0,
price DOUBLE(16,2) DEFAULT 0,
price_unit DOUBLE(16,2) DEFAULT 0,
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
price DOUBLE(16,2) DEFAULT 0,
tax_id INT,
tax_total DOUBLE(16,2) DEFAULT 0,
price_taxes_included DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_budget PRIMARY KEY(id),
CONSTRAINT fk_budget_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_budget_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_budget_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);



CREATE TABLE budget_detail(
id INT AUTO_INCREMENT,
budget_id INT,
product_id INT ,
quantity DOUBLE(16,2) DEFAULT 0,
price DOUBLE(16,2) DEFAULT 0,
price_unit DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_budget_details_01 PRIMARY KEY(id),
CONSTRAINT fk_budget_details_01 FOREIGN KEY (budget_id) REFERENCES budget(id),
CONSTRAINT fk_budget_details_02 FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE concept_budget(
id INT AUTO_INCREMENT,
budget_id INT ,
description VARCHAR(600),
price DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_concept PRIMARY KEY(id),
CONSTRAINT fk_budget_concept FOREIGN KEY (budget_id) REFERENCES budget(id)
);

CREATE TABLE work_order(
id INT AUTO_INCREMENT,
work_order_number VARCHAR(255) DEFAULT '0',
company_id INT,
customer_id INT,
work_order_date DATE,
status int DEFAULT 0,
concept_id INT,
price DOUBLE(16,2) DEFAULT 0,
tax_id INT,
tax_total DOUBLE(16,2) DEFAULT 0,
price_taxes_included DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_work_order PRIMARY KEY(id),
CONSTRAINT fk_work_order_01 FOREIGN KEY (company_id) REFERENCES company(id),
CONSTRAINT fk_work_order_02 FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT fk_work_order_05 FOREIGN KEY (tax_id) REFERENCES tax(id)
);

CREATE TABLE work_order_detail(
id INT AUTO_INCREMENT,
work_order_id INT,
product_id INT ,
quantity DOUBLE(16,2) DEFAULT 0,
price DOUBLE(16,2) DEFAULT 0,
price_unit DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_work_order_details_01 PRIMARY KEY(id),
CONSTRAINT fk_work_order_details_01 FOREIGN KEY (work_order_id) REFERENCES work_order(id),
CONSTRAINT fk_work_order_details_02 FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE concept_work_order(
id INT AUTO_INCREMENT,
work_order_id INT,
description VARCHAR(600),
price DOUBLE(16,2) DEFAULT 0,
CONSTRAINT pk_concept PRIMARY KEY(id),
CONSTRAINT fk_work_order_concept FOREIGN KEY (work_order_id) REFERENCES work_order(id)
);

DELIMITER //
CREATE TRIGGER insert_concept_invoice BEFORE INSERT ON concept_invoice
      FOR EACH ROW
            BEGIN
                  UPDATE invoice SET price = ((SELECT price FROM invoice WHERE id = NEW.invoice_id) + (NEW.price)) WHERE id = NEW.invoice_id;
                  UPDATE invoice SET tax_total = ((SELECT price FROM invoice WHERE id = NEW.invoice_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM invoice WHERE id = NEW.invoice_id)) / 100) WHERE id = NEW.invoice_id;
                  UPDATE invoice SET price_taxes_included = ((SELECT price + tax_total FROM invoice WHERE id = NEW.invoice_id)) WHERE id = NEW.invoice_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_concept_work_order BEFORE INSERT ON concept_work_order
      FOR EACH ROW
            BEGIN
                  UPDATE work_order SET price = ((SELECT price FROM work_order WHERE id = NEW.work_order_id) + (NEW.price)) WHERE id = NEW.work_order_id;
                  UPDATE work_order SET tax_total = ((SELECT price FROM work_order WHERE id = NEW.work_order_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM work_order WHERE id = NEW.work_order_id)) / 100) WHERE id = NEW.work_order_id;
                  UPDATE work_order SET price_taxes_included = ((SELECT price + tax_total FROM work_order WHERE id = NEW.work_order_id)) WHERE id = NEW.work_order_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_concept_budget BEFORE INSERT ON concept_budget
      FOR EACH ROW
            BEGIN
                  UPDATE budget SET price = ((SELECT price FROM budget WHERE id = NEW.budget_id) + (NEW.price)) WHERE id = NEW.budget_id;
                  UPDATE budget SET tax_total = ((SELECT price FROM budget WHERE id = NEW.budget_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM budget WHERE id = NEW.budget_id)) / 100) WHERE id = NEW.budget_id;
                  UPDATE budget SET price_taxes_included = ((SELECT price + tax_total FROM budget WHERE id = NEW.budget_id)) WHERE id = NEW.budget_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_invoice_number BEFORE INSERT ON invoice
      FOR EACH ROW
            BEGIN
                  SET NEW.invoice_number = (SELECT CONCAT_WS('',(SELECT YEAR(NEW.invoice_date)), (SELECT LPAD((MONTH(NEW.invoice_date)),2,'0')), (SELECT LPAD((SELECT COUNT(invoice_number) + 1  FROM invoice WHERE YEAR(invoice_date) = YEAR(NEW.invoice_date) AND MONTH(invoice_date) = MONTH(NEW.invoice_date)),4,'0'))));
            END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER insert_budget_number BEFORE INSERT ON budget
      FOR EACH ROW
            BEGIN
                  SET NEW.budget_number = (SELECT CONCAT_WS('','B',(SELECT YEAR(NEW.budget_date)), (SELECT LPAD((MONTH(NEW.budget_date)),2,'0')), (SELECT LPAD((SELECT COUNT(budget_number) + 1  FROM budget WHERE YEAR(budget_date) = YEAR(NEW.budget_date) AND MONTH(budget_date) = MONTH(NEW.budget_date)),4,'0'))));
            END;
//
DELIMITER ;



DELIMITER //
CREATE TRIGGER insert_work_order_number BEFORE INSERT ON work_order
      FOR EACH ROW
            BEGIN
                  SET NEW.work_order_number = (SELECT CONCAT_WS('','WO',(SELECT YEAR(NEW.work_order_date)), (SELECT LPAD((MONTH(NEW.work_order_date)),2,'0')), (SELECT LPAD((SELECT COUNT(work_order_number) + 1  FROM work_order WHERE YEAR(work_order_date) = YEAR(NEW.work_order_date) AND MONTH(work_order_date) = MONTH(NEW.work_order_date)),4,'0'))));
            END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER update_detail BEFORE UPDATE ON invoice_detail
      FOR EACH ROW
            BEGIN
                  DECLARE fallo CONDITION FOR SQLSTATE '45000';
                  IF (ABS(OLD.quantity-NEW.quantity)<=(SELECT stock FROM product WHERE id=NEW.product_id)) THEN                           
                        UPDATE product SET product.stock = ((SELECT stock FROM product WHERE id = NEW.product_id) + (OLD.quantity-NEW.quantity)) WHERE product.id = NEW.product_id;
                        SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                        UPDATE invoice SET invoice.price = ((SELECT price FROM invoice WHERE id = OLD.invoice_id) + (NEW.price - OLD.price)) WHERE invoice.id = OLD.invoice_id;
                        UPDATE invoice SET invoice.tax_total = ((SELECT price FROM invoice WHERE invoice.id = NEW.invoice_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM invoice WHERE invoice.id = NEW.invoice_id)) / 100) WHERE invoice.id = NEW.invoice_id;
                        UPDATE invoice SET invoice.price_taxes_included = ((SELECT price + tax_total FROM invoice WHERE invoice.id = NEW.invoice_id)) WHERE invoice.id = NEW.invoice_id;

                  ELSE
                          SIGNAL SQLSTATE  '45000'
                              SET MESSAGE_Text = 'No se ha podido actualizar';

                          END IF;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER update_work_order BEFORE UPDATE ON work_order_detail
      FOR EACH ROW
            BEGIN                      
                  SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                  UPDATE work_order SET price = ((SELECT price FROM work_order WHERE id = OLD.work_order_id) + (NEW.price - OLD.price)) WHERE id = OLD.work_order_id;
                  UPDATE work_order SET tax_total = ((SELECT price FROM work_order WHERE id = NEW.work_order_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM work_order WHERE id = NEW.work_order_id)) / 100) WHERE id = NEW.work_order_id;
                  UPDATE work_order SET price_taxes_included = ((SELECT price + tax_total FROM work_order WHERE id = NEW.work_order_id)) WHERE id = NEW.work_order_id;
            END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER update_budget BEFORE UPDATE ON budget_detail
      FOR EACH ROW
            BEGIN                      
                  SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                  UPDATE budget SET price = ((SELECT price FROM budget WHERE id = OLD.budget_id) + (NEW.price - OLD.price)) WHERE id = OLD.budget_id;
                  UPDATE budget SET tax_total = ((SELECT price FROM budget WHERE id = NEW.budget_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM budget WHERE id = NEW.budget_id)) / 100) WHERE id = NEW.budget_id;
                  UPDATE budget SET price_taxes_included = ((SELECT price + tax_total FROM budget WHERE id = NEW.budget_id)) WHERE id = NEW.budget_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER update_detail_tax BEFORE UPDATE ON invoice
      FOR EACH ROW
            BEGIN
                  IF (NEW.tax_id!=(SELECT tax_id FROM invoice WHERE id=NEW.id)) THEN     
                        SET NEW.tax_total = (OLD.price * (SELECT percentage FROM tax WHERE id = NEW.tax_id) / 100);
                        SET NEW.price_taxes_included = OLD.price + NEW.tax_total;
                  END IF;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER update_work_order_tax BEFORE UPDATE ON work_order
      FOR EACH ROW
            BEGIN                      
                  IF (NEW.tax_id!=(SELECT tax_id FROM work_order WHERE id=NEW.tax_id)) THEN     
                        SET NEW.tax_total = (OLD.price * (SELECT percentage FROM tax WHERE id = NEW.tax_id) / 100);
                        SET NEW.price_taxes_included = OLD.price + NEW.tax_total;
                  END IF;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER update_budget_tax BEFORE UPDATE ON budget
      FOR EACH ROW
            BEGIN                      
                  IF (NEW.tax_id!=(SELECT tax_id FROM budget WHERE id=NEW.tax_id)) THEN     
                        SET NEW.tax_total = (OLD.price * (SELECT percentage FROM tax WHERE id = NEW.tax_id) / 100);
                        SET NEW.price_taxes_included = OLD.price + NEW.tax_total;
                  END IF;
            END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER delete_detail AFTER delete ON invoice_detail
      FOR EACH ROW
            BEGIN
                  UPDATE product SET product.stock = ((SELECT stock FROM product WHERE id = OLD.product_id) + OLD.quantity) WHERE product.id = OLD.product_id;
                  UPDATE invoice SET invoice.price = ((SELECT price FROM invoice WHERE invoice.id = OLD.invoice_id) - OLD.price) WHERE invoice.id = OLD.invoice_id;
                  UPDATE invoice SET invoice.tax_total = ((SELECT price FROM invoice WHERE invoice.id = OLD.invoice_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM invoice WHERE invoice.id = OLD.invoice_id)) / 100) WHERE invoice.id = OLD.invoice_id;
                  UPDATE invoice SET invoice.price_taxes_included = ((SELECT price + tax_total FROM invoice WHERE invoice.id = OLD.invoice_id)) WHERE invoice.id = OLD.invoice_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER delete_work_order AFTER delete ON work_order_detail
      FOR EACH ROW
            BEGIN
                  UPDATE work_order SET price = ((SELECT price FROM work_order WHERE id = OLD.work_order_id) - OLD.price) WHERE id = OLD.work_order_id;
                  UPDATE work_order SET tax_total = ((SELECT price FROM work_order WHERE id = OLD.work_order_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM work_order WHERE id = OLD.work_order_id)) / 100) WHERE id = OLD.work_order_id;
                  UPDATE work_order SET price_taxes_included = ((SELECT price + tax_total FROM work_order WHERE id = OLD.work_order_id)) WHERE id = OLD.work_order_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER delete_budget AFTER delete ON budget_detail
      FOR EACH ROW
            BEGIN
                  UPDATE budget SET price = ((SELECT price FROM budget WHERE id = OLD.budget_id) - OLD.price) WHERE id = OLD.budget_id;
                  UPDATE budget SET tax_total = ((SELECT price FROM budget WHERE id = OLD.budget_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM budget WHERE id = OLD.budget_id)) / 100) WHERE id = OLD.budget_id;
                  UPDATE budget SET price_taxes_included = ((SELECT price + tax_total FROM budget WHERE id = OLD.budget_id)) WHERE id = OLD.budget_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_detail BEFORE INSERT ON invoice_detail
      FOR EACH ROW
            BEGIN 
                  DECLARE fallo CONDITION FOR SQLSTATE '45000';
                  IF (NEW.quantity<=(SELECT stock FROM product WHERE id=NEW.product_id)) THEN                           
                        SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                        SET NEW.price_unit = (SELECT price FROM product WHERE id = NEW.product_id);
                        UPDATE product SET product.stock = ((SELECT stock FROM product WHERE id = NEW.product_id) - NEW.quantity) WHERE product.id = NEW.product_id;
                        UPDATE invoice SET invoice.price = ((SELECT price FROM invoice WHERE id = NEW.invoice_id) + NEW.price) WHERE invoice.id = NEW.invoice_id;
                        UPDATE invoice SET invoice.tax_total = ((SELECT price FROM invoice WHERE invoice.id = NEW.invoice_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM invoice WHERE invoice.id = NEW.invoice_id)) / 100) WHERE invoice.id = NEW.invoice_id;
                        UPDATE invoice SET invoice.price_taxes_included = ((SELECT price + tax_total FROM invoice WHERE invoice.id = NEW.invoice_id)) WHERE invoice.id = NEW.invoice_id;
                  ELSE
                          SIGNAL SQLSTATE  '45000'
                              SET MESSAGE_Text = 'No se ha podido insertar ';

                          END IF;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_work_order BEFORE INSERT ON work_order_detail
      FOR EACH ROW
            BEGIN                            
                  SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                  SET NEW.price_unit = (SELECT price FROM product WHERE id = NEW.product_id);
                  UPDATE work_order SET price = ((SELECT price FROM work_order WHERE id = NEW.work_order_id) + NEW.price) WHERE id = NEW.work_order_id;
                  UPDATE work_order SET tax_total = ((SELECT price FROM work_order WHERE id = NEW.work_order_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM work_order WHERE id = NEW.work_order_id)) / 100) WHERE id = NEW.work_order_id;
                  UPDATE work_order SET price_taxes_included = ((SELECT price + tax_total FROM work_order WHERE id = NEW.work_order_id)) WHERE id = NEW.work_order_id;
            END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insert_budget BEFORE INSERT ON budget_detail
      FOR EACH ROW
            BEGIN                            
                  SET NEW.price = (NEW.quantity * (SELECT price FROM product WHERE id = NEW.product_id));
                  SET NEW.price_unit = (SELECT price FROM product WHERE id = NEW.product_id);
                  UPDATE budget SET price = ((SELECT price FROM budget WHERE id = NEW.budget_id) + NEW.price) WHERE id = NEW.budget_id;
                  UPDATE budget SET tax_total = ((SELECT price FROM budget WHERE id = NEW.budget_id) * (SELECT percentage FROM tax WHERE id = (SELECT tax_id FROM budget WHERE id = NEW.budget_id)) / 100) WHERE id = NEW.budget_id;
                  UPDATE budget SET price_taxes_included = ((SELECT price + tax_total FROM budget WHERE id = NEW.budget_id)) WHERE id = NEW.budget_id;
            END;
//
DELIMITER ;



USE 7057507_administration_db;

INSERT INTO company (company_id, name, address, city, country, email, phone) VALUES ('D0001', 'Informatica SL', 'C/pincel 12','la laguna', 'Spain', 'inforsl@mycompany.es', '922686868');

INSERT INTO customer (customer_id, name, address, city, country, email, phone) VALUES ('A0001', 'Tu bicicleta ya', 'C/Peliaguda 11','la laguna', 'Spain', 'info@tubici.es', '9226333333');

INSERT INTO tax (tax_id, percentage, description) VALUES ('IGIC', 7, 'IMPUESTO CANARIO INDIRECTO GENERAL'); 

INSERT INTO tax (tax_id, percentage, description) VALUES ('IVA', 21, 'IMPUESTO VALOR AÑADIDO'); 

INSERT INTO pay_method(description) VALUES ('Tansferencia'); 

INSERT INTO pay_method(description) VALUES ('Efectivo'); 

INSERT INTO pay_method(description) VALUES ('Crédito'); 
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000191', 'ASUS M5A78L-M PLUS/USB3 - 90MB0RB0-M0EAY0', 'Placa base ASUS', 110.6, 900, 'https://i.imgur.com/ZKMDR9i.jpeg');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000192', 'ROG Pugio', 'Raton ASUS', 60.6, 900, 'https://i.imgur.com/jncB7bp.jpg');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000193', 'Impresora multifunción Canon Pixma TS3150', 'Impresora multifuncion', 100.6, 100, 'https://i.imgur.com/1yvSued.jpg');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000194', 'HP 22w - Monitor 21.5" ', 'Full HD, 1920 x 1080 pixeles, tiempo de respuesta de 5 ms, 1 x HDMI, 1 x VGA, 16:9', 180.6, 100, 'https://i.imgur.com/xCDupm6.jpeg');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000195', 'HyperX Fury HX426C16FB3AK2/16 Memoria RAM DIMM DDR4 ', '16GB 2666MHz CL161Rx8 RGB', 120.6, 100, 'https://i.imgur.com/wPVshiO.png');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000196', 'Procesador Intel® Core™ i9-9900K', '16 MB de caché, hasta 5 GHz', 500.6, 100, 'https://i.imgur.com/aYGDDnJ.jpeg');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000197', 'Limpieza', ' limpieza del pc y cambiamos pasta termica', 50.6, 100000, '');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000198', 'Mano de obra', 'Mano de obra de un tecnico expeciaizado', 30.0, 100000, '');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000198', 'Formateo', 'Te hacemos una tarea que podrias hacer tu solo y te cobramos muchisimo por ello gracias', 50.6, 100000, '');
INSERT INTO product (product_id, name, description, price, stock, url) VALUES ('10000199', 'Transporte', 'Si es necesario transporte del tecnico', 10.6, 100000, '');

INSERT INTO invoice (company_id, customer_id, invoice_date, concept_id, pay_method_id, tax_id) VALUES (1, 1, '2020-01-20', 1, 1, 1);

INSERT INTO invoice (company_id, customer_id, invoice_date, concept_id, pay_method_id, tax_id) VALUES (1, 1, '2020-01-20', 1, 1, 1);

INSERT INTO concept_invoice (invoice_id,description,price) VALUES (1,'Presupuesto de un ordenador sobremesa',30); 

INSERT INTO invoice_detail (invoice_id, product_id, quantity) VALUES (1, 1, 3);

INSERT INTO invoice_detail (invoice_id, product_id, quantity) VALUES (2, 1, 10);



INSERT INTO invoice_detail (invoice_id, product_id, quantity) VALUES (1, 2, 8);
