package boardLogic;

import exceptions.CoordinateException;

public class Board {

	private String[][] board;

	public Board() {
		this.board = new String[3][3];
		setBoard();
	}
	
	// set empty fields in board
	private void setBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = " ";
			}
		}
	}

	//print board with knight and position and steps
	public void print(Knight knight) {
		setStep(knight);
		board[knight.getxCoordinate()][knight.getyCoordinate()] = "K";
		printBoard(knight);
		System.out.println();
		System.out.println("---------------------------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printBoard(Knight knight) {
		System.out.println("    0  1  2");
		for (int i = 0; i < board.length; i++) {
			System.out.print("  " + i);
			for (int j = 0; j < board.length; j++) {
				System.out.print(" |" + board[i][j]);
			}
			System.out.println("|");

		}
	}

	//set last knight step on board
	private void setStep(Knight knight) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].equals("K")) {
					board[i][j] = String.valueOf(knight.getSteps());
				}
			}

		}
	}

	//set knight on board and check if x and y are illegal
	public void setTheKnightOnBoard(int x, int y) {
		
		try {
			Knight knight = new Knight(this, x, y);
			
			knight.start();
		} catch (CoordinateException e) {
			System.out.println(e.getMessage());
			
		}
	}

	//check if current position of array is empty
	public boolean isEmptyPosition(int newX, int newY) {
		if (board[newX][newY].equals(" ")) {
			return true;
		}
		return false;
	}

	//message for finished game
	public void finishTheGame(Knight knight) {
		System.out.println("The game finished. The knight went to 3x3 position by "+knight.getSteps()+ "steps!");

	}
	


}
