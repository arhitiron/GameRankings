package edu.ant.main;

import edu.ant.managers.FileManager;
import edu.ant.managers.Manager;
import edu.ant.managers.MatchesManager;
import edu.ant.managers.OutputFTLManager;
import edu.ant.managers.OutputManager;
import edu.ant.managers.UserManager;

public class Game {
	public static FileManager fileManager = new FileManager();
	public static UserManager userManager = new UserManager();
	public static MatchesManager matchesManager = new MatchesManager();
	public static OutputManager outputManager = new OutputManager();
	
	public static void main(String[] args) {
		initManagers();
		System.out.println("Application started.");
//		outputManager.start();
		(new OutputFTLManager()).printToFTL();
	}
	
	private static void initManagers() {
		initManager(fileManager);
		initManager(userManager);
		initManager(matchesManager);
		initManager(outputManager);
		
	}

	public static void initManager(Manager manager) {
		manager.init();
	}
}
