package exceptions;

public class NoAvailableSpotException extends Exception{
    String message;
    public NoAvailableSpotException(String message){
        super(message);
    }
}
