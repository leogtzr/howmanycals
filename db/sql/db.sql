CREATE EXTENSION pgcrypto;

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

CREATE SEQUENCE IF NOT EXISTS meal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS ingredients (
  id INT NOT NULL DEFAULT nextval('ingredient_id_seq'::regclass),
  id_meal INTEGER NOT NULL,
  id_nutrition_ingredient INTEGER NOT NULL,
  
  CONSTRAINT ingredient_pkey PRIMARY KEY (id),
  CONSTRAINT id_nutrition_ingredient_fkey FOREIGN KEY (id_nutrition_ingredient)
        REFERENCES nutrition_ingredient (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE IF NOT EXISTS meal (
  id INT NOT NULL DEFAULT nextval('meal_id_seq'::regclass),
  name varchar(250) NOT NULL,
  notes TEXT,
  creation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
  
  CONSTRAINT meal_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS index_id_meal ON meal(id);
CREATE UNIQUE INDEX IF NOT EXISTS index_name_meal ON meal(name);

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
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('50g Aguacate Hass', 50, 80.0, 'Grasa', 'From the mypalfitness/fatsecret');

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
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('250g Watermelon', 250, 75, 'Fruta', 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('150g manzana roja', 150, 79, 'Fruta', 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('150g manzana roja', 150, 79, 'Fruta', 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('20 Fresas', 0, 100, 'Fruta', 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, category, notes) 
  VALUES('10 Fresas', 0, 40, 'Fruta', 'From fatsecret.com');

INSERT INTO category (name) VALUES('Fruta');
INSERT INTO category (name) VALUES('Carne');
INSERT INTO category (name) VALUES('Verdura');
INSERT INTO category (name) VALUES('Protein');
INSERT INTO category (name) VALUES('Drink');
INSERT INTO category (name) VALUES('Queso');
INSERT INTO category (name) VALUES('Fat');

INSERT INTO meal (name, notes) VALUES('frijolitos', 'Delicioso!'); 

INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 1);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 2);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 3);

/*
SELECT 
    m.id id_meal
    , m.name as name
    , m.creation_date
    , ing.id_nutrition_ingredient 
    , nut.name as ingredient_name
    , nut.grams
    , nut.calories
    , nut.fat
    , nut.sugar
    , nut.carbohydrates
    , nut.protein
    , nut.cholesterol
    , nut.sodium
    , nut.category
    , nut.notes
FROM meal m
INNER JOIN ingredients ing 
    ON m.id = ing.id_meal
INNER JOIN nutrition_ingredient nut
    ON nut.id = ing.id_nutrition_ingredient;
*/