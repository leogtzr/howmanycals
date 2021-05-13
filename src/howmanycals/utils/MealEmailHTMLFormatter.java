package howmanycals.utils;

import howmanycals.domain.Meal;
import howmanycals.domain.MealNutritionInformation;
import howmanycals.domain.NutritionalIngredient;
import static howmanycals.utils.FormatUtils.formatDecimal1;

public final class MealEmailHTMLFormatter {
    
    private MealEmailHTMLFormatter() {}
    
    public static String format(final Meal meal) {
        final StringBuilder sb = new StringBuilder();
        
        final String mealNameHTML = String.format("<h1>😋🤤 %s, delicious! 🤤😋</h1>", meal.getName());
        
        final String tableTemplate = """
            <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
              overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
              font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg .tg-79vt{background-color:#000000;border-color:inherit;color:#ffffff;
              font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;font-size:20px;font-weight:bold;
              text-align:left;vertical-align:top}
            .tg .tg-75x0{background-color:#000000;border-color:inherit;color:#ffffff;
              font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif !important;;font-size:20px;font-weight:bold;
              text-align:right;vertical-align:top}
            .tg .tg-41rn{background-color:#000000;color:#ffffff;font-family:"Lucida Console", Monaco, monospace !important;;font-size:20px;
              font-weight:bold;text-align:left;vertical-align:top}
            .tg .tg-r0kq{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;text-align:left;vertical-align:top}
            .tg .tg-0lax{text-align:left;vertical-align:top}
            </style>
            <table class="tg">
            <thead>
              <tr>
                <th class="tg-75x0">ID<br></th>
                <th class="tg-79vt">Name</th>
                <th class="tg-79vt">Grams<br></th>
                <th class="tg-41rn">Calories<br></th>
                <th class="tg-41rn">Fat</th>
                <th class="tg-41rn">Sugar</th>
                <th class="tg-41rn">Carbohydrates</th>
                <th class="tg-41rn">Protein</th>
                <th class="tg-41rn">Cholesterol</th>
                <th class="tg-41rn">Sodium</th>
                <th class="tg-41rn">Category</th>
              </tr>
            </thead>
            <tbody>
            @trs@
            </tbody>
            </table>""";
        
        final StringBuilder tds = new StringBuilder();
        
        for (final NutritionalIngredient ingredient : meal.getIngredients()) {
            tds.append(formatIngredientForHTMLTableRow(ingredient));
        }
        
        sb.append(mealNameHTML);
        sb.append(tableTemplate.replace("@trs@", tds.toString()));
        
        sb.append("<hr><br>");
        sb.append("<h2>Summary</h2>");
        SummaryUtil.calculateSummaryFromIngredients(meal.getIngredients()).ifPresent(summary -> {
            sb.append(getSummaryHTMLTable(summary));
        });
        
        return sb.toString();
    }
    
    private static String getSummaryHTMLTable(final MealNutritionInformation info) {
        final String htmlTemplate = """
            <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
              overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
              font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
            </style>
            <table class="tg">
            <thead>
              <tr>
                <th class="tg-0pky">Calories</th>
                <th class="tg-0pky">Protein</th>
                <th class="tg-0pky">Sugar</th>
                <th class="tg-0pky">Carbs</th>
                <th class="tg-0pky">Fat</th>
                <th class="tg-0pky">Cholesterol</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="tg-0pky">@calories@</td>
                <td class="tg-0pky">@protein@</td>
                <td class="tg-0pky">@sugar@</td>
                <td class="tg-0pky">@carbs@</td>
                <td class="tg-0pky">@fat@</td>
                <td class="tg-0pky">@cholesterol@</td>
              </tr>
            </tbody>
            </table>""";

        final StringBuilder sb = new StringBuilder();
        
        sb.append(htmlTemplate.replace("@calories@", formatDecimal1(info.getCalories()))
                .replace("@protein@", formatDecimal1(info.getProtein()))
                .replace("@sugar@", formatDecimal1(info.getSugar()))
                .replace("@carbs@", formatDecimal1(info.getCarbohydrates()))
                .replace("@fat@", formatDecimal1(info.getFat()))
                .replace("@cholesterol@", formatDecimal1(info.getCholesterol())));
        
        return sb.toString();
    }
    
    private static String formatIngredientForHTMLTableRow(final NutritionalIngredient ingredient) {
        final StringBuilder sb = new StringBuilder();
        
        sb.append("<tr>");
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
        sb.append("</tr>");
        sb.append("\n");
        
        return sb.toString();
    }
    
}
