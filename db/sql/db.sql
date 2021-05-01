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
  notes VARCHAR(150) NOT NULL,
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
INSERT INTO nutrition_ingredient (name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, category, notes) 
  VALUES('Pechuga Pollo', 200.0, 390, -1, -1, -1, 59.1, -1, -1, 'Protein', 'From fatsecret');
INSERT INTO nutrition_ingredient (name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, category, notes) 
  VALUES('Queso manchego', 100, 23, -1, -1, -1, -1, -1, -1, 'Grasa', 'pal...x');

-- Some categories
insert into category (name) values('Fruta');
insert into category (name) values('Carne');
insert into category (name) values('Verdura');
insert into category (name) values('Protein');
insert into category (name) values('Drink');
