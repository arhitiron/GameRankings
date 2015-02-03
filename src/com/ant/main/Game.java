package com.ant.main;

import com.ant.factory.FileFactory;
import com.ant.managers.MatchesManager;
import com.ant.managers.OutputManager;
import com.ant.managers.UserManager;

public class Game {
	public static FileFactory fileFactory = FileFactory.getInstance();
	public static UserManager userManager = UserManager.getInstance();
	public static MatchesManager matchesManager = MatchesManager.getInstance();
	public static OutputManager outputManager = OutputManager.getInstance();
	
	public static void main(String[] args) {
		System.out.println("Application started.");
		userManager.getUsers();
		userManager.getUsersSortedByWin();
		outputManager.start();
	}
}
