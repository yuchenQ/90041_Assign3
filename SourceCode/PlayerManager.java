import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/*
COMP90041 Programming and Software Development
Semester 1, 2016
Project C
Student name: Yuchen.Qiao
Student ID: 748663
*/
public class PlayerManager {
	// create a array_list which contain objects in class Player as member
	ArrayList<Player> list = new ArrayList<Player>();
	//public int winRatio = 0;// represent the winning ratio
	//public int drawRatio = 0;// represent drawing ratio
	public String name_nought;// represent the 'O' player
	public String name_cross;// represent the 'X' player

	/*---------------------------------------------------------------*/
	public void readDataFile() {
		File file = new File("Player.dat");
		if (!file.exists()) return;

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Player.dat"));
			ArrayList<Player> playerlist = (ArrayList<Player>) ois.readObject();
			this.list = playerlist;
			ois.close();
		}
		catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/*---------------------------------------------------------------*/
	public void writeDataFile() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Player.dat"));
			oos.writeObject(list);
			oos.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*---------------------------------------------------------------*/
	// the addplayer method to add new member into the array_list
	public void addPlayer(String userName, String familyName, String givenName) {

		boolean sameordiff = false;// use boolean value to control the judgment
		for (int i = 0; i < list.size(); i++) {
			// if input is match with the exist player, respond error message
			if (list.get(i).getuserName().equals(userName)) {
				System.out.println("The username has been used already.");
				sameordiff = true;
				break;
			}
		}
		// add new player when finish checking from exist members
		if (!sameordiff) {
			list.add(new HumanPlayer(userName, familyName, givenName));
		}

	}

	/*---------------------------------------------------------------*/
	public void addaiplayer(String userName, String familyName, String givenName) {
		boolean sameordiff = false;// use boolean value to control the judgment
		for (int i = 0; i < list.size(); i++) {
			// if input is match with the exist player, respond error message
			if (list.get(i).getuserName().equals(userName)) {
				System.out.println("The username has been used already.");
				sameordiff = true;
				break;
			}
		}
		// add new player when finish checking from exist members
		if (!sameordiff) {
			list.add(new AIPlayer(userName, familyName, givenName));
		}
	}

	/*---------------------------------------------------------------*/
	// this method aims to remove a single player
	public void removePlayer(String userName) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName)) {
				list.remove(i);// remove the chosen player
				break;
			} else
				System.out.println("The player does not exist.");

		}

	}

	// this method is to remove all players from the list
	public void removeallPlayer() {

		list.clear();// clear the list

	}

	/*---------------------------------------------------------------*/
	// this method can provide a chance to modify the name of player
	public void editPlayer(String userName, String familyName, String givenName) {
		boolean sameordiff = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName)) {
				list.get(i).setfamilyName(familyName);
				list.get(i).setgivenName(givenName);
				sameordiff = true;
				return;
			}
		}
		// if player does not exist
		if (!sameordiff) {
			System.out.println("The player does not exist.");
		}
	}

	/*---------------------------------------------------------------*/
	// this method is to reset a single player's statistics
	public void resetStats(String userName) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName)) {
				list.get(i).setgamePlayed(0);// set all statistics to 0;
				list.get(i).setgameWon(0);
				list.get(i).setgameDrawn(0);
				break;
			} else
				System.out.println("The player does not exist.");// if player
																	// does not
																	// exist
		}

	}

	// this method is to reset all player's statistics
	public void resetallStats() {
		// get all members in list
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setgamePlayed(0);
			list.get(i).setgameWon(0);
			list.get(i).setgameDrawn(0);
		}

	}

	/*---------------------------------------------------------------*/
	// this method is to display a single player's information
	public void displayPlayer(String userName) {

		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			// find the player
			if (list.get(i).getuserName().equals(userName)) {
				System.out.println(userName + "," + list.get(i).getfamilyName() + "," + list.get(i).getgivenName() + ","
						+ list.get(i).getgamePlayed() + " games," + list.get(i).getgameWon() + " wins,"
						+ list.get(i).getgameDrawn() + " draws");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("The player does not exist.");
		}

	}

	// this method can display all players' information, ordered by username
	// alphabetically
	public void displayallPlayer() {

		int size = list.size();
		// bubble sort need a agent to swap two variables
		String media1 = "";
		String media2 = "";
		String media3 = "";
		int media4 = 0;
		int media5 = 0;
		int media6 = 0;

		String[] username_rank = new String[size];
		String[] familyname_rank = new String[size];
		String[] givenname_rank = new String[size];
		int[] games_rank = new int[size];
		int[] wins_rank = new int[size];
		int[] draws_rank = new int[size];
		// get all data and put them into each array
		for (int i = 0; i < list.size(); i++) {
			username_rank[i] = (String) list.get(i).getuserName();
			familyname_rank[i] = (String) list.get(i).getfamilyName();
			givenname_rank[i] = (String) list.get(i).getgivenName();
			games_rank[i] = list.get(i).getgamePlayed();
			wins_rank[i] = list.get(i).getgameWon();
			draws_rank[i] = list.get(i).getgameDrawn();
		}
		// this is bubble sort, if value in j is bigger than value in k, swap
		// their location
		for (int j = 0; j < list.size(); j++) {
			for (int k = j + 1; k < list.size(); k++) {
				// compare two names
				if ((username_rank[j].compareTo(username_rank[k]) > 0)) {
					// swap value j and k
					media1 = username_rank[j];
					username_rank[j] = username_rank[k];
					username_rank[k] = media1;

					media2 = familyname_rank[j];
					familyname_rank[j] = familyname_rank[k];
					familyname_rank[k] = media2;

					media3 = givenname_rank[j];
					givenname_rank[j] = givenname_rank[k];
					givenname_rank[k] = media3;

					media4 = games_rank[j];
					games_rank[j] = games_rank[k];
					games_rank[k] = media4;

					media5 = wins_rank[j];
					wins_rank[j] = wins_rank[k];
					wins_rank[k] = media5;

					media6 = draws_rank[j];
					draws_rank[j] = draws_rank[k];
					draws_rank[k] = media6;
				}
			}
		}
		// output the display list
		for (int i = 0; i < list.size(); i++) {
			System.out.println(username_rank[i] + "," + familyname_rank[i] + "," + givenname_rank[i] + ","
					+ games_rank[i] + " games," + wins_rank[i] + " wins," + draws_rank[i] + " draws");
		}
	}

	/*---------------------------------------------------------------*/
	public void displayRanking() {
		// bubble sort need a agent to swap two variables
		double media1 = 0;
		double media2 = 0;
		String media3 = "";
		int media4 = 0;
		int size = list.size();

		double[] win_ratio = new double[size];
		double[] draw_ratio = new double[size];
		String[] name_Rank = new String[size];
		int[] game_Rank = new int[size];
		// get all data and put them into each array
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getgamePlayed() != 0) {

				win_ratio[i] =
						(double)100 * list.get(i).getgameWon() / list.get(i).getgamePlayed();
				draw_ratio[i] =
						(double)100 * list.get(i).getgameDrawn()/list.get(i).getgamePlayed();
			}
			else {
				win_ratio[i] = 0;
				draw_ratio[i] = 0;
			}
			name_Rank[i] = list.get(i).getuserName();
			game_Rank[i] = list.get(i).getgamePlayed();
		}
		/*
		 * this is bubble sort, if winning ratio in j is bigger than the value
		 * in k, swap their location
		 */
		for (int j = 0; j < size; j++) {
			for (int k = j + 1; k < size; k++) {
				if (win_ratio[j] < win_ratio[k]) {

					media1 = win_ratio[j];
					win_ratio[j] = win_ratio[k];
					win_ratio[k] = media1;

					media2 = draw_ratio[j];
					draw_ratio[j] = draw_ratio[k];
					draw_ratio[k] = media2;

					media3 = name_Rank[j];
					name_Rank[j] = name_Rank[k];
					name_Rank[k] = media3;

					media4 = game_Rank[j];
					game_Rank[j] = game_Rank[k];
					game_Rank[k] = media4;
				}
			}
		}
		for (int j = 0; j < size; j++) {
			for (int k = j + 1; k < size; k++) {
				if ((win_ratio[j] == win_ratio[k]) && (draw_ratio[j] < draw_ratio[k])) {
					// then sort these player who has same winning ratio, using
					// difference of drawing ratio
					media1 = win_ratio[j];
					win_ratio[j] = win_ratio[k];
					win_ratio[k] = media1;

					media2 = draw_ratio[j];
					draw_ratio[j] = draw_ratio[k];
					draw_ratio[k] = media2;

					media3 = name_Rank[j];
					name_Rank[j] = name_Rank[k];
					name_Rank[k] = media3;

					media4 = game_Rank[j];
					game_Rank[j] = game_Rank[k];
					game_Rank[k] = media4;
				}
			}
		}
		for (int j = 0; j < size; j++) {
			for (int k = j + 1; k < size; k++) {
				if ((win_ratio[j] == win_ratio[k]) && (draw_ratio[j] == draw_ratio[k])
						&& (name_Rank[j].compareTo(name_Rank[k]) > 0)
						&& (name_Rank[j] != "") && (name_Rank[j] != "")) {
					// thirdly, sort the name for players who has same value in
					// winning and drawing ratio
					media1 = win_ratio[j];
					win_ratio[j] = win_ratio[k];
					win_ratio[k] = media1;

					media2 = draw_ratio[j];
					draw_ratio[j] = draw_ratio[k];
					draw_ratio[k] = media2;

					media3 = name_Rank[j];
					name_Rank[j] = name_Rank[k];
					name_Rank[k] = media3;

					media4 = game_Rank[j];
					game_Rank[j] = game_Rank[k];
					game_Rank[k] = media4;
				}
			}
		}
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		if (size < 10) {// if list has less than ten member, display all players
			for (int i = 0; i < size; i++) {
				System.out.printf
				(" %3.0f%% | %3.0f%% |%3d   | %s\n", win_ratio[i], draw_ratio[i], game_Rank[i],
						name_Rank[i]);
			}
		} else if (size > 10) {
			for (int i = 0; i < 10; i++) {
				System.out.printf
				(" %3.0f%% | %3.0f%% |%3d   | %s\n", win_ratio[i], draw_ratio[i], game_Rank[i],
						name_Rank[i]);
				//%4d%%
			}
		}
	}
	/*---------------------------------------------------------------*/
	// this method will try to find the user and return a value
	public int find(String user) {
		int index = -1;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getuserName().equals(user)) {
				index = i;
			}
		return index;
	}
	/*---------------------------------------------------------------*/
	// this method serves the playgame part, it can check whether input names
	// existed
	public boolean playgameFind(String userName1, String userName2) {

		boolean sameordiff1 = false;
		boolean sameordiff2 = false;

		for (int i = 0; i < list.size(); i++) {
			// try to find name 1
			if (list.get(i).getuserName().equals(userName1)) {
				name_nought = list.get(i).getgivenName();
				sameordiff1 = true;
			}
			// try to find name 2
			if (list.get(i).getuserName().equals(userName2)) {
				name_cross = list.get(i).getgivenName();
				sameordiff2 = true;
			}
			if (sameordiff1 && sameordiff2) {
				break;
			}
		}
		// only if both of names have been found, return true
		if (sameordiff1 && sameordiff2) {
			return true;
		} else
			return false;
	}

	/*---------------------------------------------------------------*/
	// this method can add relevant data for winner and loser
	public void playGamehaswon(String userName_WON, String userName_LOSE) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName_WON)) {
				// for winner, win times plus 1 and play times plus 1
				list.get(i).setgameWon(list.get(i).getgameWon() + 1);
				list.get(i).setgamePlayed(list.get(i).getgamePlayed() + 1);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName_LOSE)) {
				// for loser only play plus times 1
				list.get(i).setgamePlayed(list.get(i).getgamePlayed() + 1);
			}
		}

	}

	// this method can add relevant data for both players when drawn happened
	public void playGamedrawn(String userName1, String userName2) {
		// both players will get one more drawn and played game
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName1)) {

				list.get(i).setgameDrawn(list.get(i).getgameDrawn() + 1);
				list.get(i).setgamePlayed(list.get(i).getgamePlayed() + 1);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getuserName().equals(userName2)) {

				list.get(i).setgameDrawn(list.get(i).getgameDrawn() + 1);
				list.get(i).setgamePlayed(list.get(i).getgamePlayed() + 1);
			}
		}

	}
	/*---------------------------------------------------------------*/
}
