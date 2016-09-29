/*
COMP90041 Programming and Software Development
Semester 1, 2016
Project B
Student name: Yuchen.Qiao
Student ID: 748663
*/
public class GameManager {
	/*---------------------------------------------------------------*/
	/* constants to represent all the marks */
	public static final int EMPTYMARK = 0;
	public static final int CROSSMARK = 1;
	public static final int NOUGHTMARK = 2;

	/* constants to represent the current states */
	public final int PLAYING = 0;
	public final int GAMEDRAWN = 1;
	public final int CROSS_WIN = 2;
	public final int NOUGHT_WIN = 3;

	/* variables to represent the game board */
	public static final int ROWS = 3;// rows and columns
	public static final int COLS = 3;

	/* a 2D array to represent the game board */
	public int[][] board = new int[ROWS][COLS];

	public int currentState;// the current state of the game
	public int currentPlayer;// the current player
	public int currentRows, currentCols;// current location of the mark

	Player playerNought, playerCross;

	// PlayerManager playerdata = new PlayerManager();
	/*---------------------------------------------------------------*/
	private String nameCross;// players' name with modifier "private"
	private String nameNought;

	/* the getter and setter methods for username'X' */
	public String getnameCross() {
		return nameCross;
	}

	public void setnameCross(String nameCross) {
		this.nameCross = nameCross;
	}

	/* the getter and setter methods for username'O' */
	public String getnameNought() {
		return nameNought;
	}

	public void setnameNought(String nameNought) {
		this.nameNought = nameNought;
	}

	/*---------------------------------------------------------------*/
	public void playGame
	(Player playerNought, Player playerCross, String nameNought, String nameCross) {
		this.nameNought = nameNought;
		this.nameCross = nameCross;
		this.playerNought = playerNought;
		this.playerCross = playerCross;

		initIALISE();// initialize the game board and status
		printBoard();// display the original game board

		/* the playing process once */
		do {
			moveINCHESS(currentPlayer);// update the currentRow and currentCol
			getGameState(currentPlayer, currentRows, currentCols);// return the current state
			printBoard();
			/* display the relevant message if game over */
			if (currentState == CROSS_WIN) {
				System.out.println("Game over. " + nameCross + " won!");
			} else if (currentState == NOUGHT_WIN) {
				System.out.println("Game over. " + nameNought + " won!");
			} else if (currentState == GAMEDRAWN) {
				System.out.println("Game over. It was a draw!");
			}
			/* switch the current player */
			currentPlayer = (currentPlayer == NOUGHTMARK) ? CROSSMARK : NOUGHTMARK;
		} while (currentState == PLAYING);// repeat the process till game over

		//TicTacToe.keyboard.nextLine();
	}

	/*---------------------------------------------------------------*/
	/* Initialize the game-board contents and set all cells to empty */
	public void initIALISE() {

		for (int row = 0; row < ROWS; ++row) {// use double loop to get all
												// cells{
			for (int col = 0; col < COLS; ++col) {
				board[row][col] = EMPTYMARK;// all cells should be empty
			}
		}
		currentState = PLAYING;// activate the game
		currentPlayer = NOUGHTMARK;// let player "o" plays first
	}

	/*---------------------------------------------------------------*/
	/* print the game board */
	public void printBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				updateCELLS(board[row][col]);// print all cells and update their
												// status
				if (col != COLS - 1) {
					System.out.print("|");// display the vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----");// display the horizontal partition
			}
		}
	}

	/* print all cells and update their status */
	public void updateCELLS(int cells) {
		switch (cells) {
		case EMPTYMARK:
			System.out.print(" ");
			break;
		case NOUGHTMARK:
			System.out.print("O");
			break;
		case CROSSMARK:
			System.out.print("X");
			break;
		}
	}
	/*---------------------------------------------------------------*/
	public void moveINCHESS(int moveMent) {
		boolean vaildInput = false;// for input validation
		do {
			int row = 0;
			int col = 0;
			if (moveMent == CROSSMARK) {
				System.out.println(nameCross + "'s move:");
				Move playerMove = playerCross.makeMove(board);
				row = playerMove.getcurrentRow();
				col = playerMove.getcurrentCol();
			} else {
				System.out.println(nameNought + "'s move:");
				Move playerMove = playerNought.makeMove(board);
				row = playerMove.getcurrentRow();
				col = playerMove.getcurrentCol();
			}
			if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTYMARK) {
				currentCols = col;
				currentRows = row;
				board[currentRows][currentCols] = moveMent;// update the game board contents
				vaildInput = true; // input okay, exit loop
			} else if (row < 0 || row >= ROWS || col < 0 || col > COLS) {
				System.out.println
				("Invalid move. " + "You must place at a cell within {0,1,2} {0,1,2}.");
			} else if (board[row][col] != EMPTYMARK) {
				System.out.println("Invalid move. The cell has been occupied.");
			}
	 	} while (!vaildInput); // repeat until input is valid
	}
	/*---------------------------------------------------------------*/
	/* return the current state of the game */
	public void getGameState(int thePieces, int currentRow, int currentCol) {
		if (vicTORY(thePieces, currentRow, currentCol)) { // if there is a
															// winner
			currentState = (thePieces == CROSSMARK) ? CROSS_WIN : NOUGHT_WIN;
		} else if (gameDRAWN()) {// if the result is draw
			currentState = GAMEDRAWN;
		}
		// besides, the game will still playing
	}

	/* return true if any player win this game after his/her move */
	public boolean vicTORY(int thePieces, int currentRow, int currentCol) {
		/* three same pieces a row */
		return (board[currentRow][0] == thePieces & board[currentRow][1] == thePieces
				& board[currentRow][2] == thePieces
				/* three same pieces a column */
				|| board[0][currentCol] == thePieces & board[1][currentCol] == thePieces
						& board[2][currentCol] == thePieces
				/* three same pieces in the diagonal */
				|| currentRow == currentCol & board[0][0] == thePieces & board[1][1] == thePieces
						& board[2][2] == thePieces
				/* three same pieces in the anti-diagonal */
				|| currentRow + currentCol == 2 & 
				board[0][2] == thePieces & board[1][1] == thePieces
						& board[2][0] == thePieces);
	}

	/* return ture if there is draw */
	public boolean gameDRAWN() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (board[row][col] == EMPTYMARK) { // if there is empty cell
					return false;
				}
			}
		}
		/* no winner, and all cells are occupied, which means draw */
		return true;
	}
	/*---------------------------------------------------------------*/
}
