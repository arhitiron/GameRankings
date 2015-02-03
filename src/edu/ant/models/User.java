package edu.ant.models;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int id;
	private String name;
	private int wins;
	private int gamesPlayed;
	private List<User> whoWon = new ArrayList<> ();
	private List<User> whoLost = new ArrayList<> ();
	private List<User> withWhomPlayed = new ArrayList<> ();
	private List<User> withWhomShouldPlay = new ArrayList<> ();

	public User() {
		
	}
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void incrementWins() {
		this.wins++;
		this.gamesPlayed++;
	}
	
	public void incrementGamesPlayed() {
		this.gamesPlayed++;
	}
	
	public void addWhoWon (User user) {
		if (this.whoWon.indexOf(user) == -1) {
			this.whoWon.add(user);
		}
	}
	
	public void addWhoLost (User user) {
		if (this.whoLost.indexOf(user) == -1) {
			this.whoLost.add(user);
		}
	}	

	public void addWithWhomPlayed(User user) {
		if (this.withWhomPlayed.indexOf(user) == -1) {
			this.withWhomPlayed.add(user);
		}
	}
	
	public void addWithWhomShouldPlay(User user) {
		if (this.withWhomShouldPlay.indexOf(user) == -1) {
			this.withWhomShouldPlay.add(user);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public List<User> getWhoWon() {
		return whoWon;
	}

	public void setWhoWon(List<User> whoWon) {
		this.whoWon = whoWon;
	}

	public List<User> getWhoLost() {
		return whoLost;
	}

	public void setWhoLost(List<User> whoLost) {
		this.whoLost = whoLost;
	}

	public List<User> getWithWhomPlayed() {
		return withWhomPlayed;
	}

	public void setWithWhomPlayed(List<User> withWhomPlayed) {
		this.withWhomPlayed = withWhomPlayed;
	}

	public List<User> getWithWhomShouldPlay() {
		return withWhomShouldPlay;
	}

	public void setWithWhomShouldPlay(List<User> withWhomShouldPlay) {
		this.withWhomShouldPlay = withWhomShouldPlay;
	}
	
	
	
}
