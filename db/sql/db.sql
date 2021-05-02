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
  calories REAL NOT NULL DEFAULT -1.0,
  fat REAL NOT NULL DEFAULT -1.0,
  sugar REAL NOT NULL DEFAULT -1.0,
  carbohydrates REAL NOT NULL DEFAULT -1.0,
  protein REAL NOT NULL DEFAULT -1.0,
  cholesterol REAL NOT NULL DEFAULT -1.0,
  sodium REAL NOT NULL DEFAULT -1.0,
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

-- Pollo
INSERT INTO nutrition_ingredient (name, grams, calories, category, protein, notes) 
  VALUES('100g pechuga pollo hervida', 100, 107, 'Protein', 22.42, 'From "cuantas calorias tiene una pechuga hervida"');
INSERT INTO nutrition_ingredient (name, grams, calories, category, protein, notes) 
  VALUES('200g pechuga pollo hervida', 200, 214, 'Protein', 22.42 * 2, 'From "cuantas calorias tiene una pechuga hervida"');

INSERT INTO nutrition_ingredient (name, grams, calories, category, protein, notes) 
  VALUES('2 huevos (110g)', 110, 160, 'Protein', 22.42 * 2, '~');

INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('Queso Panela Zwan (3)', 90, 216, 'Queso', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('Queso Panela Zwan (1)', 30, 72, 'Queso', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('1 scoop ISOPURE', 31, 103.6, 'Protein', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('1 1/2 scoop ISOPURE', 46.5, 155.4, 'Protein', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('2 scoop ISOPURE', 62, 207.2, 'Protein', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, protein, category, notes) 
  VALUES('1 tuna Tuny', 100, 93, 21, 'Protein', 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('1 Tostada Susalia', 12, 32.0, 'Snack', 'From the label');

-- Algo de verdura:
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
        VALUES('100g esparragos/asparagus', 100, 20.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
        VALUES('100g champiñones', 100, 22.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
        VALUES('150g champiñones', 100, 33.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
      VALUES('100g chile morrón', 100, 24.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
        VALUES('30g chile morrón', 30, 7.2, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
      VALUES('50g chile morrón', 50, 12.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
      VALUES('100g Broccoli', 100, 34.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
      VALUES('30g Broccoli', 30, 10.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
      VALUES('50g Broccoli', 50, 17.0, 'Verdura', 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('2 Jalapeños', 28, 8.0, 'Verdura', 'From Google');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('100g Pepino', 100, 12.0, 'Verdura', 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('200g Pepino', 200, 24.0, 'Verdura', 'From fatsecret.com');
  

      

-- Some categories
insert into category (name) values('Fruta');
insert into category (name) values('Carne');
insert into category (name) values('Verdura');
insert into category (name) values('Protein');
insert into category (name) values('Drink');
insert into category (name) values('Queso');
