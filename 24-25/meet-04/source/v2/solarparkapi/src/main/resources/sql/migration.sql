-- Потребители
CREATE TABLE IF NOT EXISTS td_customers(
                                           id INT PRIMARY KEY AUTO_INCREMENT,
                                           name VARCHAR(256),
    number_of_projects INT DEFAULT 0,
    is_active INT DEFAULT 1
    );

-- проекти
CREATE TABLE IF NOT EXISTS td_projects(

                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(256),
    cost NUMBER,
    customer_id INT,
    is_active INT DEFAULT 1

    );

-- Контакти
CREATE TABLE IF NOT EXISTS td_contacts(
                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          first_name VARCHAR(256),
    last_name VARCHAR(256),
    email VARCHAR(256),
    phone VARCHAR(256),
    is_active INT DEFAULT 1
    );


-- Оценки на обекти
CREATE TABLE IF NOT EXISTS td_sites(
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(256),
    address VARCHAR(256),
    config_cost NUMBER,
    other_cost NUMBER,
    project_id INT,
    is_active INT DEFAULT 1
    );

CREATE TABLE IF NOT EXISTS tc_project_contact(
                                                 project_id INT,
                                                 contact_id INT,
                                                 PRIMARY KEY(project_id, contact_id)
    );

DROP TABLE td_customers;
DROP TABLE td_projects;
DROP TABLE td_contacts;
DROP TABLE td_sites;
DROP TABLE tc_project_contact;