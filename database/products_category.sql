CREATE DATABASE msproducts;
USE msproducts;

CREATE TABLE tb_product (
    id INT NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    description VARCHAR(100) NOT NULL,
    imgURL VARCHAR(1000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_category (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_product_categories(
    product_id INT NOT NULL,
    categories_id INT NOT NULL,
    PRIMARY KEY (product_id, categories_id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id),
    FOREIGN KEY (categories_id) REFERENCES tb_category(id)
)