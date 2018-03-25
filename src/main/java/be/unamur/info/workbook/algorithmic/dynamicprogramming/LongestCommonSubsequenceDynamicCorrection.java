package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * LongestCommonSubsequenceDynamic exercice correction
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class LongestCommonSubsequenceDynamicCorrection<T> extends LongestCommonSubsequenceDynamic<T> {

    public LongestCommonSubsequenceDynamicCorrection(T[] a, T[] b) {
        super(a, b);
    }

    @Override
    protected void initMemory() {
        l = new int[a.length + 1][b.length + 1]; // Initialize L to size n+1, m+1 to cover cases L(0,j) and L(i, 0)
        c = new Case[a.length][b.length];
        for (int i = 0; i < l.length; i++) {
            l[i][0] = 0;
        }
        for (int j = 0; j < l[0].length; j++) {
            l[0][j] = 0;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int lgtA = i + 1;
                int lgtB = j + 1;
                if (a[i] == b[j]) {
                    l[lgtA][lgtB] = l[lgtA - 1][lgtB - 1] + 1;
                    c[i][j] = Case.case1;
                } else if (l[lgtA - 1][lgtB] > l[lgtA][lgtB - 1]) {
                    l[lgtA][lgtB] = l[lgtA - 1][lgtB];
                    c[i][j] = Case.case2;
                } else {
                    l[lgtA][lgtB] = l[lgtA][lgtB - 1];
                    c[i][j] = Case.case3;
                }
            }
        }
    }

    @Override
    public int longestSize(int lgtA, int lgtB) {
        if (l == null) {
            initMemory();
        }
        return l[lgtA][lgtB];
    }

    @Override
    public List<T> longest(int lgtA, int lgtB) {
        List<T> longest = Lists.newLinkedList();
        if (lgtA == 0 || lgtB == 0) {
            return longest;
        }
        if (c == null) {
            initMemory();
        }
        int i = lgtA - 1;
        int j = lgtB - 1;
        while (i >= 0 && j >= 0) {
            switch (c[i][j]) {
                case case1:
                    longest.add(0, a[i]);
                    i = i - 1;
                    j = j - 1;
                    break;
                case case2:
                    i = i - 1;
                    break;
                case case3:
                    j = j - 1;
                    break;
            }
        }
        return longest;
    }

}
