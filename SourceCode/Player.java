import java.io.Serializable;

/*
COMP90041 Programming and Software Development
Semester 1, 2016
Project C
Student name: Yuchen.Qiao
Student ID: 748663
*/
public abstract class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	// these three variables for stating the game data;
	private int gamePlayed;
	private int gameWon;
	private int gameDrawn;

	// these three variables for stating the user's name;
	private String userName;
	private String familyName;
	private String givenName;
	/*---------------------------------------------------------------*/
	public Player(String userName, String familyName, String givenName) {
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		// initialize the game data
		gamePlayed = 0;
		gameWon = 0;
		gameDrawn = 0;
	}

	/*---------------------------------------------------------------*/
	public abstract Move makeMove(int[][] board);

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for number of played games */
	public int getgamePlayed() {
		return gamePlayed;
	}

	public void setgamePlayed(int gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for number of won */
	public int getgameWon() {
		return gameWon;
	}

	public void setgameWon(int gameWon) {
		this.gameWon = gameWon;
	}

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for number of drawn */
	public int getgameDrawn() {
		return gameDrawn;
	}

	public void setgameDrawn(int gameDrawn) {
		this.gameDrawn = gameDrawn;
	}

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for user name */
	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for family name */
	public String getfamilyName() {
		return familyName;
	}

	public void setfamilyName(String familyName) {
		this.familyName = familyName;
	}

	/*---------------------------------------------------------------*/
	/* the getter and setter methods for given name */
	public String getgivenName() {
		return givenName;
	}

	public void setgivenName(String givenName) {
		this.givenName = givenName;
	}
	/*---------------------------------------------------------------*/
}
