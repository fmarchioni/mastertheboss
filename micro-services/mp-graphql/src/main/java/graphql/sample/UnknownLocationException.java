

package graphql.sample;

public class UnknownLocationException extends Exception {
    private static final long serialVersionUID = 10067834876L;

    public UnknownLocationException(String location) {
        super(location);
    }
}
