CREATE TABLE IF NOT EXISTS customer(
    id VARCHAR(50)PRIMARY KEY ,
    name VARCHAR(100)NOT NULL ,
    number INT NOT NULL,
    salary DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS item(
    code VARCHAR(50)PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    qty INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    order_id VARCHAR(50) NOT NULL PRIMARY KEY ,
    customer_id VARCHAR(50) NOT NULL,
    item_code VARCHAR(50) NOT NULL,
    date VARCHAR(50)NOT NULL,
    qty INT NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (item_code) REFERENCES item(code)
);