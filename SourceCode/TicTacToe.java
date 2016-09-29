import java.util.Scanner;
/*
COMP90041 Programming and Software Development
Semester 1, 2016
Project C
Student name: Yuchen.Qiao
Student ID: 748663
*/
public class TicTacToe {
	// create two object for class GameManager and PlayerManager;
	GameManager gamemanager;
	PlayerManager playermanager = new PlayerManager();
	/* the input scanner as a global variable */
	public static Scanner keyboard = new Scanner(System.in);

	/*---------------------------------------------------------------*/
	/* the main method */
	public static void main(String[] args) {
		// create an object "gameSystem" to activate the game
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.runGame();

	}
	/*---------------------------------------------------------------*/
	public void runGame() {
		playermanager.readDataFile();
		run();
	}
	/*---------------------------------------------------------------*/
	public void run() {

		boolean exitEnter = false;
		System.out.println("Welcome to Tic Tac Toe!");
		/*---------------------------------------------------------------*/
		do {
			try {
				System.out.println();
				System.out.print(">");
				String enterCommand = keyboard.nextLine();
				String[] divspace = enterCommand.split(" ");// divided by space
				/*---------------------------------------------------------------*/
				if (divspace[0].equals("exit")) {
					playermanager.writeDataFile();
					exitEnter = true;// change boolean value to end the loop
										// then end the program
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("addplayer")) {
					String[] divcomma = divspace[1].split(",");// divided by comma
					if (divcomma.length != 3) {
						throw new InargumentsException("Incorrect number of arguments supplied to command.");
					}
					playermanager.addPlayer(divcomma[0], divcomma[1], divcomma[2]);
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("addaiplayer")) {
					String[] divcomma = divspace[1].split(",");// divided by comma
					if (divcomma.length != 3) {
						throw new InargumentsException
						("Incorrect number of arguments supplied to command.");
					}
					playermanager.addaiplayer(divcomma[0], divcomma[1], divcomma[2]);
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("removeplayer")) {
					// judge whether delete one player or all players
					if (divspace.length != 1) {
						playermanager.removePlayer(divspace[1]);
					} else {
						System.out.println("Are you sure you want to remove all players? (y/n)");
						String s = keyboard.nextLine();
						if (s.equals("y")) {
							playermanager.removeallPlayer();
						}
					}
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("editplayer")) {
					String[] divcomma = divspace[1].split(",");
					if (divcomma.length != 3) {
						throw new InargumentsException("Incorrect number of arguments supplied to command.");
					}
					playermanager.editPlayer(divcomma[0], divcomma[1], divcomma[2]);
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("resetstats")) {
					// judge whether reset one player's or all players'
					// statistics
					if (divspace.length != 1) {
						playermanager.resetStats(divspace[1]);
					} else {
						System.out.println
						("Are you sure you want to reset all player statistics? (y/n)");
						String s1 = keyboard.nextLine();
						char c1 = s1.charAt(0);
						if (c1 == 'y') {
							playermanager.resetallStats();
						}
					}
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("displayplayer")) {
					// judge whether display one player's message or all
					// players'
					if (divspace.length != 1) {
						playermanager.displayPlayer(divspace[1]);
					} else {
						playermanager.displayallPlayer();
					}
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("rankings")) {
					playermanager.displayRanking();
				}
				/*---------------------------------------------------------------*/
				else if (divspace[0].equals("playgame")) {
					String[] divcomma = divspace[1].split(",");
					if (divcomma.length != 2) {
						throw new InargumentsException("Incorrect number of arguments supplied to command.");
					}
					if (playermanager.playgameFind(divcomma[0], divcomma[1])) {
						// get two players given name before start playing
						String nameNought =
						playermanager.list.get(playermanager.find(divcomma[0])).getgivenName();
						String nameCross =
						playermanager.list.get(playermanager.find(divcomma[1])).getgivenName();
						Player playerNought =
						playermanager.list.get(playermanager.find(divcomma[0]));
						Player playerCross =
						playermanager.list.get(playermanager.find(divcomma[1]));

						gamemanager = new GameManager();
						// start playing the game
						gamemanager.playGame(playerNought, playerCross, nameNought, nameCross);
						// if there is a winner
						if (gamemanager.currentState == gamemanager.NOUGHT_WIN) {
							playermanager.playGamehaswon(divcomma[0], divcomma[1]);
						}
						if (gamemanager.currentState == gamemanager.CROSS_WIN) {
							playermanager.playGamehaswon(divcomma[1], divcomma[0]);
						}
						// if there is a drawn
						if (gamemanager.currentState == gamemanager.GAMEDRAWN) {
							playermanager.playGamedrawn(divcomma[0], divcomma[1]);
						}
					} else if (!playermanager.playgameFind(divcomma[0], divcomma[1])) {
						System.out.println("Player does not exist.");
					}
				}
				else {
					throw new IncommandException("'" + divspace[0] + "' is not a valid command.");
				}
			/*---------------------------------------------------------------*/
			}
			catch (IncommandException e) {
				System.out.println(e.getMessage());
			}
			catch (InargumentsException ie) {
				System.out.println(ie.getMessage());
			}
			catch (IndexOutOfBoundsException ex) {
				System.out.println("Incorrect number of arguments supplied to command.");
			}
			/*---------------------------------------------------------------*/
		} while (!exitEnter);// if no exit entered, continue loop
		System.out.println();
		System.exit(0);// end the program
	}

}
