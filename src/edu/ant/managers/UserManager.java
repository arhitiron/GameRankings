package edu.ant.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ant.exceptions.UserNotFoundException;
import edu.ant.main.Game;
import edu.ant.models.User;

public class UserManager implements Manager {

//	public static boolean usersReady;
	
	private Map<Integer, User> users = new HashMap<>();
	private List<User> userList = new ArrayList<>();
	
	public void init() {
		System.out.println("Initializing UserManager");
		String currentLine;
		try {
			while ((currentLine = Game.fileManager.getNamesBufferedReader().readLine()) != null) {
				String[] userLine = currentLine.split(" ");
				if (userLine.length != 0) {
					User user = new User(Integer.parseInt(userLine[0]), userLine[1]);
					users.put(Integer.parseInt(userLine[0]), user);
					userList.add(user); // omg wtf?
				} 
			}
//			usersReady = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserById (int id) throws UserNotFoundException {
		User user = null;
		if (users.get(id) != null) {
			user = users.get(id);
		} else {
			throw new UserNotFoundException();
		}
		
		return user;
	}
	
	public List<User> getUsersByWins (int wins) {
		List<User> winUsers = new ArrayList<> ();
		for (java.util.Map.Entry<Integer, User> entry : ((Map<Integer, User>) users).entrySet()) {
			User user = entry.getValue();
			if (user.getWins() == wins) {
				winUsers.add(user);
			} 
		}
		return winUsers;
	}
	
	public Map<Integer, User> getUsers() {
		return users;
	}
	
	
	public List<User> getUserList() {
		return userList;
	}
	
	public void sortUsersByWin() {
		Collections.sort(userList, (p2, p1) -> ((Integer)p1.getWins()).compareTo(p2.getWins()));
//		return userList;
	}
	
}
