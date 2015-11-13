package lab3.utility;

import java.util.regex.Pattern;

/**
 * Util:
 * <brief description of class>
 */
public class Util {

    public static Pattern whitespace = Pattern.compile("\\s+");

    public Util() {
    }

    public static String[] words(String var0) {
        return whitespace.split(var0);
    }

    private static int minimum(int var0, int var1, int var2) {
        return Math.min(Math.min(var0, var1), var2);
    }

    public static int editDistance(String var0, String var1) {
        int[][] var2 = new int[var0.length() + 1][var1.length() + 1];

        int var3;
        for(var3 = 0; var3 <= var0.length(); var2[var3][0] = var3++) {
            ;
        }

        for(var3 = 1; var3 <= var1.length(); var2[0][var3] = var3++) {
            ;
        }

        for(var3 = 1; var3 <= var0.length(); ++var3) {
            for(int var4 = 1; var4 <= var1.length(); ++var4) {
                var2[var3][var4] = minimum(var2[var3 - 1][var4] + 1, var2[var3][var4 - 1] + 1, var2[var3 - 1][var4 - 1] + (var0.charAt(var3 - 1) == var1.charAt(var4 - 1)?0:1));
            }
        }

        return var2[var0.length()][var1.length()];
    }
}