create database mobile_store;
use mobile_store;
create table `user`(
id int auto_increment primary key,
`name` varchar(255),
username varchar(255),
password varchar(255),
email varchar(255)
);
 create table `role`(
 id int auto_increment primary key,
`name` varchar(255)
);
 create table user_role(
 id_role int,
 id_user int,
 primary key(id_role,id_user),
 foreign key(id_user) references `user`(id),
  foreign key(id_role) references `role`(id)
 );
create table commodity(
id int auto_increment primary key,
`name` varchar(255),
`cpu` varchar(255),
capacity varchar(255),
trademark varchar(255),
price double,
image varchar(255),
camera varchar(255),
selfie varchar(255),
screensize varchar(255),
guarantee varchar(255),
origin varchar(255),
`description` varchar(255),
code_qr varchar(255) ,
quantity int,
id_supplier int,
foreign key(id_supplier) references supplier(id)	
);
create table supplier(
id int auto_increment primary key,
code varchar(6) unique,
`name` varchar(255),
address varchar(255),
phone_number varchar(255),
email varchar(255)
);
create table customer(
id int auto_increment primary key,
`name` varchar(255),
phone_number varchar(255),
email varchar(255),
address varchar(255),
age int,
gender bit,
id_user int,
foreign key(id_user) references user(id)
);
-- create table manager_customer(
-- id int auto_increment primary key,
-- id_customer int,
-- quantity int
-- );
create table bill_history (
id int auto_increment primary key,
buy_date date,
id_bill int,
detail varchar(255),
money_total double,
foreign key(id_bill) references bill(id)
);
create table bill (
id int auto_increment primary key,
id_customer int,
payment_method varchar(255),
foreign key(id_customer) references customer(id)
);
create table imports_commodity_history (
id int auto_increment primary key,
date_imports date,
id_commodity int,
foreign key(id_commodity) references commodity(id)
);
create table employee(
id int auto_increment primary key,
`name` varchar(255),
phone_number varchar(255),
email varchar(255),
address varchar(255),
dateOfBirth date,
gender bit,
id_user int,
foreign key(id_user) references user(id)
);
-- create table bill_detail(
-- );