package edu.ant.main;

import edu.ant.managers.FileManager;
import edu.ant.managers.Manager;
import edu.ant.managers.MatchesManager;
import edu.ant.managers.RequestManager;
import edu.ant.managers.UserManager;
import edu.ant.output.FTLOutput;
import edu.ant.output.SimpleOutput;

public class Game {
	public static FileManager fileManager = new FileManager();
	public static UserManager userManager = new UserManager();
	public static MatchesManager matchesManager = new MatchesManager();
	public static SimpleOutput outputManager = new SimpleOutput();
	public static RequestManager requestManager = new RequestManager();
	
	public static void main(String[] args) {
		initManagers();
		System.out.println("Application started.");
		requestManager.start();
	}
	
	private static void initManagers() {
		initManager(fileManager);
		initManager(userManager);
		initManager(matchesManager);
		initManager(requestManager);
		
	}

	public static void initManager(Manager manager) {
		manager.init();
	}
}
