package be.unamur.info.workbook.algorithmic.complexity;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class NumericCharsCountCorrection {

    public static int numericCharsCountIterative(char[] tab) {
        int cpt = 0;
        for (int i = 0; i < tab.length; i++) {
            if ('0' <= tab[i] && tab[i] <= '9') {
                cpt++;
            }
        }
        return cpt;
    }

    public static int numericCharsCountRecursive(char[] tab) {
        return numericCharsCountRecursive(tab, 0);
    }

    private static int numericCharsCountRecursive(char[] tab, int begin) {
        if (begin >= tab.length) {
            return 0;
        } else if ('0' <= tab[begin] && tab[begin] <= '9') {
            return 1 + numericCharsCountRecursive(tab, begin + 1);
        } else {
            return numericCharsCountRecursive(tab, begin + 1);
        }
    }

}
