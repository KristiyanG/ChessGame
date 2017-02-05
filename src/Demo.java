import boardLogic.Board;

public class Demo {

	public static void main(String[] args) {
		if(args.length!=2){
			System.out.println("Enter two parameters for x and y !");
			System.exit(0);
		}
		int x = Integer.valueOf(args[0]);
		int y = Integer.valueOf(args[1]);
		Board board = new Board();
		board.setTheKnightOnBoard(x,y);
	}
}
