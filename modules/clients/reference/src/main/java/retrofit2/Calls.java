package retrofit2;

/**
 * Miscellaneous utility functions for dealing with {@link Call}.
 *
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
public final class Calls {

    private Calls() {
    }

    /**
     * Used by workarounds for SI-151.
     *
     * @see Call#clone()
     * @see <a href="https://stackoverflow.com/questions/25729827">Stack Overflow: ''Call Java method from Scala named “clone”''</a>
     * @see <a href="https://issues.scala-lang.org/browse/SI-151">SI-151</a>
     */
    public static <T> Call<T> copy(Call<T> call) {
        return call.clone();
    }

}
