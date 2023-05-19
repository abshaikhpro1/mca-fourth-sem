-- migration/V1__Create_Product_Table.sql

CREATE TABLE Product (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL
);
