package be.unamur.info.workbook.algorithmic.recursion;

/**
 * A class representing a integer list element as a couple (value,
 * tail). This class is used by LinkedList to represent simpky chained
 * lists.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class ListElement {
    
    /**
     * The value of the list element.
     */
    private int value;
    
    /**
     * The rest of the list of null if there is none.
     */
    private ListElement tail;

    /**
     * Builds a new list element using the given value and tail.
     * @param value The value of the list element.
     * @param tail The rest of the list or null if there is no rest.
     */
    public ListElement(int value, ListElement tail) {
        this.value = value;
        this.tail = tail;
    }

    /**
     * Returns the value of this list element.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the value rest of the list or null if there is none.
     */
    public ListElement getTail() {
        return tail;
    }

    /**
     * Sets the value of this list element.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the next element of the list or null if there is none.
     */
    public void setTail(ListElement tail) {
        this.tail = tail;
    }

}
