CREATE TABLE IF NOT EXISTS td_products(

    id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(128),
    company_short_name CHAR(3),
    buy_price DOUBLE,
    sell_price DOUBLE,
    is_active INT DEFAULT 1
);