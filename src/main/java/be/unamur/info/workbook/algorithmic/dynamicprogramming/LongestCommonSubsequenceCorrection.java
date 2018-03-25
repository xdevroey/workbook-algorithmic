
package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * LongestCommonSubsequence exercice correction.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class LongestCommonSubsequenceCorrection<T> extends LongestCommonSubsequence<T> {

    public LongestCommonSubsequenceCorrection(T[] a, T[] b) {
        super(a, b);
    }

    @Override
    public int longestSize(int lgtA, int lgtB) {
        if(lgtA == 0 || lgtB == 0){
            return 0;
        } else {
            int i = lgtA - 1;
            int j = lgtB - 1;
            if(a[i].equals(b[j])){
                return 1 + longestSize(lgtA - 1, lgtB - 1);
            } else {
                return Integer.max(longestSize(lgtA - 1, lgtB), longestSize(lgtA, lgtB - 1));
            }
        }
    }

    @Override
    public List<T> longest(int lgtA, int lgtB) {
        List<T> longest;
        if(lgtA == 0 || lgtB == 0){
            longest = Lists.newLinkedList();
        } else {
            int i = lgtA - 1;
            int j = lgtB - 1;
            if(a[i].equals(b[j])){
                longest = longest(lgtA - 1, lgtB - 1);
                longest.add(a[i]);
            } else {
                List<T> head1 = longest(lgtA - 1, lgtB);
                List<T> head2 = longest(lgtA, lgtB - 1);
                if(head1.size() > head2.size()){
                    longest = head1;
                } else {
                    longest = head2;
                }
            }
        }
        return longest;
    }
    
    
    
    

}
