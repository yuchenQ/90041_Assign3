import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {

	private static final long serialVersionUID = 1L;
	/*---------------------------------------------------------------*/
	public HumanPlayer(String userName, String familyName, String givenName) {

		super(userName, familyName, givenName);
	}

	/*---------------------------------------------------------------*/
	public Move makeMove(int[][] board) {

		int row = TicTacToe.keyboard.nextInt();
		
		String restall = TicTacToe.keyboard.nextLine();
		String stringCols = restall.split(" ")[1];
		int col = Integer.valueOf(stringCols);

		return new Move(row, col);
	}
	/*---------------------------------------------------------------*/
}
