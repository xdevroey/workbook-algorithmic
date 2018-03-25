package be.unamur.info.workbook.algorithmic.abstractdatatype;

/**
 * This class is implemented as a singleton with a set of utility methods
 * manipulating Stack&lt;T&gt; objects.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Stacks {

    // Singleton pattern implementation 
    public static final Stacks STACKS = new Stacks();

    protected Stacks() {
    }

    /**
     *
     */
    public <T> Stack reverse(ImmutableStack<T> stack) {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * 
     */
    public <T> Stack<T> zip(ImmutableStack<T> first, ImmutableStack<T> second) {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * 
     */
    public <T> Stack<T>[] unzip(ImmutableStack<T> stack, int n) {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
