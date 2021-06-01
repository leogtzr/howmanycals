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
  ref_link TEXT,
  
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

INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes, ref_link) 
    VALUES('100g pechuga pollo hervida', 100, 107, 4, 'From "cuantas calorias tiene una pechuga hervida"', 
        'https://www.fatsecret.com.mx/calor%C3%ADas-nutrici%C3%B3n/gen%C3%A9rico/pechuga-de-pollo-hervida'
    );
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes, ref_link) 
    VALUES('200g pechuga pollo hervida', 200, 214, 4, 'From "cuantas calorias tiene una pechuga hervida"',
        'https://www.fatsecret.com.mx/calor%C3%ADas-nutrici%C3%B3n/gen%C3%A9rico/pechuga-de-pollo-hervida'
    );
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('150g pechuga pollo hervida', 150, 160, 4, 'From "cuantas calorias tiene una pechuga hervida"');
INSERT INTO nutrition_ingredient (name, grams, calories, id_category, protein, notes, ref_link) 
    VALUES('2 huevos (110g)', 110, 160, 4, 22.42 * 2, '~', 
    'https://www.fatsecret.com.mx/calor%C3%ADas-nutrici%C3%B3n/gen%C3%A9rico/pechuga-de-pollo-hervida'
    );
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
    VALUES('Tostadas Ligeras DIET', 5, 16.0, 8, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Frijol La Sierra Low Fat', 100, 60.0, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g Frijol La Sierra Low Fat', 50, 30.0, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Hawaiian Papaya', 100, 39.0, 9, 'From fatsecret');
INSERT INTO nutrition_ingredient(name, grams, calories, carbohydrates, protein, id_category, notes) 
    VALUES('100ml Clara Huevo San Juan', 100, 42.0, 1.0, 10.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, carbohydrates, protein, id_category, notes) 
    VALUES('50ml Clara Huevo San Juan', 50, 21.0, 1.0, 10.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, carbohydrates, protein, id_category, notes) 
    VALUES('25ml Clara Huevo San Juan', 25, 21.0 / 2.0, 1.0, 10.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g Yogur Griego Fage 0%', 100, 50.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g Yogur Griego Fage 0%', 50, 50.0 / 2.0, 4, 'From the label');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g yogur griego Chobani', 100, 56.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');
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
    VALUES('50g fresa', 100, 33.0 / 2.0, 1, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('100g eggplant/berenjena', 100, 25, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('50g eggplant/berenjena', 50, 12, 8, '');
INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('25g Onion/Cebolla', 25, 10, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, id_category, notes) 
    VALUES('10g Onion/Cebolla', 10, 4, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g baby carrots', 100, 35.0, -1.0, -1.0, 8.2, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g baby carrots', 50, 18.0, -1.0, -1.0, 8.2, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g coliflor', 100, 25.0, -1.0, -1.0, 8.2, -1.0, -1.0, -1.0, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('1 Pieza pan Thin', 42, 94.0, -1.0, -1.0, 17.0, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g pollo bachoco', 100, 103.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g repollo/cabagge', 100, 24.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g repollo/cabagge', 50, 12.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('157g pechuga california', 157, 153.0, -1.0, -1.0, -1.0, 20.0, -1.0, -1.0, 4, 'Temporal');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g pechuga california', 100, 98.0, -1.0, -1.0, -1.0, 20.0, -1.0, -1.0, 4, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g pechuga california', 50, 98.0 / 2.0, -1.0, -1.0, -1.0, 20.0, -1.0, -1.0, 4, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g manzana Peron Golden', 100, 52.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 9, '');
INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Guarnición Verduras', 100, 24.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('Tortilla Susalia', 16, 33.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g asparagus/espárragos', 50, 10.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Frijoles Negros Enteros La Costena', 100, 45.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 9, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g Frijoles Negros Enteros La Costena', 50, 45.0 / 2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 9, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g pollo Bachoco Mezquite', 100, 108.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g cherry tomato calories', 50, 9.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g lechuga romana orejona', 100, 17.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient (name, grams, calories, id_category, notes) 
    VALUES('250g pechuga pollo hervida', 250, 267, 4, 'From "cuantas calorias tiene una pechuga hervida"');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g calabacitas zucchini', 100, 17.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('10g cebolla', 10, 4.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g mandarina/tangerine', 50, 27.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('1 pieza / Magic Pop', 5, 15.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 8, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('Noodles Konjac', 200, 11.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g blackberries/zarzamora', 100, 44.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g brocoli y coliflor miniflakes', 50, 20.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g melón/Cantaloupe/melon', 100, 34.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('500ml gelatina', 500, 36.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 5, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100ml leche almendra Great Value', 100, 11.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 6, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g zucchini calabacin calaba', 50, 8.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g manzana golden', 50, 25.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes, ref_link) 
    VALUES('Kalipai Snack Xoconostle', 80, 17.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 9, '', 'http://www.codejava.net');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('Bolsa Arroz de Coliflor San Miguel', 241, 35.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Floretes de Broccoli Marketside', 100, 12.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g Floretes de Broccoli Marketside', 50, 6.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('Chips zanahoria Isla Blanca', 50, 45.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Floretes de Coliflor Mr. Lucky', 100, 28.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g Floretes Coliflor Mr. Lucky', 50, 14.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 3, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Camarón/Shrimp pelado Selecta', 100, 98.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g Camarón/Shrimp pelado Selecta', 50, 98.0 / 2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('100g Yogur Skyr Griego', 100, 60.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');

INSERT INTO nutrition_ingredient(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) 
    VALUES('50g Yogur Skyr Griego', 50, 60.0 / 2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 4, '');


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


INSERT INTO meal (name, notes) VALUES('Less than 350cals breakfast', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 45);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 41);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 52);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(3, 66);

INSERT INTO meal (name, notes) VALUES('~560cal burguer', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 1);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 1);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 15);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 53);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 26);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 24);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 58);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 60);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(4, 52);


INSERT INTO meal (name, notes) VALUES('~500cal Cena de todo', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 67);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 67);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 54);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 54);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 68);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 60);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 16);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 26);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 52);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(5, 42);


INSERT INTO meal (name, notes) VALUES('< 400cals breakfast', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 45);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 41);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 52);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 46);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 66);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 26);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(6, 68);

INSERT INTO meal (name, notes) VALUES('~430 cals - super SALADs', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 70);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 71);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 69);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 16);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 15);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 26);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 67);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 38);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(7, 38);

-- Another salad:
INSERT INTO meal (name, notes) VALUES('< 520cal con TODO', 'En realidad son 490 cals, pongo 520 por las salsas y demás.');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 57);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 74);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 26);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 54);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 73);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 75);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 72);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 16);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 69);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 77);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 77);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(8, 50);

INSERT INTO meal (name, notes) 
    VALUES('Breakfast - ~41X cals', 'Tiene un poco de todo, el problema es que por los champiñones se tarda un poco en hacer.');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 57);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 73);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 55);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 27);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 17);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 47);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 48);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 87);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 89);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 81);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(9, 79);

INSERT INTO meal (name, notes) VALUES('~520 meal con arroz de Coliflor/Broocoli', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 2);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 73);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 81);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 81);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 56);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 58);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 91);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 27);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(10, 55);

INSERT INTO meal (name, notes) VALUES('~400 cals - desayuno con gelatina', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 55);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 27);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 81);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 73);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 48);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 87);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 46);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 89);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 18);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(11, 88);

INSERT INTO meal (name, notes) VALUES('< 550 ~ yogur y fresa <3', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 69);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 69);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 70);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 75);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 83);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 83);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 93);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 60);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 95);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 46);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(12, 57);


INSERT INTO meal (name, notes) VALUES('~ 420 cal - breakfast - papaya+yogur+fresa', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 83);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 49);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 89);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 46);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 91);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 57);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 60);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 27);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 75);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 77);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(13, 19);

INSERT INTO meal (name, notes) VALUES('404 not found - breakfast...', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 46);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 57);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 42);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 76);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 27);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 18);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 49);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 90);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 92);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 61);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 44);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(14, 96);

INSERT INTO meal (name, notes) VALUES('Casi 500 - austero pero cumplidor', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 82);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 77);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 96);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 17);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(15, 60);

INSERT INTO meal (name, notes) VALUES('500 cal dinner - high protein', '');
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 3);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 86);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 77);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 102);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 17);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 60);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 43);
INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(16, 98);
