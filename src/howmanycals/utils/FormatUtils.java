package howmanycals.utils;

public final class FormatUtils {
    
    private FormatUtils() {}
    
    public static String formatDecimal1(final double value) {
        return String.format("%.1f", value);
    }
    
}
