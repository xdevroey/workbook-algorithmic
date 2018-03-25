package be.unamur.info.workbook.algorithmic.abstractdatatype;

/**
 * This interface defines an abstract stack data type of T elements. A stack S =
 * [v1,v2,...,vn] where vi elements are of type T.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public interface Stack<T> {

    /**
     * Returns a stack with the given value added to the top.
     *
     * @requires this = [v1,...,vn]
     * @effects return = [v,v1,...,vn]
     */
    public Stack push(T value);

    /**
     * Returns a stack where the top value has been removed.s
     *
     * @requires this = [v,v1,...,vn] and not this.empty()
     * @effects return = [v1,...,vn]
     */
    public Stack pop();

    /**
     * Returns true if this stack is empty.
     *
     * @effects return = true if this = [], false otherwise
     */
    public boolean empty();

    /**
     * Returns the value at the top of the stack.
     *
     * @requires this = [v1,...,vn] and not this.empty()
     * @effects return = v1
     */
    public T top();

    /**
     * Returns the number of elements in this stack.
     *
     * @requires this = [v1,...,vn]
     * @effects return = n
     */
    public int size();

}
