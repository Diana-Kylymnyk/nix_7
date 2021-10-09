package exception;

public class NotFoundException extends Exception {

    private final String message;

    public NotFoundException(String message) {
        this.message = "Not found " + message;
    }

    @Override
    public String toString() {
        return message;
    }
}
