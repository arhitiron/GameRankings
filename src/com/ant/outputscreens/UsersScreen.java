package com.ant.outputscreens;

import java.lang.reflect.Field;

import com.ant.main.Game;
import com.ant.managers.OutputManager;
import com.ant.models.User;

public class UsersScreen implements Screen{
	
	private Class userClass = User.class;
	
	public void showUser(User user) {
		for (Field fieldName : userClass.getDeclaredFields()) {
			String property = OutputManager.getPreparedProperty(user, fieldName.getName());
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
				String property = OutputManager.getPreparedProperty(user, param);
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
		for (User user : Game.userManager.getUserList()) {
			showUser(user);
		}
	}
	
	public void showUsers(String[] params) {
		for (User user : Game.userManager.getUserList()) {
			showUser(user, params);
		}
	}
	
	
	

}
