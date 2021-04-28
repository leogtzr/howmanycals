CREATE EXTENSION pgcrypto;

CREATE SEQUENCE IF NOT EXISTS product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Creation of product table
CREATE TABLE IF NOT EXISTS product (
  id INT NOT NULL DEFAULT nextval('product_id_seq'::regclass),
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

insert into product (id, name) values(1, 'beer');

-- Creation of order_status table
-- CREATE TABLE IF NOT EXISTS order_status (
--   order_status_id varchar(200) NOT NULL,
--   update_at TIMESTAMP,
--   sale_id varchar(200) NOT NULL,
--   status_name_id INT NOT NULL,
--   PRIMARY KEY (order_status_id),
--   CONSTRAINT fk_sale
--       FOREIGN KEY(sale_id)
--     REFERENCES sale(sale_id),
--   CONSTRAINT fk_status_name
--       FOREIGN KEY(status_name_id)
--     REFERENCES status_name(status_name_id)
-- );
