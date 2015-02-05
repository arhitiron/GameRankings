package edu.ant.output;

import edu.ant.models.User;

public interface Output {
	public void showUser(User user);
	public void showUser(User user, String[] params);
	public void showUsers();
	public void showUsers(String[] params);
	public void showHelp();
}
