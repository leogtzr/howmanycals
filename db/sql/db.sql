CREATE EXTENSION pgcrypto;

CREATE SEQUENCE IF NOT EXISTS nutrition_ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

CREATE UNIQUE INDEX IF NOT EXISTS index_id_category ON category(id);

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
  id_category INTEGER NOT NULL,
  notes VARCHAR(150) NOT NULL,
  
  PRIMARY KEY (id),
  CONSTRAINT id_category_fkey FOREIGN KEY (id_category)
    REFERENCES category(id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
);

CREATE UNIQUE INDEX IF NOT EXISTS index_id_nutrition_ingredient ON nutrition_ingredient(id);

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

CREATE UNIQUE INDEX IF NOT EXISTS index_id_ingredients ON ingredients(id);

CREATE TABLE IF NOT EXISTS meal (
  id INT NOT NULL DEFAULT nextval('meal_id_seq'::regclass),
  name varchar(250) NOT NULL,
  notes TEXT,
  creation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
  
  CONSTRAINT meal_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS index_id_meal ON meal(id);
CREATE UNIQUE INDEX IF NOT EXISTS index_name_meal ON meal(name);

CREATE SEQUENCE IF NOT EXISTS note_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE IF NOT EXISTS note (
  id INT NOT NULL DEFAULT nextval('note_id_seq'::regclass),
  note TEXT NOT NULL,
  creation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
  
  CONSTRAINT note_pkey PRIMARY KEY (id)  
);

CREATE UNIQUE INDEX IF NOT EXISTS index_id_note ON note(id);

INSERT INTO category (name) VALUES('Fruta');
INSERT INTO category (name) VALUES('Carne');
INSERT INTO category (name) VALUES('Verdura');
INSERT INTO category (name) VALUES('Protein');
INSERT INTO category (name) VALUES('Drink');
INSERT INTO category (name) VALUES('Lacteal');
INSERT INTO category (name) VALUES('Fat');
INSERT INTO category (name) VALUES('Carbs');
INSERT INTO category (name) VALUES('Fibra');

INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g pechuga pollo hervida', 100, 107, 4, 'From "cuantas calorias tiene una pechuga hervida"');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('200g pechuga pollo hervida', 200, 214, 4, 'From "cuantas calorias tiene una pechuga hervida"');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, protein, notes) 
    VALUES('2 huevos (110g)', 110, 160, 4, 22.42 * 2, '~');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('Queso Panela Zwan (3)', 90, 216, 6, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('Queso Panela Zwan (1)', 30, 72, 6, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('1 scoop ISOPURE Fresa', 31, 103.6, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('1 scoop ISOPURE Unflavored', 29, 100, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('2 scoop ISOPURE Unflavored', 58, 200, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('1 1/2 scoop ISOPURE Unflavored', 43.5, 150, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('1 1/2 scoop ISOPURE', 46.5, 155.4, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('2 scoop ISOPURE', 62, 207.2, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, protein, id_category, notes) 
    VALUES('1 tuna Tuny', 100, 93, 21, 4, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('1 Tostada Susalia', 12, 32.0, 8, 'From the label');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('50g Aguacate Hass', 50, 80.0, 7, 'From the mypalfitness/fatsecret');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g esparragos/asparagus', 100, 20.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g champiñones/mushrooms', 100, 22.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('50g champiñones/mushrooms', 50, 11.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('30g champiñones/mushrooms', 30, 7.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('150g champiñones', 100, 33.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g chile morrón', 100, 24.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('30g chile morrón', 30, 7.2, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('50g chile morrón', 50, 12.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g Broccoli', 100, 34.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('30g Broccoli', 30, 10.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('50g Broccoli', 50, 17.0, 3, 'From fatsecret.cl');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('2 Jalapeños', 28, 8.0, 3, 'From Google');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g Pepino', 100, 12.0, 3, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('50g Pepino', 50, 6.0, 3, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('200g Pepino', 200, 24.0, 3, 'From fatsecret.com');      
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('250g Watermelon', 250, 75, 3, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g Watermelon', 100, 30, 3, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('150g manzana roja', 150, 79, 9, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('150g manzana roja', 150, 79, 9, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g golden delicious apple/manzana', 100, 52, 9, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('20 Fresas', 0, 100, 1, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('10 Fresas', 0, 40, 1, 'From fatsecret.com');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('100g Granny Smith', 100.0, 52, 9, 'From fatsecret.com');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('Tostadas Ligeras', 5, 16.0, 8, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Frijol La Sierra Low Fat', 100, 60.0, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g Frijol La Sierra Low Fat', 50, 30.0, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Hawaiian Papaya', 100, 39.0, 9, 'From fatsecret');
INSERT INTO nutrition_ingredient(name, grams, calories, carbohydrates, protein, id_category, notes) 
    VALUES('100ml Clara Huevo San Juan', 100, 42.0, 1.0, 10.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Yogur Griego Fage 0%', 100, 50.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('18g avena Quaker', 18, 61.0, 9, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100ml Leche Almendra', 100, 12.0, 7, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g banana/platano', 50, 44.0, 9, 'From fatsecret');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('150g fresas/strawberries', 150, 48.0, 9, 'From fatsecret'); 
INSERT INTO nutrition_ingredient(name, grams, calories, protein, id_category, notes) 
    VALUES('100g Ham/Jamón Pavo Virginia Low Fat Cuida-t', 100, 100.0, 12, 4, 'Parece que cada rebanada ~ 20g');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('Rebanada Ham/Jam Pavo Virginia LowFat Cuida-t', 20, 20, 4, 'Parece que cada rebanada ~ 20g');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('3 rebnds Ham/Jam Pavo Virginia LowFat Cuida-t', 60, 60, 4, 'Parece que cada rebanada ~ 20g');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g tomate', 50, 9.0, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g fresa', 100, 33.0, 1, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g eggplant/berenjena', 100, 25, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g eggplant/berenjena', 50, 12, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('25g Onion/Cebolla', 25, 10, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g baby carrots', 100, 35.0, -1.0, -1.0, 8.2, -1.0, -1.0, -1.0, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g coliflor', 100, 25.0, -1.0, -1.0, 8.2, -1.0, -1.0, -1.0, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('1 Pieza pan Thin', 42, 94.0, -1.0, -1.0, 17.0, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g pollo bachoco', 100, 103.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g repollo/cabagge', 100, 24.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g pechuga california', 100, 98.0, -1.0, -1.0, -1.0, 20.0, -1.0, -1.0, 4, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g manzana Peron Golden', 100, 52.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 9, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Guarnición Verduras', 100, 24.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('Tortilla Susalia', 16, 33.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g asparagus/espárragos', 50, 10.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');


-- Test data:
INSERT INTO note (note) VALUES('The note 1');
INSERT INTO note (note) VALUES('The note 2');

INSERT INTO meal (name, notes) VALUES('600 cals - ham-egg-frijol', 'Parece que tiene algo de proteina.');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 34);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 2);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 36);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 36);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 41);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 21);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 16);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 24);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(1, 19);


INSERT INTO meal (name, notes) VALUES('~350 cal - huevos/frijol/avena/yogur/papaya', 'Un poco de todo, sabe bien.');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 36);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 36);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 34);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 38);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 35);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 37);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 21);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 24);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(2, 20);

