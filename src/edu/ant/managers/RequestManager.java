package edu.ant.managers;

import java.util.Arrays;
import java.util.Scanner;

import edu.ant.config.Config;
import edu.ant.exceptions.OutputTypeNotFoundException;
import edu.ant.exceptions.UserNotFoundException;
import edu.ant.factory.OutputTypeFactory;
import edu.ant.main.Game;
import edu.ant.models.User;
import edu.ant.output.Output;
import edu.ant.outputscreens.HelpScreen;

public class RequestManager implements Manager{

	private boolean running;
	private Output output;
	
	public RequestManager() {
		try {
			initOutputType();
		} catch (OutputTypeNotFoundException e) {
			e.printStackTrace();
		}
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
            		output.showHelp();
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
	
	private void initOutputType() throws OutputTypeNotFoundException {
		switch (Config.OUTPUT_TYPE) {
			case "ftl":
				output = OutputTypeFactory.getFTLOutput();
				break;
			case "simple":
				output = OutputTypeFactory.getSimpleOutput();
				break;
			default:
				throw new OutputTypeNotFoundException();
		}
	}
	
	private boolean parseRequest(String request) {
		String lowerCasedRequest = request.toLowerCase();
		boolean parsed = false;
		if (lowerCasedRequest.indexOf("select user") != -1) {
			(new RequesParser()).parseRequest(request);
			parsed = true;
		}
		
//		if (lowerCasedRequest.indexOf("select matches by") != -1) {
//			MatchesInputParser.parseRequest(request);
//			parsed = true;
//		}
//		
		return parsed;
	}
	
	@Override
	public void init() {
		
	}
	
	private class RequesParser {

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
						output.showUser(user, Arrays.copyOfRange(requestArray, 1, requestArray.length));
					} else {
						output.showUser(user);
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
					output.showUsers(Arrays.copyOfRange(requestArray, 1, requestArray.length));
				} else {
					output.showUsers();
				}
			}
		}
	}
	
//	private static class MatchesInputParser {
//		
//		public static void parseRequest(String lowerCasedRequest) {
//			
//		}
//	}

}
