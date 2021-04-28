CREATE EXTENSION pgcrypto;

CREATE SEQUENCE IF NOT EXISTS meal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS meal (
  id INT NOT NULL DEFAULT nextval('meal_id_seq'::regclass),
  name varchar(250) NOT NULL,
  CONSTRAINT meal_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS nutrition_ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS nutrition_ingredient (
  id INT NOT NULL DEFAULT nextval('nutrition_ingredient_id_seq'::regclass),
  name VARCHAR(250) NOT NULL,
  grams INTEGER NOT NULL,
  calories REAL NOT NULL,
  fat REAL NOT NULL,
  sugar REAL NOT NULL,
  carbohydrates REAL NOT NULL,
  protein REAL NOT NULL,
  cholesterol REAL NOT NULL,
  sodium REAL NOT NULL,
  category VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS category (
  id INT NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  name varchar(250) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- Data:

---- Some ingredients:
insert into nutrition_ingredient (name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, category) 
  values('Ejote', 75.0, -1, -1, -1, -1, -1, -1, -1, 'verdura');

-- Some categories
insert into category (name) values('fruta');
insert into category (name) values('carne');
insert into category (name) values('verdura');
