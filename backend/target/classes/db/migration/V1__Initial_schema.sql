DROP TABLE IF EXISTS cart_product_mapping;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS app_user_seq;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_product_mapping;

CREATE TABLE app_user (
      id BIGINT AUTO_INCREMENT NOT NULL,
      email VARCHAR(255),
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      full_name VARCHAR(255),
      address1 VARCHAR(255),
      address2 VARCHAR(255),
      city VARCHAR(255),
      state VARCHAR(255),
      zip VARCHAR(255),
      country VARCHAR(255),
      user_password VARCHAR(255),
      role enum ('USER','ADMIN'),
      PRIMARY KEY (id)
);

CREATE TABLE carts (
     id varchar(255) NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE product (
      id                    BIGINT AUTO_INCREMENT NOT NULL,
      name                  varchar(255) NOT NULL,
      description           varchar(255) NOT NULL,
      price                 float8 NOT NULL,
      picture_url           varchar(255) NOT NULL,
      category              varchar(255) NOT NULL,
      brand                 varchar(255) NOT NULL,
      quantity_in_stock     int NOT NULL,
      created_date          timestamp NOT NULL,
      last_modified_date    timestamp NOT NULL,
      version               int NOT NULL,
      PRIMARY KEY (id)
);

CREATE TABLE cart_product_mapping (
     product_qty INTEGER,
     cart_id varchar(255) NOT NULL,
     product_id BIGINT NOT NULL,
     PRIMARY KEY (cart_id, product_id),
     FOREIGN KEY (cart_id) REFERENCES carts (id)
     ON DELETE CASCADE
);

CREATE TABLE orders (
     id BIGINT NOT NULL AUTO_INCREMENT,
     order_date DATETIME(6),
     buyer_id VARCHAR(255),
     full_name VARCHAR(255),
     address1 VARCHAR(255),
     address2 VARCHAR(255),
     city VARCHAR(255),
     state VARCHAR(255),
     zip VARCHAR(255),
     country VARCHAR(255),
     payment_intent_id VARCHAR(255),
     order_status enum ('PENDING','PAYMENT_RECEIVED', 'PAYMENT_FAILED'),
     primary key (id)
);

CREATE TABLE order_product_mapping (
     product_qty INTEGER,
     order_id BIGINT NOT NULL,
     product_id BIGINT NOT NULL,
     PRIMARY KEY (order_id, product_id),
     FOREIGN KEY (order_id) REFERENCES orders (id)
     ON DELETE CASCADE
);