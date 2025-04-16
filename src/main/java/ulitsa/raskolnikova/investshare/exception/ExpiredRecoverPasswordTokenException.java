package ulitsa.raskolnikova.investshare.exception;

public class ExpiredRecoverPasswordTokenException extends RuntimeException {
    public ExpiredRecoverPasswordTokenException(String message) {
        super(message);
    }
}
