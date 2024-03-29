CREATE DATABASE electronic_component;
USE electronic_component;

CREATE TABLE trademark (
id INT AUTO_INCREMENT PRIMARY KEY,
   `name` VARCHAR(255) NOT NULL,
   `description` LONGTEXT
);

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
   `name` VARCHAR(255) NOT NULL,
   `description` LONGTEXT
);

CREATE TABLE product (
  id INT PRIMARY KEY AUTO_INCREMENT,
  category_id INT NOT NULL,
  trademark_id INT NOT NULL,
  `code` VARCHAR(8),
  `name` VARCHAR(255) NOT NULL,
  create_day DATETIME,
  `description` LONGTEXT,
  price DOUBLE NOT NULL,
  quantity INT NOT NULL,
  flag_delete BIT(1),
  FOREIGN KEY (category_id) REFERENCES category(id),
  FOREIGN KEY (trademark_id) REFERENCES trademark(id)
);

CREATE TABLE image (
id INT PRIMARY KEY AUTO_INCREMENT,
ulr LONGTEXT,
product_id INT NOT NULL,
 FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE `user` (
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL UNIQUE,
`password` VARCHAR(255) NOT NULL,
`name` VARCHAR(255) NOT NULL,
avatar LONGTEXT,
flag_delete BIT(1)
);

CREATE TABLE `role` (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255)
);

CREATE TABLE user_role (
user_id INT NOT NULL,
role_id INT NOT NULL,
PRIMARY KEY (user_id, role_id),
FOREIGN KEY (user_id) REFERENCES `user`(id),
FOREIGN KEY (role_id) REFERENCES `role`(id)
);

CREATE TABLE customer (
  id INT PRIMARY KEY AUTO_INCREMENT,
  gender BIT(1),
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(20) NOT NULL UNIQUE,
  address VARCHAR(255),
  user_id INT NOT NULL,
  flag_delete BIT(1),
  FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE staff (
  id INT PRIMARY KEY AUTO_INCREMENT,
  gender BIT(1),
  id_card VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(20) NOT NULL UNIQUE,
  address VARCHAR(255),
  salary DOUBLE,
  flag_delete BOOLEAN,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE `order` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(8),
  customer_id INT NOT NULL,
  order_date DATE NOT NULL,
  `status` varchar(255),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE order_detail (
  id INT PRIMARY KEY AUTO_INCREMENT,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price DOUBLE NOT NULL,
  FOREIGN KEY (order_id) REFERENCES `order`(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);



