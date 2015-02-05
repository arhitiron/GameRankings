package edu.ant.output;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import edu.ant.exceptions.UserNotFoundException;
import edu.ant.main.Game;
import edu.ant.managers.Manager;
import edu.ant.models.User;
import edu.ant.outputscreens.HelpScreen;
import edu.ant.outputscreens.MatchesScreen;
import edu.ant.outputscreens.Screen;
import edu.ant.outputscreens.UsersScreen;

public class SimpleOutput implements Output{
	private Map<String, Screen> screens = new HashMap<>();
	
	public SimpleOutput() {
		System.out.println("Inintializing OutputManager...");
		screens.put("helpScreen", new HelpScreen());
		screens.put("matchesScreen", new MatchesScreen());
		screens.put("usersScreen", new UsersScreen());
		System.out.println("OutputManager inintialized");
	}
	
	public static String getPreparedProperty(Object bean, String fieldName) {
		String property = null;
		try {
			property = BeanUtils.getProperty(bean, fieldName);
			if (PropertyUtils.getPropertyType(bean, fieldName).getName() == "java.util.List") {
				property = "";
				List<User> propertyObject = (List<User>)PropertyUtils.getProperty(bean, fieldName);
				for (int i = 0; i < propertyObject.size(); i++) {
					User user = propertyObject.get(i);
					property += ((i == 0) ? "" : ", ") + user.getName();
				}
			}
			
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		return property;
	}
	
	public Map<String, Screen> getScreens() {
		return screens;
	}

	public void setScreens(Map<String, Screen> screens) {
		this.screens = screens;
	}

	@Override
	public void showUser(User user) {
		((UsersScreen)screens.get("usersScreen")).showUser(user);
	}

	@Override
	public void showUser(User user, String[] params) {
		((UsersScreen)screens.get("usersScreen")).showUser(user, params);
	}

	@Override
	public void showUsers() {
		((UsersScreen)screens.get("usersScreen")).showUsers();
	}

	@Override
	public void showUsers(String[] params) {
		((UsersScreen)screens.get("usersScreen")).showUsers(params);
	}

	@Override
	public void showHelp() {
		((HelpScreen)screens.get("helpScreen")).showHelp();		
	}
	
	

	
}
