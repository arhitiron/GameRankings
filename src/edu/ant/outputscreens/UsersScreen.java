package edu.ant.outputscreens;

import java.lang.reflect.Field;

import edu.ant.main.Game;
import edu.ant.models.User;
import edu.ant.output.SimpleOutput;

public class UsersScreen implements Screen{
	private Class userClass = User.class;
	
	public void showUser(User user) {
		for (Field fieldName : userClass.getDeclaredFields()) {
			String property = SimpleOutput.getPreparedProperty(user, fieldName.getName());
			System.out.print(property + " | ");	
		}
		System.out.println();
	}
	
	public void showUser(User user, String[] params) {
		for (String param : params) {
			if (param.equals("")) {
				continue;
			}
			try {
				userClass.getDeclaredField(param); // check is property exist
				String property = SimpleOutput.getPreparedProperty(user, param);
				System.out.print(property + " | ");	
			} catch (NoSuchFieldException e) {
				System.err.println("There is no field with name '" + param + "'");
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	public void showUsers() {
		Game.userManager.sortUsersByWin();
		for (User user : Game.userManager.getUserList()) {
			showUser(user);
		}
	}
	
	public void showUsers(String[] params) {
		Game.userManager.sortUsersByWin();
		for (User user : Game.userManager.getUserList()) {
			showUser(user, params);
		}
	}
}
