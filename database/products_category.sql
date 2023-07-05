USE challenge;
CREATE TABLE categories (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

use challenge;
CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  date DATE,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(10, 2) NOT NULL,
  img_url VARCHAR(255)
);

USE challenge;
CREATE TABLE product_categories (
  product_id INT,
  category_id INT,
  FOREIGN KEY (product_id) REFERENCES products(id),
  FOREIGN KEY (category_id) REFERENCES categories(id),
  PRIMARY KEY (product_id, category_id)
);

USE challenge;
-- Inserir dados na tabela categories
INSERT INTO categories (id, name) VALUES
(1, 'Category 1'),
(2, 'Category 2'),
(3, 'Category 3'),
(4, 'Category 4'),
(5, 'Category 5');

USE challenge;
-- Inserir dados na tabela products
INSERT INTO products (date, name, description, price, img_url)
VALUES
  ('2023-01-01', 'Product 1', 'Description 1', 19.99, 'https://example.com/image1.jpg'),
  ('2023-02-01', 'Product 2', 'Description 2', 29.99, 'https://example.com/image2.jpg'),
  ('2023-03-01', 'Product 3', 'Description 3', 39.99, 'https://example.com/image3.jpg'),
  ('2023-04-01', 'Product 4', 'Description 4', 49.99, 'https://example.com/image4.jpg'),
  ('2023-05-01', 'Product 5', 'Description 5', 59.99, 'https://example.com/image5.jpg'),
  ('2023-06-01', 'Product 6', 'Description 6', 69.99, 'https://example.com/image6.jpg'),
  ('2023-07-01', 'Product 7', 'Description 7', 79.99, 'https://example.com/image7.jpg'),
  ('2023-08-01', 'Product 8', 'Description 8', 89.99, 'https://example.com/image8.jpg'),
  ('2023-09-01', 'Product 9', 'Description 9', 99.99, 'https://example.com/image9.jpg'),
  ('2023-10-01', 'Product 10', 'Description 10', 109.99, 'https://example.com/image10.jpg');


USE challenge;
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1),
(7, 2),
(8, 3),
(9, 4),
(10, 5);