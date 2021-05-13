package howmanycals.utils;

import howmanycals.domain.Meal;
import howmanycals.domain.NutritionalIngredient;
import static howmanycals.utils.FormatUtils.formatDecimal1;

public final class MealEmailHTMLFormatter {
    
    private MealEmailHTMLFormatter() {}
    
    public static String format(final Meal meal) {
        final StringBuilder sb = new StringBuilder();
        
        final String mealName = meal.getName();
        final String mealNameHTML = String.format("<h1>😋🤤 %s, delicious! 🤤😋</h1>", meal.getName());
        
        final String tableTemplate = "<style type=\"text/css\">\n" +
".tg  {border-collapse:collapse;border-spacing:0;}\n" +
".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
"  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
"  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
".tg .tg-79vt{background-color:#000000;border-color:inherit;color:#ffffff;\n" +
"  font-family:\"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif !important;;font-size:20px;font-weight:bold;\n" +
"  text-align:left;vertical-align:top}\n" +
".tg .tg-75x0{background-color:#000000;border-color:inherit;color:#ffffff;\n" +
"  font-family:\"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif !important;;font-size:20px;font-weight:bold;\n" +
"  text-align:right;vertical-align:top}\n" +
".tg .tg-41rn{background-color:#000000;color:#ffffff;font-family:\"Lucida Console\", Monaco, monospace !important;;font-size:20px;\n" +
"  font-weight:bold;text-align:left;vertical-align:top}\n" +
".tg .tg-r0kq{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;text-align:left;vertical-align:top}\n" +
".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
"</style>\n" +
"<table class=\"tg\">\n" +
"<thead>\n" +
"  <tr>\n" +
"    <th class=\"tg-75x0\">ID<br></th>\n" +
"    <th class=\"tg-79vt\">Name</th>\n" +
"    <th class=\"tg-79vt\">Grams<br></th>\n" +
"    <th class=\"tg-41rn\">Calories<br></th>\n" +
"    <th class=\"tg-41rn\">Fat</th>\n" +
"    <th class=\"tg-41rn\">Sugar</th>\n" +
"    <th class=\"tg-41rn\">Carbohydrates</th>\n" +
"    <th class=\"tg-41rn\">Protein</th>\n" +
"    <th class=\"tg-41rn\">Cholesterol</th>\n" +
"    <th class=\"tg-41rn\">Sodium</th>\n" +
"    <th class=\"tg-41rn\">Category</th>\n" +
"  </tr>\n" +
"</thead>\n" +
"<tbody>\n" +
"  <tr>\n" +
"    @tds@\n" +
"  </tr>\n" +
"</tbody>\n" +
"</table>";
        
        final StringBuilder tds = new StringBuilder();
        
        for (final NutritionalIngredient ingredient : meal.getIngredients()) {
            tds.append(formatIngredientForHTMLTableRow(ingredient));
        }
        
        sb.append(mealNameHTML);
        
        return sb.toString();
    }
    
    private static String formatIngredientForHTMLTableRow(final NutritionalIngredient ingredient) {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("<td class='tg-r0kq'>%d</td>", ingredient.getId()));
        sb.append(String.format("<td class='tg-r0kq'>%s</td>", ingredient.getName()));
        sb.append(String.format("<td class='tg-r0kq'>%d</td>", ingredient.getGrams()));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getCalories())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getFat())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getSugar())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getCarbohydrates())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getProtein())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getCholesterol())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", formatDecimal1(ingredient.getSodium())));
        sb.append(String.format("<td class='tg-0lax'>%s</td>", ingredient.getCategory().getName()));
        
        return sb.toString();
    }
    
}
