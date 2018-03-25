package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import java.util.Objects;

/**
 * This class represents pairs of values.
 *
 * @param <F> The type of the first element in the pair.
 * @param <S> The type of the second element in the pair.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Pair<F, S> {

    /**
     * The first element of the pair.
     */
    private F first;

    /**
     * The second element of the pair.
     */
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element of the pair.
     *
     * @effects Returns the first element of the pair.
     */
    public F getFirst() {
        return first;
    }

    /**
     * Returns the second element of the pair.
     *
     * @effects Returns the second element of the pair.
     */
    public S getSecond() {
        return second;
    }

    /**
     * Sets the value of the first element in the pair.
     *
     * @effects this'.first = first.
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Sets the value of the second element in the pair.
     *
     * @effects this'.second = second.
     */
    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.first);
        hash = 37 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }

}
