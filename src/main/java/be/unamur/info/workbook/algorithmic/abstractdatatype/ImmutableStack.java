package be.unamur.info.workbook.algorithmic.abstractdatatype;

/**
 * This class represents immutable stacks. Each time a value is added or removed
 * using the push and pop methods, a new stack is created and returned.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class ImmutableStack<T> implements Stack<T> {

    // INV: (size == 0 <=> top == null) && sequence of elements in top is loop free
    private SequenceElement<T> top;
    private int size;

    /**
     * Creates a new empty immutable stack.
     *
     * @modifies this
     * @effect this = []
     */
    public ImmutableStack() {
        size = 0;
    }

    /**
     * Creates a new immutable stack using the given top and size values.
     *
     * @modifies this
     * @effects this.top = top and this.size = newSize
     */
    private ImmutableStack(SequenceElement top, int newSize) {
        this.top = top;
        size = newSize;
    }

    @Override
    public ImmutableStack push(T value) {
        return new ImmutableStack(new SequenceElement(value, top), size + 1);
    }

    @Override
    public ImmutableStack pop() {
        return new ImmutableStack(this.top.getTail(), size - 1);
    }

    @Override
    public boolean empty() {
        return this.top == null;
    }

    @Override
    public T top() {
        return top.getValue();
    }

    @Override
    public int size() {
        return size;
    }

}
