package com.ant.outputscreens;

public class HelpScreen implements Screen {

	public void showHelp() {
		System.out.println("You can type next commands:");	
		System.out.println("select user where id = {userID}, {fields what you want select, e.g.: name, wins, playedGames}");
		System.out.println("select users, {fields what you want select, e.g.: name, wins, playedGames}");
//		System.out.println("select matches by {userID}");
	}
}