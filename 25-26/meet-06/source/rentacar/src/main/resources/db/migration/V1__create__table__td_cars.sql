CREATE TABLE td_cars(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256),
    model VARCHAR(256),
    type VARCHAR(256),
    price_for_rent DOUBLE,

    -- SOFT DELETE pointer
    is_enabled  INT DEFAULT 1
)