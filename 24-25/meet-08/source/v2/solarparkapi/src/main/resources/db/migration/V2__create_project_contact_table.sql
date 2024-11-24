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


CREATE TABLE IF NOT EXISTS tc_project_contact(
    project_id INT,
    contact_id INT,
    PRIMARY KEY(project_id, contact_id)
);