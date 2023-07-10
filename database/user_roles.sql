CREATE DATABASE msusers;
USE msusers;


CREATE TABLE roles (
                       id INT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL
);

USE msusers;
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      first_name VARCHAR(50) NOT NULL,
                      last_name VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      roles VARCHAR(50) NOT NULL
);

USE msusers;
CREATE TABLE user_roles (
                            user_id INT,
                            role_id INT,
                            FOREIGN KEY (user_id) REFERENCES user(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);