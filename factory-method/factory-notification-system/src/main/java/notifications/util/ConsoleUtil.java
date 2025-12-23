package notifications.util;

public class ConsoleUtil {
    
    private static final int LINE_LENGTH = 80;
    
    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(LINE_LENGTH));
        printCentered(title);
        System.out.println("=".repeat(LINE_LENGTH) + "\n");
    }
    
    public static void printFooter(String title) {
        System.out.println("\n" + "=".repeat(LINE_LENGTH));
        printCentered(title);
        System.out.println("=".repeat(LINE_LENGTH) + "\n");
    }
    
    public static void printSectionTitle(String title) {
        System.out.println("\n" + "-".repeat(LINE_LENGTH));
        System.out.println("  " + title);
        System.out.println("-".repeat(LINE_LENGTH) + "\n");
    }
    
    private static void printCentered(String text) {
        int padding = (LINE_LENGTH - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }
}

