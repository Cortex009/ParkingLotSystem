package exceptions;

public class InvalidParkingLotException extends Exception{
    String message;
    public InvalidParkingLotException(String message){
        super(message);
    }
}
