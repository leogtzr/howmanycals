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
    ON nut.id = ing.id_nutrition_ingredient
WHERE m.id = 1;
*/