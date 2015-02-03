package edu.ant.managers;

import java.io.IOException;
import java.util.Map;

import edu.ant.exceptions.UserNotFoundException;
import edu.ant.main.Game;
import edu.ant.models.User;

public class MatchesManager implements Manager{
	
	public void init() {
		System.out.println("Initializing MatchesManager...");
		String currentLine;
		try {
			while ((currentLine = Game.fileManager.getMatchesBufferedReader().readLine()) != null) {
				String[] userLine = currentLine.split(" ");
				if (userLine.length != 0) {
					try {
						prepareWinner(Game.userManager.getUserById(Integer.parseInt(userLine[0])),
								Game.userManager.getUserById(Integer.parseInt(userLine[1])));
						prepareLoser(Game.userManager.getUserById(Integer.parseInt(userLine[1])),
								Game.userManager.getUserById(Integer.parseInt(userLine[0])));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UserNotFoundException e) {
						System.out.println("Users with id = '" + Integer.parseInt(userLine[0]) + "' "
								+ "or with id = '" +Integer.parseInt(userLine[1])+ "' does not exist");
						e.printStackTrace();
					}
				} 
			}
			
			for (java.util.Map.Entry<Integer, User> entry : ((Map<Integer, User>) Game.userManager.getUsers()).entrySet()) {
				User user = entry.getValue();
				generateWithWhomShouldPlay(user);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("MatchesManager inintialized");
	}
	
	private void prepareWinner(User user, User opponent) {
		user.incrementWins();
		user.addWhoWon(opponent);
		user.addWithWhomPlayed(opponent);
	}
	
	private void prepareLoser (User user, User opponent) {
		user.incrementGamesPlayed();
		user.addWhoLost(opponent);
		user.addWithWhomPlayed(opponent);
	}
	
	private void generateWithWhomShouldPlay (User cUser) {
		for (java.util.Map.Entry<Integer, User> entry : ((Map<Integer, User>) Game.userManager.getUsers()).entrySet()) {
			User user = entry.getValue();
			if (cUser.getWithWhomPlayed().indexOf(user) == -1 && !user.equals(cUser)) {
				cUser.addWithWhomShouldPlay(user);
			}
		}
	}
	
}
