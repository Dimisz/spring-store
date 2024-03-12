DROP TABLE IF EXISTS cart_product_mapping;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS product;

CREATE TABLE carts (
     id BIGINT AUTO_INCREMENT NOT NULL,
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
     cart_product INTEGER,
     cart_id BIGINT NOT NULL,
     product_id BIGINT NOT NULL,
     PRIMARY KEY (cart_id, product_id),
     FOREIGN KEY (cart_id) REFERENCES carts (id)
);