package edu.ant.managers;

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
import edu.ant.models.User;
import edu.ant.outputscreens.HelpScreen;
import edu.ant.outputscreens.MatchesScreen;
import edu.ant.outputscreens.Screen;
import edu.ant.outputscreens.UsersScreen;

public class OutputManager implements Manager{

	private boolean running;
	private Map<String, Screen> screens = new HashMap<>();
	
	public void init() {
		System.out.println("Inintializing OutputManager...");
		screens.put("helpScreen", new HelpScreen());
		screens.put("matchesScreen", new MatchesScreen());
		screens.put("usersScreen", new UsersScreen());
		System.out.println("OutputManager inintialized");
	}
	
	public void start() {
		System.out.println("Enter command (for help type 'help' or '?'): ");
		running = true;
	    String inputRequest;
 
        Scanner scanIn = new Scanner(System.in);
        // need normal decision
        while (running) {
            inputRequest = scanIn.nextLine();
            boolean parsed = false;
            switch (inputRequest) {
            	case "exit": case "close":
                	scanIn.close();
                	running = false;
                	parsed = true;
                	break;
            	case "help": case "?":
            		((HelpScreen)screens.get("helpScreen")).showHelp();
            		parsed = true;
            		break;
        		default:
        			parsed = parseRequest(inputRequest);
            }
            
            if (!parsed && !inputRequest.equals("")) {
            	System.err.println("Parse error. Try again, or type 'help'");
            }
        }
        System.exit(0);
        System.out.println("Application stopped.");
	}
	
	private boolean parseRequest(String request) {
		String lowerCasedRequest = request.toLowerCase();
		boolean parsed = false;
		if (lowerCasedRequest.indexOf("select user") != -1) {
			(new UserInputParser()).parseRequest(request);
			parsed = true;
		}
		
		if (lowerCasedRequest.indexOf("select matches by") != -1) {
			MatchesInputParser.parseRequest(request);
			parsed = true;
		}
		
		return parsed;
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
	
	private class UserInputParser {

		public void parseRequest(String cRequest) {
			String[] requestArray;
			if (cRequest.indexOf("select user where id") != -1) {
				User user = null;
				requestArray = cRequest.substring("select user where id".length()).split(",");
				requestArray[0] = requestArray[0].split("=")[1];
				for (int i = 0; i < requestArray.length; i++) {
					requestArray[i] = requestArray[i].trim();
				}
				try {
					user = Game.userManager.getUserById(Integer.parseInt(requestArray[0]));
					if (requestArray.length != 1) {
						((UsersScreen)screens.get("usersScreen")).showUser(user, Arrays.copyOfRange(requestArray, 1, requestArray.length));
					} else {
						((UsersScreen)screens.get("usersScreen")).showUser(user);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				}
				
			} else {
				requestArray = cRequest.substring("select users".length()).split(",");
				for (int i = 0; i < requestArray.length; i++) {
					requestArray[i] = requestArray[i].trim();
				}
				
				if (requestArray.length != 1) {
					((UsersScreen)screens.get("usersScreen")).showUsers(requestArray);
				} else {
					((UsersScreen)screens.get("usersScreen")).showUsers();
				}
			}
		}
	}
	
	private static class MatchesInputParser {
		
		public static void parseRequest(String lowerCasedRequest) {
			
		}
	}

	
}
