#DROP TABLE IF EXISTS offer;
DROP TABLE IF EXISTS has_billing_info;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS billing_info;
DROP TABLE IF EXISTS occupies;
DROP TABLE IF EXISTS works_for;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS staff;


CREATE TABLE staff (
		#15
id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
age INT(3) UNSIGNED NOT NULL,
department VARCHAR(255) NOT NULL,
phone VARCHAR(12) NOT NULL,
address VARCHAR(255) NOT NULL,
job_title VARCHAR(255) NOT NULL );

CREATE TABLE hotel (
#1
id INT(10) UNSIGNED AUTO_INCREMENT KEY,
name VARCHAR(60) NOT NULL,
phone VARCHAR(12) NOT NULL,
address VARCHAR(255) NOT NULL,
city VARCHAR(30) NOT NULL,
#2
manager_id INT(10) UNSIGNED,
FOREIGN KEY (manager_id) REFERENCES staff(id));



CREATE TABLE room (
	#16
id INT(12) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
#3
num VARCHAR(30) NOT NULL,
#17
hotel_id INT(10) UNSIGNED ,
#4
category VARCHAR(30) NOT NULL,
max_occupancy INT(2) UNSIGNED NOT NULL,
nightly_rate FLOAT NOT NULL,
availability INT(1) NOT NULL,
FOREIGN KEY (hotel_id) REFERENCES hotel(id));




CREATE TABLE customer (
#5
id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
date_of_birth DATE,
phone VARCHAR(12) NOT NULL,
email VARCHAR(255) NOT NULL );

CREATE TABLE service (
#16
id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
fee FLOAT NOT NULL);


CREATE TABLE billing_info (
id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
ssn VARCHAR(11) NOT NULL,
address VARCHAR(255) NOT NULL,
credit_card_number VARCHAR(16) NOT NULL,
payment_method VARCHAR(25) NOT NULL);


CREATE TABLE occupies (
stay_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
#6
hotel_id INT(10) UNSIGNED,
room_id INT(12) UNSIGNED,
#7
room_num VARCHAR(30) REFERENCES room(num),
#8
customer_id INT(10) UNSIGNED,
number_of_guest INT(2) UNSIGNED NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME,
discount INT(1) NOT NULL,
FOREIGN KEY (hotel_id) REFERENCES hotel(id),
FOREIGN KEY (room_id) REFERENCES room(id),
FOREIGN KEY (customer_id) REFERENCES customer(id));

CREATE TABLE purchase(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
#9
service_id INT(10) UNSIGNED,
#10
customer_id INT(10) UNSIGNED,
FOREIGN KEY (service_id) REFERENCES service(id),
FOREIGN KEY (customer_id) REFERENCES customer(id));


CREATE TABLE has_billing_info (
#11
customer_id INT(10) UNSIGNED,
#12
billing_id INT(10) UNSIGNED,
FOREIGN KEY (customer_id) REFERENCES customer(id),
FOREIGN KEY (billing_id) REFERENCES billing_info(id));


CREATE TABLE works_for (
#13
hotel_id INT(10) UNSIGNED,
#14
staff_id INT(10) UNSIGNED,
FOREIGN KEY (hotel_id) REFERENCES hotel(id),
FOREIGN KEY (staff_id) REFERENCES staff(id));

INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 100, 'Mary', 40, 'Management', '654', '90 ABC St, Raleigh NC 27', 'Manager');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 101, 'John', 45, 'Management', '564', '798 XYZ St, Rochester NY 54', 'Manager');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 102, 'Carol', 55, 'Management', '546', '351 MH St, Greensboro NC 27', 'Manager');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 103, 'Emma', 55, 'Management', '546', '49 ABC St, Raleigh NC 27', 'Front Desk Staff');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 104, 'Ava', 55, 'Catering', '777', '425 RG St, Raleigh NC 27', 'Catering Staff');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 105, 'Peter', 52, 'Management', '724', '475 RG St, Raleigh NC 27', 'Manager');
INSERT INTO staff (id, name, age, department, phone, address, job_title) VALUES ( 106, 'Olivia', 27, 'Management', '799', '325 PD St, Raleigh NC 27', 'Front Desk Staff');

INSERT INTO hotel (id, name, phone, address, city, manager_id ) VALUES ( 1, 'Hotel A', '919', '21 ABC St, Raleigh NC 27', 'Raleigh', 100);
INSERT INTO hotel (id, name, phone, address, city, manager_id ) VALUES ( 2, 'Hotel B', '718', '25 XYZ St, Rochester NY 54', 'Rochester', 101);
INSERT INTO hotel (id, name, phone, address, city, manager_id ) VALUES ( 3, 'Hotel C', '984', '29 PQR St, Greensboro NC 27', 'Greensboro', 102);
INSERT INTO hotel (id, name, phone, address, city, manager_id ) VALUES ( 4, 'Hotel D', '920', '28 GHW St, Raleigh NC 32', 'Raleigh', 105);

INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 1, '01', 1, 'Economy', 1, 100, 1);
INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 2, '02', 1, 'Deluxe', 2, 200, 1);
INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 3, '03', 2, 'Economy', 1, 100, 1);
INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 4, '02', 3, 'Executive', 3, 1000, 0);
INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 5, '01', 4, 'Presidential', 4, 5000, 1);
INSERT INTO room (id, num, hotel_id, category, max_occupancy, nightly_rate, availability) VALUES ( 6, '05', 1, 'Deluxe', 2, 200, 1);

INSERT INTO customer (id, name, date_of_birth, phone, email) VALUES ( 1001, 'David', STR_TO_DATE('1-30-1980 00:00:00','%m-%d-%Y %H:%i:%s'), '123', 'david@gmail.com');
INSERT INTO customer (id, name, date_of_birth, phone, email) VALUES ( 1002, 'Sarah', STR_TO_DATE('1-30-1971 00:00:00','%m-%d-%Y %H:%i:%s'), '456', 'sarah@gmail.com');
INSERT INTO customer (id, name, date_of_birth, phone, email) VALUES ( 1003, 'Joseph', STR_TO_DATE('1-30-1987 00:00:00','%m-%d-%Y %H:%i:%s'), '789', 'joseph@gmail.com');
INSERT INTO customer (id, name, date_of_birth, phone, email) VALUES ( 1004, 'Lucy', STR_TO_DATE('1-30-1985 00:00:00','%m-%d-%Y %H:%i:%s'), '213', 'lucy@gmail.com');

INSERT INTO service (id, name, fee) VALUES (1, 'phone bills', 5);
INSERT INTO service (id, name, fee) VALUES (2, 'dry cleaning', 16);
INSERT INTO service (id, name, fee) VALUES (3, 'gyms', 15);
INSERT INTO service (id, name, fee) VALUES (4, 'room service', 10);
INSERT INTO service (id, name, fee) VALUES (5, 'special request', 20);

INSERT INTO billing_info ( id, ssn, address, payment_method, credit_card_number) VALUES (1, '593-9846', '980 TRT St, Raleigh NC', 'credit', '1052');
INSERT INTO billing_info ( id, ssn, address, payment_method, credit_card_number) VALUES (2, '777-8352', '7720 MHT St, Greensboro NC', 'hotel credit', '3020');
INSERT INTO billing_info ( id, ssn, address, payment_method, credit_card_number) VALUES (3, '858-9430', '231 DRY St, Rochester NY 78', 'credit', '2497');
INSERT INTO billing_info ( id, ssn, address, payment_method, credit_card_number) VALUES (4, '440-9328', '24 BST Dr, Dallas TX 14', 'cash', '0');

INSERT INTO occupies (hotel_id, room_id, room_num, customer_id, number_of_guest, start_date, end_date, discount) VALUES (1, 1, '01', 1001, 1, STR_TO_DATE('5-10-2017 15:17:00','%m-%d-%Y %H:%i:%s'), STR_TO_DATE('5-13-2017 10:22:00','%m-%d-%Y %H:%i:%s'), 0);
INSERT INTO occupies (hotel_id, room_id, room_num, customer_id, number_of_guest, start_date, end_date, discount) VALUES (1, 2, '02', 1002, 2, STR_TO_DATE('5-10-2017 16:11:00','%m-%d-%Y %H:%i:%s'), STR_TO_DATE('5-13-2017 09:27:00','%m-%d-%Y %H:%i:%s'), 1);
INSERT INTO occupies (hotel_id, room_id, room_num, customer_id, number_of_guest, start_date, end_date, discount) VALUES (2, 3, '03', 1003, 1, STR_TO_DATE('5-10-2017 15:45:00','%m-%d-%Y %H:%i:%s'), STR_TO_DATE('5-14-2017 11:10:00','%m-%d-%Y %H:%i:%s'), 0);
INSERT INTO occupies (hotel_id, room_id, room_num, customer_id, number_of_guest, start_date, end_date, discount) VALUES (3, 4, '02', 1004, 2, STR_TO_DATE('5-10-2017 14:30:00','%m-%d-%Y %H:%i:%s'), STR_TO_DATE('5-12-2017 10:00:00','%m-%d-%Y %H:%i:%s'), 0);

INSERT INTO purchase ( service_id, customer_id) VALUES ( 2 , 1001);
INSERT INTO purchase ( service_id,  customer_id) VALUES ( 3 , 1001);
INSERT INTO purchase ( service_id,  customer_id) VALUES ( 3 , 1002);
INSERT INTO purchase ( service_id,  customer_id) VALUES ( 4 , 1003);
INSERT INTO purchase ( service_id,  customer_id) VALUES ( 1 , 1004);

INSERT INTO has_billing_info (customer_id, billing_id) VALUES (1001, 1);
INSERT INTO has_billing_info (customer_id, billing_id) VALUES (1002, 2);
INSERT INTO has_billing_info (customer_id, billing_id) VALUES (1003, 3);
INSERT INTO has_billing_info (customer_id, billing_id) VALUES (1004, 4);

INSERT INTO works_for (staff_id, hotel_id) VALUES (100, 1);
INSERT INTO works_for (staff_id, hotel_id) VALUES (101, 2);
INSERT INTO works_for (staff_id, hotel_id) VALUES (102, 3);
INSERT INTO works_for (staff_id, hotel_id) VALUES (103, 1);
INSERT INTO works_for (staff_id, hotel_id) VALUES (104, 1);
INSERT INTO works_for (staff_id, hotel_id) VALUES (105, 4);
INSERT INTO works_for (staff_id, hotel_id) VALUES (106, 4);



