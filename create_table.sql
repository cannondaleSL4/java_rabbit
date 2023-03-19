CREATE TABLE User_Table (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50),
    age INTEGER,
    role INTEGER
);

CREATE TABLE User_Address (
    id SERIAL PRIMARY KEY,
    zip_code INTEGER,
    country VARCHAR(50),
    state VARCHAR(50),
    city VARCHAR(50),
    street VARCHAR(50),
    number_house INTEGER,
    number_apartment INTEGER
);

CREATE TABLE User_Address_User (
    address_id INTEGER REFERENCES User_Address(id),
    user_id INTEGER REFERENCES User_Table(id)
);

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    price numeric(10,2),
    amount INTEGER,
    cost numeric(10,2)
);

CREATE TABLE Users_Order (
    id SERIAL PRIMARY KEY,
    uuid UUID,
    timestamp BIGINT,
    user_id INTEGER REFERENCES User_Table(id),
    address_id INTEGER REFERENCES User_Address(id),
    payed BOOLEAN,
    account_total numeric(10,2),
    CONSTRAINT uuid_timestamp_unique UNIQUE (uuid, timestamp)
);

CREATE TABLE Users_Order_Product (
    order_id INTEGER REFERENCES Users_Order(id),
    product_id INTEGER REFERENCES Product(id)
);

CREATE INDEX user_order_user_account_idx ON Users_Order (user_id, account_total DESC);