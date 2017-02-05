package exceptions;

public class CanNotMoveException extends Exception {

	
	@Override
	public String getMessage() {
		return "You can not move the knight from this position !";
	}
}
