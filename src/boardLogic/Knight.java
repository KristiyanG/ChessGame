package boardLogic;

import exceptions.CanNotMoveException;
import exceptions.CoordinateException;

public class Knight extends Thread {
	private static final int START_POSITION_OF_ARRAY = 0;
	private static final int MIN_COORDINATE = 0;
	private static final int MAX_COORDINATE = 2;
	private static final int START_COUNT_STEPS = 0;
	private static int[] xCoordinates = { 0, 0, 0, 1, 1, 1, 2, 2, 2 };// xCoordinates
																		// and
																		// yCoordinates
																		// make
																		// combination
	private static int[] yCoordinates = { 0, 1, 2, 0, 1, 2, 0, 1, 2 };// to make
																		// position
	private int indexForNextPosition;
	private int xCoordinate;// x coordinate on board
	private int yCoordinate;// y coordinate on board
	private Board board;
	private int steps;// the steps of the knight

	public Knight(Board board, int x, int y) throws CoordinateException {
		this.indexForNextPosition = START_POSITION_OF_ARRAY;
		this.steps = START_COUNT_STEPS;
		if (!isValidCoordinates(x, y)) {
			throw new CoordinateException(x, y);
		}

		this.xCoordinate = --x;// use arrays(start from 0)
		this.yCoordinate = --y;
		this.board = board;
	}

	@Override
	public void run() {
		startKnightMove(xCoordinate, yCoordinate);
	}

	// start moving, but first check if the knight is on wanted position

	/**
	 * @param x
	 *            for x coordinate
	 * @param y
	 *            for y coordinate
	 */
	private void startKnightMove(int x, int y) {
		if(checkIfKnightIsOnTheCenter(x, y)){
			try {
				throw new CanNotMoveException();
			} catch (CanNotMoveException e) {
				board.print(this);
				System.out.println(e.getMessage());
				return;
			}
		}
		if (wantedPosition(x, y)) {
			board.print(this);
			board.finishTheGame(this);
		} else {
			board.print(this);
			moveKnight(x, y);
		}

	}

	/*
	 * this method moves the knight recursively 1.Check if position is valid.
	 * 1.1 If position is valid make another check 1.1.1 If the knight is on the
	 * wanted position and finish the game 1.1.2 Else set the knight new
	 * coordinates 1.2 Calls itself with next position from xCoordinate[] and
	 * yCoordinate[]
	 */

	/**
	 * @param xNewCoordinate
	 *            for new x coordinate
	 * @param yNewCoordinate
	 *            for new y coordinate
	 */
	private void moveKnight(int xNewCoordinate, int yNewCoordinate) {

		if (indexForNextPosition == xCoordinates.length) {
			indexForNextPosition = START_POSITION_OF_ARRAY;
		}

		boolean isValidNewPosition = isValidNewPositon(xNewCoordinate, yNewCoordinate);
		if (isValidNewPosition && wantedPosition(xNewCoordinate, yNewCoordinate)) {
			xCoordinate = xNewCoordinate;// set new coordinates x and y
			yCoordinate = yNewCoordinate;
			steps++; // increment steps
			board.print(this);// print board with new position
			board.finishTheGame(this);
			return;
		}
		if (isValidNewPosition) {
			xCoordinate = xNewCoordinate;// set new coordinates x and y
			yCoordinate = yNewCoordinate;
			steps++; // increment steps
			board.print(this);// print board with new position
		}
		// call the method with next indexes from x and y coordinates arrays
		moveKnight(xCoordinates[indexForNextPosition], yCoordinates[indexForNextPosition++]);

	}

	// check if the knight is on wanted position
	public static boolean wantedPosition(int x, int y) {
		if (x == 2 && y == 2) {
			return true;
		}
		return false;
	}

	// check if new coordinates are available
	private boolean isValidNewPositon(int newX, int newY) {
		if (board.isEmptyPosition(newX, newY)) {
			// check possible position
			boolean position1or2 = ((newX == xCoordinate + 1) || (newX == xCoordinate - 1))
					&& ((newY == yCoordinate + 2) || (newY == yCoordinate - 2));
			boolean position3or4 = ((newX == xCoordinate + 2) || (newX == xCoordinate - 2))
					&& ((newY == yCoordinate + 1) || (newY == yCoordinate - 1));
			if (position1or2 || position3or4) {
				return true;
			}

		}
		return false;
	}

	private boolean isValidCoordinates(int x, int y) {
		// arrays start from 0
		--x;
		--y;
//		if (x == 1 && y == 1) {
//			return false;
//		}
		if (((x < MIN_COORDINATE) || x > MAX_COORDINATE) || ((y < MIN_COORDINATE) || y > MAX_COORDINATE)) {
			return false;
		}
		return true;
	}

	private boolean checkIfKnightIsOnTheCenter(int x, int y) {
		return x == 1 && y == 1;
	}

	public int getSteps() {
		return steps;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}
}
