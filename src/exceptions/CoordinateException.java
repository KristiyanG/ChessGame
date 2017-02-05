package exceptions;

public class CoordinateException extends Exception {

	private int xCoordinate;
	private int yCoordinate;

	//get invalid data
	public CoordinateException(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	@Override
	public String getMessage() {
		String msg = xCoordinate + " and " + yCoordinate
				+ " are not allow. Please enter values for x and y between 1 and 3 !";
		return msg;
	}
}
