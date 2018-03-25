
package be.unamur.info.workbook.algorithmic.abstractdatatype;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class StacksCorrection extends Stacks{
    
    // Singleton pattern implementation 
    public static final Stacks STACKS = new StacksCorrection();

    private StacksCorrection() {
    }

    @Override
    public <T> Stack reverse(ImmutableStack<T> stack) {
        Stack<T> reversed = new ImmutableStack();
        while (!stack.empty()) {
            reversed = reversed.push(stack.top());
            stack = stack.pop();
        }
        return reversed;
    }

    @Override
    public <T> Stack<T> zip(ImmutableStack<T> first, ImmutableStack<T> second) {
        ImmutableStack<T> zipped = new ImmutableStack();
        while (!(first.empty() || second.empty())) {
            zipped = zipped.push(first.top()).push(second.top());
            first = first.pop();
            second = second.pop();
        }
        while (!first.empty()) {
            zipped = zipped.push(first.top());
            first = first.pop();
        }
        while (!second.empty()) {
            zipped = zipped.push(second.top());
            second = second.pop();
        }
        return reverse(zipped);
    }

    @Override
    public <T> Stack<T>[] unzip(ImmutableStack<T> stack, int n) {
        assert (n > 0);
        ImmutableStack<T>[] unzipped = new ImmutableStack[n];
        for (int i = 0; i < unzipped.length; i++) {
            unzipped[i] = new ImmutableStack();
        }
        while (!stack.empty()) {
            for (int i = 0; i < unzipped.length && !stack.empty(); i++) {
                unzipped[i] = unzipped[i].push(stack.top());
                stack = stack.pop();
            }
        }
        for (int i = 0; i < unzipped.length; i++) {
            unzipped[i] = (ImmutableStack) reverse(unzipped[i]);
        }
        return unzipped;
    }

}
