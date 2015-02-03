package com.ant.main;

import com.ant.factory.FileFactory;
import com.ant.managers.MatchesManager;
import com.ant.managers.OutputManager;
import com.ant.managers.UserManager;

public class Game {

	public static FileFactory fileFactory;
	public static UserManager userManager;
	public static MatchesManager matchesManager;
	public static OutputManager outputManager;
	
	static {
		System.out.println("Start loading managers...");
		fileFactory = FileFactory.getInstance();
		userManager = UserManager.getInstance();
		matchesManager = MatchesManager.getInstance();
		outputManager = OutputManager.getInstance();
		System.out.println("Managers loaded.");
	}

	
	
	public static void main(String[] args) {
		System.out.println("Application started.");
		userManager.getUsers();
		userManager.getUsersSortedByWin();
		// test
//		try {
//			System.out.println(userManager.getUserById(10).getName());
//		} catch (UserNotFoundException e) {
//			e.printStackTrace();
//		}
		outputManager.start();
		
		
	}

}
