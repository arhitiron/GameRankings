package com.ant.managers;

import java.io.IOException;
import java.util.Map;

import com.ant.exceptions.UserNotFoundException;
import com.ant.main.Game;
import com.ant.models.User;

public class MatchesManager {
	
	private static MatchesManager instance;
	
	private MatchesManager() {
		init();
	}
	
	public static MatchesManager getInstance() {
		if (instance == null) {
			instance = new MatchesManager();
		}
		return instance;
	}
	
	private void init() {
		System.out.println("Initializing MatchesManager");
		String currentLine;
		try {
			while ((currentLine = Game.fileFactory.getMatchesBufferedReader().readLine()) != null) {
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
