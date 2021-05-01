package howmanycals.utils;

public final class FormatUtils {
    
    private FormatUtils() {}
    
    public static String formatDoubleValueForTableVisualisation(final double value) {
        return String.format("%.1f", value);
    }
    
}
