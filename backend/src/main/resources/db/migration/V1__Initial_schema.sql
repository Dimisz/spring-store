CREATE TABLE product (
      id                    BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
      name                  varchar(255) NOT NULL,
      description           varchar(255) NOT NULL,
      price                 float8 NOT NULL,
      picture_url           varchar(255) NOT NULL,
      category              varchar(255) NOT NULL,
      brand                 varchar(255) NOT NULL,
      quantity_in_stock     int NOT NULL,
      created_date          timestamp NOT NULL,
      last_modified_date    timestamp NOT NULL,
      version               int NOT NULL
);