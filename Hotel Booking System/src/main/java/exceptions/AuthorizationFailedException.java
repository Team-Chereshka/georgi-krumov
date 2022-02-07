package exceptions;

public class AuthorizationFailedException extends Throwable {

    public AuthorizationFailedException(String message){
        super(message);
    }
}
