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