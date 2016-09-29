import java.io.Serializable;

public class AIPlayer extends Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int NOUGHT = 2;

	/*---------------------------------------------------------------*/
	public AIPlayer(String userName, String familyName, String givenName) {

		super(userName, familyName, givenName);
	}

	public Move makeMove(int[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == EMPTY)
					return new Move(i, j);
			}
		}
		return null;
	}
	/*---------------------------------------------------------------*/
}
