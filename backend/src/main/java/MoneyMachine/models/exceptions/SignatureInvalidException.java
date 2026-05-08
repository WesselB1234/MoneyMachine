package MoneyMachine.models.exceptions;

public class SignatureInvalidException extends RuntimeException {
    public SignatureInvalidException(String message) {
        super(message);
    }
}
